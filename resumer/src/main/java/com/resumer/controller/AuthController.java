package com.resumer.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.resumer.entity.req.user.MenuReq;
import com.resumer.entity.req.user.RoleMenuReq;
import com.resumer.entity.req.user.RoleReq;
import com.resumer.result.Result;
import com.resumer.service.MenuService;
import com.resumer.service.RoleMenuService;
import com.resumer.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description 权限管理
 * @auth yh
 * @date 2022/5/28
 */
@RestController
@RequestMapping(value = "/auth")
@Api(tags = "权限接口")
@ApiSupport(author = "鱼找水需要时间", order = 2)
@Validated
@AllArgsConstructor
public class AuthController {

    private final RoleService roleService;
    private final MenuService menuService;
    private final RoleMenuService roleMenuService;

    /**
     * 添加角色
     *
     * @param req 参数
     * @return Result
     * @author yh
     * @date 2022/5/28
     */
    @ApiOperation(value = "添加角色", notes = "添加系统角色")
    @PostMapping(value = "/addRole")
    public Result addRole(@Valid @RequestBody RoleReq req) {
        roleService.addRole(req);
        return Result.success();
    }

    /**
     * 添加菜单
     *
     * @param req 参数
     * @return Result
     * @author yh
     * @date 2022/5/29
     */
    @ApiOperation(value = "添加菜单", notes = "添加系统菜单")
    @PostMapping(value = "/addMenu")
    public Result addMenu(@Valid @RequestBody MenuReq req) {
        menuService.saveMenu(req);
        return Result.success();
    }

    /**
     * 添加角色权限
     *
     * @param req 参数
     * @return Result
     * @author yh
     * @date 2022/5/29
     */
    @ApiOperation(value = "添加角色权限", notes = "添加添加角色对应权限")
    @PostMapping(value = "/saveRoleMenu")
    public Result saveRoleMenu(@Valid RoleMenuReq req) {
        roleMenuService.saveRoleMenu(req);
        return Result.success();
    }
}