package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Grade;
import cn.wolfcode.domain.GradeQuery;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.mapper.ClazzMapper;
import cn.wolfcode.mapper.GradeMapper;
import cn.wolfcode.mapper.ScheduleMapper;
import cn.wolfcode.service.IGradeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements IGradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public void addGrade(Grade grade) {
        gradeMapper.addGrade(grade);
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeMapper.updateGrade(grade);
    }

    @Override
    public void deleteGrade(Long id) {
        if (id != null) {
            // 先删除关联的课程安排记录
            scheduleMapper.deleteByGradeId(id);
            // 先删除关联的班级记录
            clazzMapper.deleteByGradeId(id);
            // 最后删除年级
            gradeMapper.deleteGrade(id);
        } else {
            throw new RuntimeException("禁止删除");
        }
    }

    @Override
    public Grade getGrade(Long id) {
        return gradeMapper.getGrade(id);
    }

    @Override
    public PageInfo<Grade> selectAllGrade(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(gradeMapper.selectAllGrade());
    }

    @Override
    public PageInfo<Grade> selectGradeByQuery(GradeQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(gradeMapper.selectGradeByQuery(query));
    }

    @Override
    public void saveOrUpdate(Grade grade) {
        if (grade == null) {
            throw new RuntimeException("非法参数");
        }
        if (grade.getId() == null) {
            this.addGrade(grade);
        } else {
            this.updateGrade(grade);
        }
    }
}