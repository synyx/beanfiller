package org.synyx.beanfiller.analyzer;

import org.synyx.beanfiller.ObjectInformation;
import org.synyx.beanfiller.strategies.AbstractCreatorStrategy;
import org.synyx.beanfiller.strategies.StrategyManager;

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

    /**
     * Analyzes the given clazz and returns a List of ObjectInformation that have to be processed in order to fill the
     * Bean.
     *
     * @param  clazz
     * @param  strategyManager
     *
     * @return
     */
    public static List<ObjectInformation> analyzeBean(Class clazz, StrategyManager strategyManager) {

        List<ObjectInformation> objectInformation = new ArrayList<ObjectInformation>();

        String path = clazz.getSimpleName();

        analyzeInternal(clazz, strategyManager, objectInformation, path);

        return objectInformation;
    }


    private static void analyzeInternal(Class clazz, StrategyManager strategyManager,
        List<ObjectInformation> objectInformation, String path) throws SecurityException {

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
                        field.getType(), setter, fieldPath);
                AbstractCreatorStrategy strategy = strategyManager.getStrategyFor(parameterObjectInformation);
                parameterObjectInformation.setStrategy(strategy);
                objectInformation.add(parameterObjectInformation);

//                if (JustAnotherBeanStrategy.class.equals(strategy.getClass())) {
//                    analyzeInternal(parameterClazz, strategyManager, objectInformation, fieldPath);
//                }
            }
        }
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
