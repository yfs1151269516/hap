//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.core.exception.UpdateFailedException;
import com.hand.hap.extensible.components.ServiceListenerChainFactory;
import com.hand.hap.mybatis.common.Criteria;
import com.hand.hap.mybatis.common.Mapper;
import com.hand.hap.system.dto.BaseDTO;
import com.hand.hap.system.service.IBaseService;
import java.util.Iterator;
import java.util.List;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
    @Autowired
    protected Mapper<T> mapper;
    @Autowired
    private ServiceListenerChainFactory chainFactory;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchDeleteForAudit(IRequest request,List<T> list) {
        IBaseService<T> self = ((IBaseService<T>) AopContext.currentProxy());
        int c = 0;
        for (T t : list) {
            c += self.deleteByPrimaryKey(t);
        }
        return c;
    }


    public BaseServiceImpl() {
    }

    @Transactional(
            propagation = Propagation.SUPPORTS
    )
    public List<T> select(IRequest request, T condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return this.mapper.select(condition);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public T insert(IRequest request, @StdWho T record) {
        record = (T) this.chainFactory.getChain(this).beforeInsert (request, record);
        this.mapper.insert(record);
        record = (T)this.chainFactory.getChain(this).afterInsert(request, record);
        return record;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public T insertSelective(IRequest request, @StdWho T record) {
        record = (T)this.chainFactory.getChain(this).beforeInsert(request, record);
        this.mapper.insertSelective(record);
        record = (T)this.chainFactory.getChain(this).afterInsert(request, record);
        return record;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public T updateByPrimaryKey(IRequest request, @StdWho T record) {
        record = (T)this.chainFactory.getChain(this).beforeUpdate(request, record);
        int ret = this.mapper.updateByPrimaryKey(record);
        this.checkOvn(ret, record);
        record = (T)this.chainFactory.getChain(this).afterUpdate(request, record);
        return record;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public T updateByPrimaryKeySelective(IRequest request, @StdWho T record) {
        record = (T)this.chainFactory.getChain(this).beforeUpdate(request, record);
        int ret = this.mapper.updateByPrimaryKeySelective(record);
        this.checkOvn(ret, record);
        record = (T)this.chainFactory.getChain(this).afterUpdate(request, record);
        return record;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public T updateByPrimaryKeyOptions(IRequest request, @StdWho T record, Criteria criteria) {
        record = (T)this.chainFactory.getChain(this).beforeUpdate(request, record);
        int ret = this.mapper.updateByPrimaryKeyOptions(record, criteria);
        this.checkOvn(ret, record);
        record = (T)this.chainFactory.getChain(this).afterUpdate(request, record);
        return record;
    }

    @Transactional(
            propagation = Propagation.SUPPORTS
    )
    public T selectByPrimaryKey(IRequest request, T record) {
        return this.mapper.selectByPrimaryKey(record);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public int deleteByPrimaryKey(T record) {
        record = (T)this.chainFactory.getChain(this).beforeDelete((IRequest)null, record);
        int ret = this.mapper.deleteByPrimaryKey(record);
        this.checkOvn(ret, record);
        record = (T)this.chainFactory.getChain(this).afterDelete((IRequest)null, record);
        return ret;
    }

    protected void checkOvn(int updateCount, Object record) {
        if(updateCount == 0 && record instanceof BaseDTO) {
            BaseDTO baseDTO = (BaseDTO)record;
            if(baseDTO.getObjectVersionNumber() != null) {
                throw new RuntimeException(new UpdateFailedException(baseDTO));
            }
        }

    }

    /** @deprecated */
    @Deprecated
    @Transactional(
            propagation = Propagation.SUPPORTS
    )
    public List<T> selectAll() {
        return this.mapper.selectAll();
    }

    @Transactional(
            propagation = Propagation.SUPPORTS
    )
    public List<T> selectAll(IRequest request) {
        return this.mapper.selectAll();
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public List<T> batchUpdate(IRequest request, @StdWho List<T> list) {
        IBaseService<T> self = (IBaseService)AopContext.currentProxy();
        Iterator var4 = list.iterator();

        while(var4.hasNext()) {
            T t = (T)var4.next();
            String var6 = ((BaseDTO)t).get__status();
            byte var7 = -1;
            switch(var6.hashCode()) {
                case -1335458389:
                    if(var6.equals("delete")) {
                        var7 = 2;
                    }
                    break;
                case -838846263:
                    if(var6.equals("update")) {
                        var7 = 1;
                    }
                    break;
                case 96417:
                    if(var6.equals("add")) {
                        var7 = 0;
                    }
            }

            switch(var7) {
                case 0:
                    self.insertSelective(request, t);
                    break;
                case 1:
                    if(this.useSelectiveUpdate()) {
                        self.updateByPrimaryKeySelective(request, t);
                    } else {
                        self.updateByPrimaryKey(request, t);
                    }
                    break;
                case 2:
                    self.deleteByPrimaryKey(t);
            }
        }

        return list;
    }

    protected boolean useSelectiveUpdate() {
        return true;
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public int batchDelete(List<T> list) {
        IBaseService<T> self = (IBaseService)AopContext.currentProxy();
        int c = 0;

        Object t;
        for(Iterator var4 = list.iterator(); var4.hasNext(); c += self.deleteByPrimaryKey((T)t)) {
            t = var4.next();
        }

        return c;
    }

    @Transactional(
            propagation = Propagation.SUPPORTS
    )
    public List<T> selectOptions(IRequest request, T record, Criteria criteria) {
        if(criteria == null) {
            criteria = new Criteria(record);
        }

        IBaseService<T> self = (IBaseService)AopContext.currentProxy();
        return self.selectOptions(request, record, criteria, (Integer)null, (Integer)null);
    }

    @Transactional(
            propagation = Propagation.SUPPORTS
    )
    public List<T> selectOptions(IRequest request, T record, Criteria criteria, Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum.intValue(), pageSize.intValue());
        }

        if(criteria == null) {
            criteria = new Criteria(record);
        }

        List<T> list = this.mapper.selectOptions(record, criteria);
        return list;
    }
}
