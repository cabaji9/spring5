package org.hson.app.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;

/**
 * Created by Hyun Woo Son on 10/3/18
 **/
@Validated
@Data
@Component
@ConfigurationProperties(prefix = "custom.property")
public class PropertyHolder {


    @Size(min = 10, message = "Minimum size must be 10")
    private String salute;
}
