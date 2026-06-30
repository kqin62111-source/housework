package cn.wolfcode.service;

import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.StudentStatus;
import cn.wolfcode.domain.StudentStatusQuery;
import com.github.pagehelper.PageInfo;

public interface IStudentStatusService {
    void addStudentStatus(StudentStatus status);
    void updateStudentStatus(StudentStatus status);
    void deleteStudentStatus(Long id);
    StudentStatus getStudentStatus(Long id);
    PageInfo<StudentStatus> selectAllStudentStatus(QueryObjet qo);
    PageInfo<StudentStatus> selectStudentStatusByQuery(StudentStatusQuery query);
    void saveOrUpdate(StudentStatus status);
}