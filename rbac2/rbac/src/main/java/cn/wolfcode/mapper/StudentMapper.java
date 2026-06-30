package cn.wolfcode.mapper;

import cn.wolfcode.domain.Student;
import cn.wolfcode.domain.StudentQuery;

import java.util.List;

public interface StudentMapper {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long id);
    Student getStudent(Long id);
    List<Student> selectAllStudent();
    List<Student> selectStudentByQuery(StudentQuery query);
}