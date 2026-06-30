package cn.wolfcode.mapper;

import cn.wolfcode.domain.StudentStatus;
import cn.wolfcode.domain.StudentStatusQuery;

import java.util.List;

public interface StudentStatusMapper {
    void addStudentStatus(StudentStatus status);
    void updateStudentStatus(StudentStatus status);
    void deleteStudentStatus(Long id);
    StudentStatus getStudentStatus(Long id);
    List<StudentStatus> selectAllStudentStatus();
    List<StudentStatus> selectStudentStatusByQuery(StudentStatusQuery query);
    void deleteByStudentId(Long studentId);
}