package org.synyx.beanfiller.services;

import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.List;
import java.util.Map;


/**
 * Class for setting the created Objects on the Bean.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public final class BeanSetter {

    private BeanSetter() {

        // Utility class should hide constructor
    }

    /**
     * @param  bean  Bean to set the given Objects on
     * @param  objectInformationList  List of ObjectInformation of the Fields of the bean
     * @param  createdObjectMap  Created Objects that should be set on the bean
     *
     * @return  the Bean with set fields.
     *
     * @throws  FillingException  if something went wrong on calling a setter on the bean.
     */
    public static <T> T setBean(T bean, List<ObjectInformation> objectInformationList,
        Map<String, Object> createdObjectMap) throws FillingException {

        for (ObjectInformation information : objectInformationList) {
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
