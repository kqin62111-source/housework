package cn.wolfcode.service;


import cn.wolfcode.domain.Department;
import cn.wolfcode.mapper.DepartmentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DepartmentServiceTest {
    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void testAddDepartment() {
        Department department =new Department();
        department.setName("开发部8888");
        department.setSn("CS28888");
        departmentService.addDepartment(department);
    }
    @Test
    public void testUpdateDepartment(){
        Department department =new Department();
        department.setId(7L);
        department.setName("测试4444");
        department.setSn("cs4444");
        departmentService.updateDepartment(department);

    }
    @Test
    public void testDeleteDepartment(){
        Department department =new Department();
        departmentService.deleteDepartment(6L);
    }
    @Test
    public void getDepartment(){
        Department department =departmentService.getDepartment(4L);
        System.out.println(department);

    }
//    @Test
//    public void selectAllDepartment(){
//        List<Department> departmentList =departmentService.selectAllDepartment();
//        for(Department department:departmentList){
//            System.out.println(department);
//        }
//
//    }
}