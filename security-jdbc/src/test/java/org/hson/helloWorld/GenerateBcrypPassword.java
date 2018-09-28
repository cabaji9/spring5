package org.hson.helloWorld;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Hyun Woo Son on 9/26/18
 **/

@Slf4j
public class GenerateBcrypPassword {


    @Test
    public void generatePassword(){

        String password = encodePassword("lala");

        log.info("password: {}",password);

    }


    public String encodePassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
     }

}
