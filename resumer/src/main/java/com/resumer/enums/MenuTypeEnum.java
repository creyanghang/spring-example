package com.resumer.enums;

/**
 * @description 菜单类型 auth:路由权限，web:web菜单
 * @auth yh
 * @date 2022/5/28
 */
public enum MenuTypeEnum {

    //
    MENU_AUTH("auth", "路由权限"),
    MENU_WEB("web", "web菜单");

    MenuTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    /**
     * 根据code返回枚举类型,主要在switch中使用
     * @param code 枚举code
     * @author yh
     * @date 2022/5/28
     * @return value
     */
    public static String getByCode(String code) {
        for (MenuTypeEnum optionTypeEnum : values()) {
            if (optionTypeEnum.getCode().equals(code)) {
                return optionTypeEnum.getValue();
            }
        }
        return "";
    }
}