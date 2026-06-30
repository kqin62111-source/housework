package cn.wolfcode.service;

import cn.wolfcode.domain.Clazz;
import cn.wolfcode.domain.ClazzQuery;
import cn.wolfcode.domain.QueryObjet;
import com.github.pagehelper.PageInfo;

public interface IClazzService {
    void addClazz(Clazz clazz);
    void updateClazz(Clazz clazz);
    void deleteClazz(Long id);
    Clazz getClazz(Long id);
    PageInfo<Clazz> selectAllClazz(QueryObjet qo);
    PageInfo<Clazz> selectClazzByQuery(ClazzQuery query);
    void saveOrUpdate(Clazz clazz);
}