package com.hand.activity.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.hand.activity.mapper.RentActivityMapper;
import com.hand.activity.vacation.dto.ActivityVacation;
import com.hand.activity.vacation.service.ActivityVacationService;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.dto.ResponseData;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hand.activity.dto.RentActivity;
import com.hand.activity.service.IRentActivityService;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RentActivityServiceImpl extends BaseServiceImpl<RentActivity>  implements IRentActivityService{
    @Autowired
    private RentActivityMapper rentActivityMapper;
    @Autowired
    ActivityVacationService service;

    @Override
    public List<RentActivity> selectRentActivity(IRequest requestContext,  int page, int pageSize, RentActivity rentActivity){
        PageHelper.startPage(page, pageSize);
       return rentActivityMapper.selectRentActivity(rentActivity);
    }
    @Override
    public List<RentActivity> selectRentActivityAA(IRequest requestContext,  int page, int pageSize, RentActivity rentActivity){
        PageHelper.startPage(page, pageSize);
        return rentActivityMapper.selectRentActivityAA(rentActivity);
    }
    @Override
    public void updateActiveStatus(RentActivity rentActivity){
        rentActivityMapper.updateActiveStatus(rentActivity);
    }
    @Override
    public void updateByTimeActiveStatus(){

    rentActivityMapper.updateByTimeActiveStatus();
    }
    @Override
    public void updateByTimeActiveStatusaa(){
    rentActivityMapper.updateByTimeActiveStatusaa();
    }
    @Override
    public void submitAndAct(IRequest requestContext, RentActivity dto){
        ActivityVacation dto1 =new ActivityVacation();
        createParams(dto,dto1);
        RentActivity rentActivity = new RentActivity();
        rentActivity.setActivityId(dto.getActivityId());
        rentActivity.setActiveStatus("under_review");
        rentActivityMapper.updateActiveStatus(rentActivity);
        service.startBill(requestContext,dto1);
    }
    protected void createParams(RentActivity rentActivities, ActivityVacation dto){
        dto.setActivitiCode("activity");
        dto.setBusinessKey(rentActivities.getActivityId()+"");
        List<RestVariable> variables = new ArrayList<>();

        RestVariable actCode = new RestVariable();
        actCode.setName("actCode");
        actCode.setType("string");
        actCode.setValue("activity");

        String actRole="";
        if(rentActivities.getActivityAmount()>10000){
            actRole="经理";
        }else if(rentActivities.getActivityAmount()<=10000){
            actRole="组长";
        }


        RestVariable actrole = new RestVariable();
        actrole.setName("actrole");
        actrole.setType("string");
        actrole.setValue(actRole);

        RestVariable amount = new RestVariable();
        amount.setName("amount");
        amount.setType("long");
        amount.setValue(rentActivities.getActivityAmount());


        variables.add(amount);
        variables.add(actrole);
        variables.add(actCode);
        dto.setVariable(variables);

    }



        @Autowired
        private RentActivityMapper orgAccessMapper;
        @Autowired
        private ObjectMapper objectMapper;
        private final static String excel2003L =".xls";    //2003- 版本的excel
        private final static String excel2007U =".xlsx";   //2007+ 版本的excel



        /***自定义导入****/
        @Override
        public ResponseData importExcel(IRequest requestContext,InputStream is, String fileName) throws Exception {
            ResponseData rd = new ResponseData();
            List<List<Object>> list = new ArrayList<List<Object>>();
// Create a new workbook
            Workbook workbook = null;
            String fileType = fileName.substring(fileName.lastIndexOf("."));

            if (excel2003L.equals(fileType)) {
                workbook = new HSSFWorkbook(is); //2003-
            } else if (excel2007U.equals(fileType)) {
                workbook = new XSSFWorkbook(is); //2007+
            } else {
                rd.setSuccess(false);
                rd.setMessage("导入文件格式不正确");
                return rd;
            }
            Sheet worksheet = null;
            Row row = null;
            Cell cell = null;
//遍历Excel中所有的sheet
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                worksheet = workbook.getSheetAt(i);
                if (worksheet == null) {
                    continue;
                }
//遍历当前sheet中的所有⾏行行
                for (int j = worksheet.getFirstRowNum(); j < worksheet.getLastRowNum() + 1; j++) {
                    row = worksheet.getRow(j);
// 跳过空⾏行行和标题⾏行行（第⼀一⾏行行）
                    if (row == null || row.getFirstCellNum() == j) {
                        continue;
                    }
//遍历所有的列列
                    List<Object> li = new ArrayList<Object>();
                    for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                        cell = row.getCell(y);


                        li.add(this.getCellValue(cell));
                    }
                    list.add(li);
                }
            }
            workbook.close();
// 从第⼆二⾏行行开始遍历
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0; i < list.size(); i++) {
                List<Object> excelRow = list.get(i);
                RentActivity dto = new RentActivity();
                dto.setEventName(excelRow.get(0).toString());
                dto.setManagingEmployees(excelRow.get(1).toString());
                dto.setActivities(excelRow.get(3).toString());

                dto.setActivityAmount(Double.parseDouble(excelRow.get(4).toString()));
                dto.setReleaseDate(simpleDateFormat.parse(excelRow.get(5).toString()));
                dto.setReleaseEndDate(simpleDateFormat.parse(excelRow.get(6).toString()));
                dto.setCreatedBy(requestContext.getUserId());
                dto.setCreationDate(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
                orgAccessMapper.insertSelective(dto);
            }
            rd.setSuccess(true);
            rd.setMessage("导入成功");
            return rd;
        }

        private Object getCellValue(Cell cell) {
            Object value = null;
            DecimalFormat df = new DecimalFormat("0"); //格式化number String字符
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); //⽇日期格式化
            DecimalFormat df2 = new DecimalFormat("0.00"); //格式化数字
            if (cell != null) {
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        value = cell.getRichStringCellValue().getString();
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                            value = df.format(cell.getNumericCellValue());
                        } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                            value = sdf.format(cell.getDateCellValue());
                        } else {
                            value = df2.format(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        value = "";
                        break;
                    default:
                        break;
                }
            } else {
                value = "";
            }
            return value;
        }





}