package com.laika.demoservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author: 会跳舞的机器人
 * @date: 17/12/19 下午7:16
 * @description: 枚举校验注解, 加上此注解后,字段的值不能为空并且只能是枚举类中存在的值,需要指定enums枚举类和message提示信息
 * eg:@EnumPattern(enums = SmsTypeEnum.class,message = "短信类型错误")
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {EnumPatternValidator.class})
public @interface EnumPattern {
    Class<?> enums();

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        EnumPattern[] value();
    }
}
