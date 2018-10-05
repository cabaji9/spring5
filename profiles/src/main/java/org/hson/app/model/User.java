package org.hson.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="USER_NAME")
    private String username;
    private String password;
    private Integer enabled;

}
