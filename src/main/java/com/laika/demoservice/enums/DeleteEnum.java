package com.laika.demoservice.enums;

/**
 * @author: 会跳舞的机器人
 * @date: 17/12/16 上午11:49
 * @description: 是否删除枚举
 */
public enum DeleteEnum {
    IS_DELETED(1,"是"),
    NO_DELETE(0,"否")
    ;

    private Integer value;

    private String desc;

    DeleteEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * 根据value值获取FlagEnum
     *
     * @param value
     * @return
     */
    public static DeleteEnum getStatusByValue(String value) {
        for (DeleteEnum flagEnum : DeleteEnum.values()) {
            if (flagEnum.value.equals(value)) {
                return flagEnum;
            }
        }
        return null;
    }
}
