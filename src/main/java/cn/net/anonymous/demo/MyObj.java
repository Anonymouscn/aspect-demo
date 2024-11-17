package cn.net.anonymous.demo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MyObj {

    private Integer age;
    private String name;
    private String phone;
    private Boolean isProgrammer;
}
