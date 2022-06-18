package com.resumer.entity.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.ASSIGN_ID)
    private Long menuId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 删除标识：0、否 1、是
     */
    private Boolean deleted;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单名类型 auth:路由权限，web:web菜单
     */
    private String menuType;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 资源父节点Id,0为没有父节点
     */
    private Long parentId;

    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序, 越小越靠上，正序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String description;


    @Override
    public Serializable pkVal() {
        return this.menuId;
    }

}
