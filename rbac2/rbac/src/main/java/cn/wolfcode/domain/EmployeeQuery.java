package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeQuery extends QueryObjet {
    private String name;
    private String departmentName;
    private String position;
}