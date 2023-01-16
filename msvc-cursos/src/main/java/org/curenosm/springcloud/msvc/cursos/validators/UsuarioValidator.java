package org.curenosm.springcloud.msvc.cursos.validators;

import org.curenosm.springcloud.msvc.cursos.model.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Usuario.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "nombre", "user.name.missing");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.missing");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.missing");
        Usuario u = (Usuario) target;
        if (u.getPassword().length() < 6) {
            errors.rejectValue("password", "user.password.too.short");
        }

    }
}
