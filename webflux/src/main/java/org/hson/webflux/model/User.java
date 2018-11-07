package org.hson.webflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document
public class User {

    @Id
    @NotNull
    private Integer id;

    @NotNull
    private String name;

}
