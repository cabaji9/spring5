package org.hson.app.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Hyun Woo Son on 9/26/18
 **/
@Data
@Entity
@Table(name = "USER_AUTHORITIES")
public class UserAuthorities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String authority;

    @ManyToOne
    @JoinColumn(name="USER_NAME",referencedColumnName = "USER_NAME")
    private User user;

}
