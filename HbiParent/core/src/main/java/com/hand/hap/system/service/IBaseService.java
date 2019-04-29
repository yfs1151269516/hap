//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hand.hap.system.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.annotation.StdWho;
import com.hand.hap.mybatis.common.Criteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IBaseService<T> {

    int batchDeleteForAudit(IRequest request,List<T> list);

    List<T> select(IRequest var1, T var2, int var3, int var4);



    T insert(IRequest var1, @StdWho T var2);

    T insertSelective(IRequest var1, @StdWho T var2);

    T updateByPrimaryKey(IRequest var1, @StdWho T var2);

    @Transactional(
            rollbackFor = {Exception.class}
    )
    T updateByPrimaryKeySelective(IRequest var1, @StdWho T var2);

    @Transactional(
            rollbackFor = {Exception.class}
    )
    T updateByPrimaryKeyOptions(IRequest var1, @StdWho T var2, Criteria var3);

    T selectByPrimaryKey(IRequest var1, T var2);

    int deleteByPrimaryKey(T var1);

    /** @deprecated */
    @Deprecated
    List<T> selectAll();

    List<T> selectAll(IRequest var1);

    List<T> batchUpdate(IRequest var1, @StdWho List<T> var2);

    int batchDelete(List<T> var1);

    List<T> selectOptions(IRequest var1, T var2, Criteria var3);

    List<T> selectOptions(IRequest var1, T var2, Criteria var3, Integer var4, Integer var5);
}
