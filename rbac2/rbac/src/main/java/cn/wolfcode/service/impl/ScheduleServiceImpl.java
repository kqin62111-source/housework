package cn.wolfcode.service.impl;

import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.Schedule;
import cn.wolfcode.domain.ScheduleQuery;
import cn.wolfcode.mapper.ScheduleMapper;
import cn.wolfcode.service.IScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public void addSchedule(Schedule schedule) {
        scheduleMapper.addSchedule(schedule);
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        scheduleMapper.updateSchedule(schedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        if (id != null) {
            scheduleMapper.deleteSchedule(id);
        }
    }

    @Override
    public Schedule getSchedule(Long id) {
        return scheduleMapper.getSchedule(id);
    }

    @Override
    public PageInfo<Schedule> selectAllSchedule(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(scheduleMapper.selectAllSchedule());
    }

    @Override
    public PageInfo<Schedule> selectScheduleByQuery(ScheduleQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(scheduleMapper.selectScheduleByQuery(query));
    }

    @Override
    public void saveOrUpdate(Schedule schedule) {
        if (schedule == null) return;
        if (schedule.getId() == null) {
            this.addSchedule(schedule);
        } else {
            this.updateSchedule(schedule);
        }
    }
}