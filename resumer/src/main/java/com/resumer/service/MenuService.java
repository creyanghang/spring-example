package com.resumer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.resumer.entity.po.user.Menu;
import com.resumer.entity.req.user.MenuReq;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
public interface MenuService extends IService<Menu> {

    /**
     * 根据用户id查询权限
     * @param userId 用户id
     * @param menuType 权限类型 auth:路由权限，web:web菜单
     * @author yh
     * @date 2022/5/28
     * @return 权限
     */
    List<Menu> queryMenuByUserId(Long userId, String menuType);


    /**
     * 添加系统菜单
     * @param req 参数
     * @author yh
     * @date 2022/5/29
     */
    void saveMenu(MenuReq req);
}
