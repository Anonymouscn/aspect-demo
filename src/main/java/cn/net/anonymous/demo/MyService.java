package cn.net.anonymous.demo;

import cn.net.anonymous.inject.InjectDemoService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * MyService 自定义服务 (主要用于启动时执行获取对象方法)
 * @author anonymous
 */
@Service
@AllArgsConstructor
public class MyService implements ApplicationListener<ContextRefreshedEvent> {

    private InjectDemoService injectDemoService;

    // hook spring 事件监听器, 启动时执行
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        getMyObjWhenStart();
    }

    public void getMyObjWhenStart() {
        injectDemoService.Add(1, 2);
    }
}
