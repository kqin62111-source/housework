package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionQuery extends QueryObjet {
    private String name;
    private String sn;
    private String url;
}