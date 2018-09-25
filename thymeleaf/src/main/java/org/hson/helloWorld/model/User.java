package org.hson.helloWorld.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {

    @NotNull
    @Size(min = 2,message = "Size 2 is required as minimum")
    private String firstName;
    @NotBlank
    private String lastName;
}
