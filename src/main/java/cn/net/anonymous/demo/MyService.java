package cn.net.anonymous.demo;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * MyService 自定义服务 (主要用于启动时执行获取对象方法)
 * @author anonymous
 */
@Service
@AllArgsConstructor
public class MyService implements ApplicationListener<ContextRefreshedEvent> {

    private ObjService mapConvertService;

    // hook spring 事件监听器, 启动时执行
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        getMyObjWhenStart();
    }

    public MyObj getMyObjWhenStart() {
        Map<String, Object> paramsMap = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        MyObj obj = mapConvertService.GetMyObj(25, "Anonymous", "53453466456", true, paramsMap, resultMap);

        System.out.println("入参 map: ");
        System.out.println(paramsMap);
        System.out.println("出参 map: ");
        System.out.println(resultMap);
        System.out.println("方法执行返回对象: ");
        System.out.println(obj);
        return obj;
    }
}
