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
 * 角色表
 * </p>
 *
 * @author yh
 * @since 2022-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private Long roleId;

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
     * 角色名称
     */
    private String name;

    /**
     * user,admin
     */
    private String roleKey;

    /**
     * 角色状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;


    @Override
    public Serializable pkVal() {
        return this.roleId;
    }

}
