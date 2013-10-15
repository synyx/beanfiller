package org.synyx.beanfiller.services;

import org.synyx.beanfiller.domain.ObjectInformation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that analyzes Beans and provides the necessary information to process the Beans.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public final class BeanAnalyzer {

    private BeanAnalyzer() {

        // Utility class should hide constructor
    }

    /**
     * Analyzes the given clazz and returns a List of ObjectInformation that have to be processed in order to fill the
     * Bean.
     *
     * @param  clazz
     *
     * @return
     */
    public static List<ObjectInformation> analyzeBean(Class clazz) {

        List<ObjectInformation> oInfo = new ArrayList<ObjectInformation>();

        String path = clazz.getSimpleName();

        Field[] fields = clazz.getDeclaredFields();
        Map<String, Method> setters = getSetters(clazz);

        for (Field field : fields) {
            // get the setter for this field
            Method setter = setters.get("set" + field.getName().toLowerCase());
            String fieldPath = path + "." + field.getName();

            if (setter != null) {
                // get the parameter type of the setter
                Class parameterClazz = setter.getParameterTypes()[0];

                oInfo.add(new ObjectInformation(parameterClazz, field, field.getType(), setter, fieldPath)); // NOSONAR
            }
        }

        return oInfo;
    }


    /**
     * Gets the setter methods of the given class ("set*").
     *
     * @param  clazz  class to get the methods for
     *
     * @return  Map of the setters.
     */
    private static Map<String, Method> getSetters(Class clazz) {

        // get the methods of the class
        Method[] methods = clazz.getMethods();

        Map<String, Method> setters = new HashMap<String, Method>();

        for (Method method : methods) {
            // method name start with set,
            boolean condition = method.getName().startsWith("set");

            // it is public
            condition &= Modifier.isPublic(method.getModifiers());

            // it has exactly one parameter
            condition &= condition && (method.getParameterTypes() != null && method.getParameterTypes().length == 1);

            // it is public and it has exactly one parameter.
            if (condition) {
                setters.put(method.getName().toLowerCase(), method);
            }
        }

        return setters;
    }
}
