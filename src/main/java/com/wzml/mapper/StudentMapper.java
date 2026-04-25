package com.wzml.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzml.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Select("select (case degree " +
            "when 1 then '初中' " +
            "when 2 then '高中' " +
            "when 3 then '大专' " +
            "when 4 then '本科' " +
            "when 5 then '硕士' " +
            "when 6 then '博士' " +
            "else '其他' end) as name, " +
            "count(*) as value " +
            "from student group by degree order by degree")
    List<Map> countStudentDegreeData();

    @Select("select c.name as clazzName, count(s.id) as stuCount " +
            "from clazz c left join student s on c.id = s.clazz_id " +
            "group by c.id, c.name " +
            "order by c.id")
    List<Map> countStudentByClazz();
}
