package cn.net.anonymous.aspect;

import cn.net.anonymous.annotation.MapParams;
import cn.net.anonymous.annotation.MapResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * Map 转换器切面
 * @author anonymous
 */
@Aspect
@Component
public class MapConverterAspect {

    // 修改注入切面的点为: cn.net.anonymous.inject 包下，任意返回值的所有方法执行
    @Around("execution(* cn.net.anonymous.inject.*.*(..))")
//    @Around("@annotation(cn.net.anonymous.annotation.ParamsToMap) || @annotation(cn.net.anonymous.annotation.ResultToMap)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] args = point.getArgs();

        Parameter[] parameters = method.getParameters();
//        Map<String, Object> paramsMap = null;
//        Map<String, Object> resultMap = null;

        Map<String, Object> paramsMap = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();

        // 过滤方法是否存在 paramsMap, resultMap
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(MapParams.class) && args[i] instanceof Map) {
                paramsMap = (Map<String, Object>) args[i];
            }
            if (parameters[i].isAnnotationPresent(MapResult.class) && args[i] instanceof Map) {
                resultMap = (Map<String, Object>) args[i];
            }
        }

        // 将参数添加到 paramsMap
        if (paramsMap != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (!parameters[i].isAnnotationPresent(MapParams.class) &&
                        !parameters[i].isAnnotationPresent(MapResult.class)) {
                    paramsMap.put(parameters[i].getName(), args[i]);
                }
            }
        }

        // 执行方法
        Object result = point.proceed(args);

        // 将结果添加到 resultMap
        if (resultMap != null && result != null) {
            if (result instanceof Map) {
                resultMap.putAll((Map<String, Object>) result);
            } else if (result.getClass().isPrimitive() ||
                    result instanceof String ||
                    result instanceof Number ||
                    result instanceof Boolean) {
                resultMap.put("result", result);
            } else {
                for (Field field : result.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    resultMap.put(field.getName(), field.get(result));
                }
            }
        }

        System.out.println(paramsMap);
        System.out.println(resultMap);

        return result;
    }
}
