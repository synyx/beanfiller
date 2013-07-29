package org.synyx.beanfiller;

import org.synyx.beanfiller.exceptions.FillingException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.List;
import java.util.Map;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BeanSetter {

    public static <T> T setBean(T bean, List<ObjectInformation> objectInformationList,
        Map<String, Object> createdObjectMap) throws FillingException {

        for (int i = 0; i < objectInformationList.size(); i++) {
            ObjectInformation information = objectInformationList.get(i);
            Object object = createdObjectMap.get(information.getPath());

            Method accessor = information.getAccessor();

            try {
                accessor.invoke(bean, object);
            } catch (IllegalAccessException ex) {
                throw new FillingException("Could not access the setter '" + accessor.toString() + "' on object "
                    + bean.getClass().getName(), ex);
            } catch (IllegalArgumentException ex) {
                throw new FillingException("Wrong arguments for setter '" + accessor.toString() + "' on object "
                    + bean.getClass().getName() + ". Parameters used: " + object.toString()
                    + ". That's probably a bug in the BeanFiller code, please report.", ex);
            } catch (InvocationTargetException ex) {
                throw new FillingException("Exception in the called setter '" + accessor.toString() + "' on object "
                    + bean.getClass().getName() + ". Parameters used: " + object.toString()
                    + " Probably a bug in the used Creator, or in the setter.", ex);
            }
        }

        return bean;
    }
}
