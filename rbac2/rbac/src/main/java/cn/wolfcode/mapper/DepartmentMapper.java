package cn.wolfcode.mapper;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.DepartmentQuery;

import java.util.List;

public interface DepartmentMapper {
    void addDepartment(Department department);
    void updateDepartment(Department department);
    void deleteDepartment(Long id);
    Department getDepartment(Long id);
    List<Department> selectAllDepartment();
    List<Department> selectDepartmentByQuery(DepartmentQuery query);
}
