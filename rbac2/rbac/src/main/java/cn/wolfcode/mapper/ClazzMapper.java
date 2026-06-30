package cn.wolfcode.mapper;

import cn.wolfcode.domain.Clazz;
import cn.wolfcode.domain.ClazzQuery;

import java.util.List;

public interface ClazzMapper {
    void addClazz(Clazz clazz);
    void updateClazz(Clazz clazz);
    void deleteClazz(Long id);
    Clazz getClazz(Long id);
    List<Clazz> selectAllClazz();
    List<Clazz> selectClazzByQuery(ClazzQuery query);
    void deleteByGradeId(Long gradeId);
}