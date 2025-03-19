package co.com.bancolombia.api.dtos.register;

import co.com.bancolombia.model.technology.Technology;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Getter
@Setter
public class RegisterDTO {
    @NotNull
    @Size(min = 1, max = 50, message = "El parametro 'name', debe tener entre 1 y 50 caracteres")
    private String name;
    @NotNull
    @Size(min = 10, max = 90, message = "El parametro 'description' debe tener entre 10 y 90 caracteres")
    private String description;

    public Technology toModel() {
        return Technology.builder().name(name).description(description).build();
    }
}
