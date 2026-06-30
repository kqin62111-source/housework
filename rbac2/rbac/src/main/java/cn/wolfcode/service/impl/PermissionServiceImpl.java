package cn.wolfcode.service.impl;

import cn.wolfcode.domain.Permission;
import cn.wolfcode.domain.PermissionQuery;
import cn.wolfcode.domain.QueryObjet;
import cn.wolfcode.mapper.PermissionMapper;
import cn.wolfcode.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public void addPermission(Permission permission) {
        permissionMapper.addPermission(permission);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionMapper.updatePermission(permission);
    }

    @Override
    public void deletePermission(Long id) {
        if (id != null) {
            permissionMapper.deletePermission(id);
        }
    }

    @Override
    public Permission getPermission(Long id) {
        return permissionMapper.getPermission(id);
    }

    @Override
    public PageInfo<Permission> selectAllPermission(QueryObjet qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        return new PageInfo<>(permissionMapper.selectAllPermission());
    }

    @Override
    public PageInfo<Permission> selectPermissionByQuery(PermissionQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        return new PageInfo<>(permissionMapper.selectPermissionByQuery(query));
    }

    @Override
    public void saveOrUpdate(Permission permission) {
        if (permission == null) return;
        if (permission.getId() == null) {
            this.addPermission(permission);
        } else {
            this.updatePermission(permission);
        }
    }
}