package com.wzml.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzml.pojo.PageResult;
import com.wzml.pojo.Student;

public interface StudentService extends IService<Student> {
    PageResult page(Student student, Integer page, Integer pageSize);

    void updateViolation(Integer id, Short score);

}
