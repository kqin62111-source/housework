package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.EmployeeQuery;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.mapper.ScheduleMapper;
import cn.wolfcode.service.IEmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        if (id != null) {
            // 先删除关联的课程安排记录
            scheduleMapper.deleteByTeacherId(id);
            // 最后删除员工
            employeeMapper.deleteEmployee(id);
        } else {
            throw new RuntimeException("禁止删除");
        }
    }

    @Override
    public Employee getEmployee(Long id) {
        return employeeMapper.getEmployee(id);
    }

    @Override
    public PageInfo<Employee> selectAllEmployee(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(employeeMapper.selectAllEmployee());
    }

    @Override
    public PageInfo<Employee> selectEmployeeByQuery(EmployeeQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(employeeMapper.selectEmployeeByQuery(query));
    }

    @Override
    public void saveOrUpdate(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("非法参数");
        }
        if (employee.getId() == null) {
            this.addEmployee(employee);
        } else {
            this.updateEmployee(employee);
        }
    }
}