package cn.wolfcode.service;

import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.Student;
import cn.wolfcode.domain.StudentQuery;
import com.github.pagehelper.PageInfo;

public interface IStudentService {
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Long id);
    Student getStudent(Long id);
    PageInfo<Student> selectAllStudent(QueryObjet qo);
    PageInfo<Student> selectStudentByQuery(StudentQuery query);
    void saveOrUpdate(Student student);
}