package org.curenosm.springcloud.msvc.cursos.converters.formatters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

//@Component
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

//    @Autowired
//    private Foo aDependency;

    @Override
    public boolean isValid(Object t, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}
