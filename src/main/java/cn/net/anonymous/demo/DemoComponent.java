package cn.net.anonymous.demo;

import cn.net.anonymous.annotation.MapParams;
import cn.net.anonymous.annotation.ParamsToMap;
import cn.net.anonymous.annotation.ResultToMap;

@org.springframework.stereotype.Component
public class DemoComponent extends Component {

    @Override
    public Integer Run(Integer a, Integer b) {
        return a + b;
    }
}
