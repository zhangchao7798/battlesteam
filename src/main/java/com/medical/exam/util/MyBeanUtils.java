package com.medical.exam.util;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Wu
 */
public class MyBeanUtils extends BeanUtils {
    /**
     * 获取值为Null的property
     * @param source
     * @return
     */
    private static String[] getNullPropertyNames (Object source) {
        try {
            PropertyDescriptor[] pds= BeanUtils.getPropertyDescriptors(source.getClass());

            Set<String> emptyNames = new HashSet<String>();
            for(PropertyDescriptor pd : pds) {
                Method method=pd.getReadMethod();
                if(method!=null){
                    Object srcValue = method.invoke(source);
                    if (srcValue == null) {
                        emptyNames.add(pd.getName());
                    }
                }
            }
            String[] result = new String[emptyNames.size()];
            return emptyNames.toArray(result);
        }catch (Exception e){
            //异常不做处理即可.

        }
        return null;

    }

    /**
     * 只复制非空字段
     * @param src
     * @param target
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }
}
