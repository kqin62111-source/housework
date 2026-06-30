package cn.wolfcode.service;

import cn.wolfcode.domain.Employee;
import cn.wolfcode.domain.EmployeeQuery;
import cn.wolfcode.domain.QueryObjet;
import com.github.pagehelper.PageInfo;

public interface IEmployeeService {
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Long id);
    Employee getEmployee(Long id);
    PageInfo<Employee> selectAllEmployee(QueryObjet qo);
    PageInfo<Employee> selectEmployeeByQuery(EmployeeQuery query);
    void saveOrUpdate(Employee employee);
}