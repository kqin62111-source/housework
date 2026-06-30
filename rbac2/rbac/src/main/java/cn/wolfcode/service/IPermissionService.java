package cn.wolfcode.service;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.PermissionQuery;
import cn.wolfcode.domain.QueryObjet;
import com.github.pagehelper.PageInfo;

public interface IPermissionService {
    void addPermission(Permission permission);
    void updatePermission(Permission permission);
    void deletePermission(Long id);
    Permission getPermission(Long id);
    PageInfo<Permission> selectAllPermission(QueryObjet qo);
    PageInfo<Permission> selectPermissionByQuery(PermissionQuery query);
    void saveOrUpdate(Permission permission);
}