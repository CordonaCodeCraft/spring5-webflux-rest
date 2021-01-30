package guru.springframework.spring5webfluxrest.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Document
public class Vendor {

    private String id;
    private String firstName;
    private String lastName;
}
