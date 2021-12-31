package com.example.springmicroservices.user;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    private Integer id;
    private String name;
    private Date birthDate;
}
