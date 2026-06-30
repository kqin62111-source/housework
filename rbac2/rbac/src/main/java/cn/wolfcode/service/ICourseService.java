package cn.wolfcode.service;

import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.CourseQuery;
import cn.wolfcode.domain.QueryObjet;
import com.github.pagehelper.PageInfo;

public interface ICourseService {
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Long id);
    Course getCourse(Long id);
    PageInfo<Course> selectAllCourse(QueryObjet qo);
    PageInfo<Course> selectCourseByQuery(CourseQuery query);
    void saveOrUpdate(Course course);
}