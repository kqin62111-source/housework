package cn.wolfcode.mapper;

import cn.wolfcode.domain.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DepartmentMapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void testAddDepartment() {
        Department department =new Department();
        department.setName("开发部8888");
        department.setSn("CS28888");
        departmentMapper.addDepartment(department);
    }
    @Test
    public void testUpdateDepartment(){
        Department department =new Department();
        department.setId(7L);
        department.setName("测试4444");
        department.setSn("cs4444");
        departmentMapper.updateDepartment(department);

    }
    @Test
    public void testDeleteDepartment(){
        Department department =new Department();
        departmentMapper.deleteDepartment(7L);
    }
   @Test
    public void getDepartment(){
      Department department =departmentMapper.getDepartment(4L);
        System.out.println(department);

    }
    @Test
    public void selectAllDepartment(){
        List<Department>departmentList =departmentMapper.selectAllDepartment();
        for(Department department:departmentList){
            System.out.println(department);
        }

    }
}

