package cn.wolfcode.mapper;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.PermissionQuery;

import java.util.List;

public interface PermissionMapper {
    void addPermission(Permission permission);
    void updatePermission(Permission permission);
    void deletePermission(Long id);
    Permission getPermission(Long id);
    List<Permission> selectAllPermission();
    List<Permission> selectPermissionByQuery(PermissionQuery query);
}