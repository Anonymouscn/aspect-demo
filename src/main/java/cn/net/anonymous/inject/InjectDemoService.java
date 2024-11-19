package cn.net.anonymous.inject;

import org.springframework.stereotype.Service;

@Service
public class InjectDemoService {

    public Integer Add(Integer a, Integer b) {
        return a + b;
    }

}
