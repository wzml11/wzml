package com.wzml.controller;

import com.wzml.pojo.Clazz;
import com.wzml.pojo.PageResult;
import com.wzml.pojo.Result;
import com.wzml.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;

@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        clazzService.save(clazz);
        return Result.success();
    }

    //用mybatisplus实现分页查询

    @GetMapping
    public Result page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ) {
        Clazz clazz = new Clazz();
        clazz.setName(name);
        clazz.setBeginDate(begin);
        clazz.setEndDate(end);

        PageResult pageResult = clazzService.page(clazz, page, pageSize);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        clazzService.removeById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        clazzService.updateById(clazz);
        return Result.success();
    }

}