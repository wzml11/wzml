package com.wzml.service;

import com.wzml.pojo.ClazzOption;
import com.wzml.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption getEmpJobData();

    List<Map> getEmpGenderData();

    List<Map> getStudentDegreeData();

    ClazzOption getStudentCountData();

}
