package com.wzml.service;

import com.wzml.pojo.Emp;
import com.wzml.pojo.EmpQueryParam;
import com.wzml.pojo.LoginInfo;
import com.wzml.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     */
    PageResult page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);

}
