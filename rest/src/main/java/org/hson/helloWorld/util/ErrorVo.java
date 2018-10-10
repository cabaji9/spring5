package org.hson.helloWorld.util;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hyun Woo Son on 10/10/18
 **/
@Data
public class ErrorVo implements Serializable {

    private final String msg;

}
