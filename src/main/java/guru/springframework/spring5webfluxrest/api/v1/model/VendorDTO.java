package guru.springframework.spring5webfluxrest.api.v1.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VendorDTO {

    private String firstName;
    private String lastName;
}
