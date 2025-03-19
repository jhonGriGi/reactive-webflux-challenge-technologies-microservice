package co.com.bancolombia.r2dbc.technologies;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "technologies", schema = "public")
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyEntity {
    @Id
    @Column("id")
    private UUID id;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
}
