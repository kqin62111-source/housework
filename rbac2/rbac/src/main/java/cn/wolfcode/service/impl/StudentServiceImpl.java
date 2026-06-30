package cn.wolfcode.service.impl;

import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.Student;
import cn.wolfcode.domain.StudentQuery;
import cn.wolfcode.mapper.AttendanceMapper;
import cn.wolfcode.mapper.ScoreMapper;
import cn.wolfcode.mapper.StudentMapper;
import cn.wolfcode.mapper.StudentStatusMapper;
import cn.wolfcode.service.IStudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Autowired
    private StudentStatusMapper studentStatusMapper;

    @Override
    public void addStudent(Student student) {
        studentMapper.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentMapper.updateStudent(student);
    }

    @Override
    public void deleteStudent(Long id) {
        if (id != null) {
            // 先删除关联的考勤记录
            attendanceMapper.deleteByStudentId(id);
            // 先删除关联的成绩记录
            scoreMapper.deleteByStudentId(id);
            // 先删除关联的学籍状态记录
            studentStatusMapper.deleteByStudentId(id);
            // 最后删除学生
            studentMapper.deleteStudent(id);
        } else {
            throw new RuntimeException("禁止删除");
        }
    }

    @Override
    public Student getStudent(Long id) {
        return studentMapper.getStudent(id);
    }

    @Override
    public PageInfo<Student> selectAllStudent(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(studentMapper.selectAllStudent());
    }

    @Override
    public PageInfo<Student> selectStudentByQuery(StudentQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(studentMapper.selectStudentByQuery(query));
    }

    @Override
    public void saveOrUpdate(Student student) {
        if (student == null) {
            throw new RuntimeException("非法参数");
        }
        if (student.getId() == null) {
            this.addStudent(student);
        } else {
            this.updateStudent(student);
        }
    }
}