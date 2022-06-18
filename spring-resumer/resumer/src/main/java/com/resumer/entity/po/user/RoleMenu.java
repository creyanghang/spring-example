package com.resumer.entity.po.user;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleMenu extends Model<RoleMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单id
     */
    private Long menuId;


    @Override
    public Serializable pkVal() {
        return this.roleId;
    }

}
