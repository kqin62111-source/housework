package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Clazz;
import cn.wolfcode.domain.ClazzQuery;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.mapper.ClazzMapper;
import cn.wolfcode.mapper.ScheduleMapper;
import cn.wolfcode.service.IClazzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClazzServiceImpl implements IClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public void addClazz(Clazz clazz) {
        clazzMapper.addClazz(clazz);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public void deleteClazz(Long id) {
        if (id != null) {
            // 先删除关联的课程安排记录
            scheduleMapper.deleteByClassId(id);
            // 最后删除班级
            clazzMapper.deleteClazz(id);
        } else {
            throw new RuntimeException("禁止删除");
        }
    }

    @Override
    public Clazz getClazz(Long id) {
        return clazzMapper.getClazz(id);
    }

    @Override
    public PageInfo<Clazz> selectAllClazz(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(clazzMapper.selectAllClazz());
    }

    @Override
    public PageInfo<Clazz> selectClazzByQuery(ClazzQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(clazzMapper.selectClazzByQuery(query));
    }

    @Override
    public void saveOrUpdate(Clazz clazz) {
        if (clazz == null) {
            throw new RuntimeException("非法参数");
        }
        if (clazz.getId() == null) {
            this.addClazz(clazz);
        } else {
            this.updateClazz(clazz);
        }
    }
}