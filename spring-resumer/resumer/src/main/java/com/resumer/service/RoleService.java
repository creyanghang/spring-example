package com.resumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.resumer.entity.po.user.Role;
import com.resumer.entity.req.user.RoleReq;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
public interface RoleService extends IService<Role> {

    /**
     * 添加角色
     * @param req 参数
     * @author yh
     * @date 2022/5/28
     */
    void addRole(RoleReq req);

    /**
     * 根据用户id查询角色
     * @param userId 用户id
     * @author yh
     * @date 2022/5/29
     * @return List<Role>
     */
    List<Role> queryRoleByUserId(Long userId);
}
