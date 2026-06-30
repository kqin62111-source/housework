package cn.wolfcode.mapper;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.EmployeeQuery;

import java.util.List;

public interface EmployeeMapper {
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    Employee getEmployee(Long id);
    List<Employee> selectAllEmployee();
    List<Employee> selectEmployeeByQuery(EmployeeQuery query);
    void deleteByDepartmentId(Long departmentId);
}