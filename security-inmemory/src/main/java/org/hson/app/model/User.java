package org.hson.app.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;

}
