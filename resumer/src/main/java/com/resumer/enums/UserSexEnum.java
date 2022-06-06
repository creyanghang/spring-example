package com.resumer.enums;

/**
 * 用户角色枚举
 *
 * @author yh
 * @date 2022/5/28
 */
public enum UserSexEnum {

    //
    SEX_MALE(0, "男"),
    SEX_FEMALE(1, "女"),
    SEX_UNKNOWN(2, "未知");

    UserSexEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private Integer code;
    private String value;

    public Integer getCode() {
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
    public static String getByCode(Integer code) {
        for (UserSexEnum optionTypeEnum : values()) {
            if (optionTypeEnum.getCode().equals(code)) {
                return optionTypeEnum.getValue();
            }
        }
        return SEX_UNKNOWN.getValue();
    }

}
