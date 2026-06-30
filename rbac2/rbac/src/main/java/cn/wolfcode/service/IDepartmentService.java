package cn.wolfcode.service;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.DepartmentQuery;
import cn.wolfcode.domain.QueryObjet;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    void addDepartment(Department department);
    void updateDepartment(Department department);
    void deleteDepartment(Long id);
    Department getDepartment(Long id);
   PageInfo<Department> selectAllDepartment(QueryObjet qo);
   PageInfo<Department> selectDepartmentByQuery(DepartmentQuery query);

    void saveOrUpdate(Department department);
}
