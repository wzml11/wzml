package com.wzml.controller;

import com.wzml.pojo.Clazz;
import com.wzml.pojo.PageResult;
import com.wzml.pojo.Result;
import com.wzml.pojo.Student;
import com.wzml.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name,Integer degree,Integer clazzId
    ) {
        Student student=new Student();
        student.setName(name);
        student.setDegree(degree);
        student.setClazzId(clazzId);

        PageResult pageResult =studentService.page(student, page, pageSize);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        studentService.removeById(id);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Student student) {
        studentService.save(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student) {
        studentService.updateById(student);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id,@PathVariable Short score) {
        studentService.updateViolation(id,score);
        return Result.success();
    }
}
