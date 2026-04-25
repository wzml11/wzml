package com.wzml.service.impl;

import com.wzml.mapper.EmpMapper;
import com.wzml.mapper.StudentMapper;
import com.wzml.pojo.ClazzOption;
import com.wzml.pojo.JobOption;
import com.wzml.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map> getStudentDegreeData(){
        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzOption getStudentCountData(){
        List<Map> list = studentMapper.countStudentByClazz();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("clazzName")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("stuCount")).toList();
        return new ClazzOption(clazzList, dataList);
    }
}
