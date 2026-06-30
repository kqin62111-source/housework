package cn.wolfcode.service;

import cn.wolfcode.domain.Grade;
import cn.wolfcode.domain.GradeQuery;
import cn.wolfcode.domain.QueryObjet;
import com.github.pagehelper.PageInfo;

public interface IGradeService {
    void addGrade(Grade grade);
    void updateGrade(Grade grade);
    void deleteGrade(Long id);
    Grade getGrade(Long id);
    PageInfo<Grade> selectAllGrade(QueryObjet qo);
    PageInfo<Grade> selectGradeByQuery(GradeQuery query);
    void saveOrUpdate(Grade grade);
}