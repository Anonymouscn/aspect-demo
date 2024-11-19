package cn.net.anonymous.demo;

import cn.net.anonymous.annotation.ParamsToMap;
import cn.net.anonymous.annotation.ResultToMap;
import org.springframework.stereotype.Service;

/**
 * ObjService 获取对象服务 (demo)
 * @author anonymous
 */
@Service
public class ObjService {

    /**
     * 获取对象方法
     * @param age 年龄 (demo)
     * @param name 名字 (demo)
     * @param phone 手机 (demo)
     * @param isProgrammer 是否是程序员 (demo)
     * @return 方法返回的对象
     */
    @ParamsToMap
    @ResultToMap
    public MyObj GetMyObj(Integer age, String name, String phone, Boolean isProgrammer) {
        return new MyObj().
                setAge(age).
                setName(name).
                setPhone(phone).
                setIsProgrammer(isProgrammer);
    }
}
