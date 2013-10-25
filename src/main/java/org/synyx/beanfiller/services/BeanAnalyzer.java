package org.synyx.beanfiller.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.synyx.beanfiller.domain.ObjectInformation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class that analyzes Beans and provides the neccessary information to process the Beans.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BeanAnalyzer {

    private static final Logger LOG = LoggerFactory.getLogger(BeanAnalyzer.class);

    /**
     * Analyzes the given clazz and returns a List of ObjectInformation that have to be processed in order to fill the
     * Bean.
     *
     * @param  clazz
     * @param  strategyManager
     *
     * @return
     */
    public static List<ObjectInformation> analyzeBean(Class clazz) {

        List<ObjectInformation> objectInformation = new ArrayList<ObjectInformation>();

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

                ObjectInformation parameterObjectInformation = new ObjectInformation(parameterClazz, field,
                        field.getType(), setter, fieldPath, null);

                objectInformation.add(parameterObjectInformation);
            }
        }

        return objectInformation;
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
            // method name start with set, it is public and it has exactly one parameter.
            if (method.getName().startsWith("set") && Modifier.isPublic(method.getModifiers())
                    && method.getParameterTypes() != null && method.getParameterTypes().length == 1) {
                setters.put(method.getName().toLowerCase(), method);
            }
        }

        return setters;
    }
}
