package cn.wolfcode.service;

import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.domain.Schedule;
import cn.wolfcode.domain.ScheduleQuery;
import com.github.pagehelper.PageInfo;

public interface IScheduleService {
    void addSchedule(Schedule schedule);
    void updateSchedule(Schedule schedule);
    void deleteSchedule(Long id);
    Schedule getSchedule(Long id);
    PageInfo<Schedule> selectAllSchedule(QueryObjet qo);
    PageInfo<Schedule> selectScheduleByQuery(ScheduleQuery query);
    void saveOrUpdate(Schedule schedule);
}