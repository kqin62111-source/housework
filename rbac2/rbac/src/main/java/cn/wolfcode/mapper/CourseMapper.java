package cn.wolfcode.mapper;

import cn.wolfcode.domain.Course;
import cn.wolfcode.domain.CourseQuery;

import java.util.List;

public interface CourseMapper {
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Long id);
    Course getCourse(Long id);
    List<Course> selectAllCourse();
    List<Course> selectCourseByQuery(CourseQuery query);
}