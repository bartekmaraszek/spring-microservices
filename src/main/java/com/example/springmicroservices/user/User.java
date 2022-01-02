package com.example.springmicroservices.user;

import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private Integer id;
    // See: Java Validation API - JSR 380
    // Hibernate Validator is the most popular implementation
    @Size(min=2, max=128)
    private String name;
    @Past
    private Date birthDate;
}
