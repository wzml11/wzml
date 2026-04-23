package com.wzml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzml.mapper.StudentMapper;
import com.wzml.pojo.Clazz;
import com.wzml.pojo.PageResult;
import com.wzml.pojo.Student;
import com.wzml.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Override
    public PageResult page(Student student, Integer pageNum, Integer pageSize) {
        Page<Student> pageParam = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(student.getName()), Student::getName, student.getName())
                .ge(student.getDegree() != null, Student::getDegree, student.getDegree())
                .le(student.getClazzId() != null, Student::getClazzId, student.getClazzId())
                .orderByDesc(Student::getDegree);

        Page<Student> studentPage = baseMapper.selectPage(pageParam, queryWrapper);

        return new PageResult<>(studentPage.getTotal(), studentPage.getRecords());
    }

    @Override
    public void updateViolation(Integer id, Short score){
        Student student=this.getById(id);
        Short violationScore=student.getViolationScore();
        student.setViolationScore((short) (violationScore+score));
        student.setViolationCount((short) (student.getViolationCount()+1));
        this.updateById(student);

    }
}
