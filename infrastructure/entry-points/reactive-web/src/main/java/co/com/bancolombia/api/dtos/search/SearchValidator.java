package co.com.bancolombia.api.dtos.search;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SearchValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return SearchDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "orderBy", "orderBy.empty");
        SearchDTO searchDTO = (SearchDTO) target;

        if (!searchDTO.getOrderBy().equals("ASC") && !searchDTO.getOrderBy().equals("DESC")) {
            errors.rejectValue("orderBy", "orderBy.invalid");
        }
    }
}
