package cn.net.anonymous.demo;

import cn.net.anonymous.annotation.ParamsToMap;
import cn.net.anonymous.annotation.ResultToMap;

public class Component {

    // 假定所有方法需要实现 Run 方法
    @ParamsToMap
    @ResultToMap
    public Integer Run(Integer a, Integer b) {
        return a + b;
    }
}
