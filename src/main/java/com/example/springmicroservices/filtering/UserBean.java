package com.example.springmicroservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
// if the field name changes, this will be out of sync
// @JsonIgnoreProperties(value={"password"})
@JsonFilter("userFilter")
public class UserBean {
    private String firstName;
    private String lastName;
    // @JsonIgnore - can ignore individual props using
    // com.fasterxml.jackson.annotation.JsonIgnore;
    private String password;
}
