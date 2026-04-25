package com.wzml.controller;
import com.wzml.pojo.Dept;
import com.wzml.pojo.Result;
import com.wzml.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/depts")
@RestController
public class DeptController
{
    @Autowired
    private DeptService deptService;
    @GetMapping
    public Result list(){

        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping
    public Result delete(Integer id){

        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Dept dept){

        deptService.save(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){

        Dept dept= deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){

        deptService.update(dept);
        return Result.success();
    }

}