package com.wzml.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzml.mapper.ClazzMapper;
import com.wzml.pojo.Clazz;
import com.wzml.pojo.PageResult;
import com.wzml.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {
    @Override
    public PageResult page(Clazz clazz, Integer pageNum, Integer pageSize) {
        Page<Clazz> pageParam = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<Clazz> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(clazz.getName()), Clazz::getName, clazz.getName())
                .ge(clazz.getBeginDate() != null, Clazz::getBeginDate, clazz.getBeginDate())
                .le(clazz.getEndDate() != null, Clazz::getEndDate, clazz.getEndDate())
                .orderByDesc(Clazz::getBeginDate);

        Page<Clazz> clazzPage = baseMapper.selectPage(pageParam, queryWrapper);

        return new PageResult<>(clazzPage.getTotal(), clazzPage.getRecords());
    }


}
