package com.wzml.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzml.pojo.Clazz;
import com.wzml.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService extends IService<Clazz> {


    PageResult page(Clazz clazz, Integer page, Integer pageSize);

}
