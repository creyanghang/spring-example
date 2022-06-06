package com.resumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.resumer.entity.po.user.RoleMenu;
import com.resumer.entity.req.user.RoleMenuReq;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
public interface RoleMenuService extends IService<RoleMenu> {
    /**
     * 根据角色查询用户权限
     * @param roleId 角色id
     * @author yh
     * @date 2022/5/28
     * @return 菜单id
     */
    List<Long> queryMenuId(List<Long> roleId);

    /**
     * 添加菜单
     *
     * @param req 参数
     * @return Result
     * @author yh
     * @date 2022/5/29
     */
    void saveRoleMenu(RoleMenuReq req);
}
