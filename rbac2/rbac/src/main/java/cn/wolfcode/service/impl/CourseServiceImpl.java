package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.CourseQuery;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.mapper.CourseMapper;
import cn.wolfcode.mapper.ScheduleMapper;
import cn.wolfcode.mapper.ScoreMapper;
import cn.wolfcode.service.ICourseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public void addCourse(Course course) {
        courseMapper.addCourse(course);
    }

    @Override
    public void updateCourse(Course course) {
        courseMapper.updateCourse(course);
    }

    @Override
    public void deleteCourse(Long id) {
        if (id != null) {
            // 先删除关联的成绩记录
            scoreMapper.deleteByCourseId(id);
            // 先删除关联的课程安排记录
            scheduleMapper.deleteByCourseId(id);
            // 最后删除课程
            courseMapper.deleteCourse(id);
        } else {
            throw new RuntimeException("禁止删除");
        }
    }

    @Override
    public Course getCourse(Long id) {
        return courseMapper.getCourse(id);
    }

    @Override
    public PageInfo<Course> selectAllCourse(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(courseMapper.selectAllCourse());
    }

    @Override
    public PageInfo<Course> selectCourseByQuery(CourseQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(courseMapper.selectCourseByQuery(query));
    }

    @Override
    public void saveOrUpdate(Course course) {
        if (course == null) {
            throw new RuntimeException("非法参数");
        }
        if (course.getId() == null) {
            this.addCourse(course);
        } else {
            this.updateCourse(course);
        }
    }
}