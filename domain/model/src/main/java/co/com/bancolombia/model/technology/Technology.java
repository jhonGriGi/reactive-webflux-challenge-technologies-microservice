package co.com.bancolombia.model.technology;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Technology {
    private UUID id;
    private String name;
    private String description;
}
