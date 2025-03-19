package co.com.bancolombia.api.dtos.search;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {
    @Pattern(regexp = "ASC|DESC", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String OrderBy;
    @NotNull
    private int Limit;
    @NotNull
    private int Offset;
}
