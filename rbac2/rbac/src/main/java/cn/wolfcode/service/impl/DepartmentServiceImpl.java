package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Department;
import cn.wolfcode.domain.DepartmentQuery;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.mapper.DepartmentMapper;
import cn.wolfcode.mapper.EmployeeMapper;
import cn.wolfcode.mapper.ScheduleMapper;
import cn.wolfcode.service.IDepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
    public class DepartmentServiceImpl implements IDepartmentService {
@Autowired
   private DepartmentMapper departmentMapper;

@Autowired
   private EmployeeMapper employeeMapper;

@Autowired
   private ScheduleMapper scheduleMapper;


    @Override
     public void addDepartment(Department department) {
        departmentMapper.addDepartment(department);
    }

    @Override
      public void updateDepartment(Department department) {
        departmentMapper.updateDepartment(department);


    }

    @Override
      public void deleteDepartment(Long id) {
        if (id != null) {
            // 先删除关联的课程安排记录（通过教师）
            scheduleMapper.deleteByDepartmentId(id);
            // 先删除关联的员工记录
            employeeMapper.deleteByDepartmentId(id);
            // 最后删除部门
            departmentMapper.deleteDepartment(id);
        } else {
            throw new RuntimeException("禁止删除");
        }
    }

    @Override
    public Department getDepartment(Long id) {
        return departmentMapper.getDepartment(id);

    }

    @Override
    public PageInfo<Department> selectAllDepartment(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        return  new PageInfo<Department>(departmentMapper.selectAllDepartment());
    }

    @Override
    public PageInfo<Department> selectDepartmentByQuery(DepartmentQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(departmentMapper.selectDepartmentByQuery(query));
    }

    @Override
    public void saveOrUpdate(Department department) {
        if(department==null){
            throw new RuntimeException("非法参数");
        }
        if(department.getId()==null)
        {
            this.addDepartment(department);
        } else {
            this.updateDepartment(department);
        }
    }
}
