package com.laika.demoservice.enums;

/**
 * @author: 会跳舞的机器人
 * @date: 17/12/19 下午7:48
 * @description: 枚举接口, 需要进行@EnumPattern参数校验的枚举需要实现此接口
 */
public interface EnumInterface {
    String getValue();

    String getDesc();
}
