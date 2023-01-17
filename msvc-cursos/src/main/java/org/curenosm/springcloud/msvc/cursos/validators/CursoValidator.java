package org.curenosm.springcloud.msvc.cursos.validators;

import org.curenosm.springcloud.msvc.cursos.model.Usuario;
import org.curenosm.springcloud.msvc.cursos.model.entities.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CursoValidator implements Validator {

    private final Validator usuarioValidator;

    @Autowired
    public CursoValidator(Validator usuarioValidator) {
        if (usuarioValidator == null)
            throw new IllegalArgumentException("The supplied [Validator] is required and must not be null.");

        if (!usuarioValidator.supports(Usuario.class))
            throw new IllegalArgumentException("The supplied [Validator] must support the validation of [Usuario] instances");

        this.usuarioValidator = usuarioValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
//        return Curso.class.equals(clazz);
        return Curso.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Curso course = (Curso) target;
        if (course.getUsuarios().size() > 100000) {
            errors.rejectValue("age", "maxValueExceeded");
        }

        try {
            errors.pushNestedPath("usuarios");

            for (Usuario user : course.getUsuarios()) {
                ValidationUtils.invokeValidator(this.usuarioValidator, user, errors);
            }

        } finally {
            errors.popNestedPath();
        }
    }
}
