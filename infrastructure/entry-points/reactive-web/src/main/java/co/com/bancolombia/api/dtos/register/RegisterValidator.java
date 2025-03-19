package co.com.bancolombia.api.dtos.register;

import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class RegisterValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmpty(errors, "description", "description.empty");

    }
}
