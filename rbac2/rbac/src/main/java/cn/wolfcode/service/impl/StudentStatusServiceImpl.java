package cn.wolfcode.service.impl;

import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.StudentStatus;
import cn.wolfcode.domain.StudentStatusQuery;
import cn.wolfcode.mapper.StudentStatusMapper;
import cn.wolfcode.service.IStudentStatusService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentStatusServiceImpl implements IStudentStatusService {

    @Autowired
    private StudentStatusMapper studentStatusMapper;

    @Override
    public void addStudentStatus(StudentStatus status) {
        studentStatusMapper.addStudentStatus(status);
    }

    @Override
    public void updateStudentStatus(StudentStatus status) {
        studentStatusMapper.updateStudentStatus(status);
    }

    @Override
    public void deleteStudentStatus(Long id) {
        if (id != null) {
            studentStatusMapper.deleteStudentStatus(id);
        }
    }

    @Override
    public StudentStatus getStudentStatus(Long id) {
        return studentStatusMapper.getStudentStatus(id);
    }

    @Override
    public PageInfo<StudentStatus> selectAllStudentStatus(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(studentStatusMapper.selectAllStudentStatus());
    }

    @Override
    public PageInfo<StudentStatus> selectStudentStatusByQuery(StudentStatusQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(studentStatusMapper.selectStudentStatusByQuery(query));
    }

    @Override
    public void saveOrUpdate(StudentStatus status) {
        if (status == null) return;
        if (status.getId() == null) {
            this.addStudentStatus(status);
        } else {
            this.updateStudentStatus(status);
        }
    }
}