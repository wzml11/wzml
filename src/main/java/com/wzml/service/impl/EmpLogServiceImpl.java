package com.wzml.service.impl;

import com.wzml.mapper.EmpLogMapper;
import com.wzml.pojo.EmpLog;
import com.wzml.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    //@Transactional(propagation = Propagation.REQUIRES_NEW)是保证这个B事务被其他A事务调用的时候无论A事务是否成功都执行B事务
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}