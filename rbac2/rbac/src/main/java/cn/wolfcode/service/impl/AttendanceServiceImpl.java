package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Attendance;
import cn.wolfcode.domain.AttendanceQuery;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.mapper.AttendanceMapper;
import cn.wolfcode.service.IAttendanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public void addAttendance(Attendance attendance) {
        attendanceMapper.addAttendance(attendance);
    }

    @Override
    public void updateAttendance(Attendance attendance) {
        attendanceMapper.updateAttendance(attendance);
    }

    @Override
    public void deleteAttendance(Long id) {
        if (id != null) {
            attendanceMapper.deleteAttendance(id);
        }
    }

    @Override
    public Attendance getAttendance(Long id) {
        return attendanceMapper.getAttendance(id);
    }

    @Override
    public PageInfo<Attendance> selectAllAttendance(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(attendanceMapper.selectAllAttendance());
    }

    @Override
    public PageInfo<Attendance> selectAttendanceByQuery(AttendanceQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(attendanceMapper.selectAttendanceByQuery(query));
    }

    @Override
    public void saveOrUpdate(Attendance attendance) {
        if (attendance == null) return;
        if (attendance.getId() == null) {
            this.addAttendance(attendance);
        } else {
            this.updateAttendance(attendance);
        }
    }
}