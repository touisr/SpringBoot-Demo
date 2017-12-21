package com.laika.demoservice.validator;

import com.laika.demoservice.enums.EnumInterface;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

/**
 * @author: 会跳舞的机器人
 * @date: 17/12/19 下午7:28
 * @description: EnumPattern枚举校验注解实际的校验器
 */
public class EnumPatternValidator implements ConstraintValidator<EnumPattern, CharSequence> {
    private Class<?> clazz;

    @Override
    public void initialize(EnumPattern parameters) {
        clazz = parameters.enums();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        Method method = null;
        try {
            // invoke调用枚举类的values()方法获取到所有的枚举值
            method = clazz.getMethod("values");
            EnumInterface enumInterfaces[] = (EnumInterface[]) method.invoke(null, null);
            for (EnumInterface enumInterface : enumInterfaces) {
                if (value.toString().equals(enumInterface.getValue())) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
