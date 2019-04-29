package com.hand.activity.mapper;

import com.hand.hap.mybatis.common.Mapper;
import com.hand.activity.dto.File;

import java.util.List;

public interface FileMapper extends Mapper<File>{

    List<File> selectAllFile(File file);

}