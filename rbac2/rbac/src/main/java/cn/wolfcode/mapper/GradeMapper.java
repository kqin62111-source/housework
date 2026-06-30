package cn.wolfcode.mapper;

import cn.wolfcode.domain.Grade;
import cn.wolfcode.domain.GradeQuery;

import java.util.List;

public interface GradeMapper {
    void addGrade(Grade grade);
    void updateGrade(Grade grade);
    void deleteGrade(Long id);
    Grade getGrade(Long id);
    List<Grade> selectAllGrade();
    List<Grade> selectGradeByQuery(GradeQuery query);
}