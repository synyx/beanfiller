package org.synyx.beanfiller.util;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Class for Util methods for handling generics.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class GenericsUtils {

    /**
     * Gets the actual type arguments for the given field.
     *
     * @param  field  field to get the actual type arguments for.
     *
     * @return  List of the actual type arguments of the field. Can be empty if the field has none.
     */
    public static List<Type> getActualTypeArguments(Field field) {

        List<Type> actualTypeList = new ArrayList<>();

        if (field != null) {
            Type genericType = field.getGenericType();

            if (hasGenerics(genericType)) {
                if (GenericArrayType.class.isAssignableFrom(genericType.getClass())) {
                    // if we have an Array of a Type with Generics, this is needed!
                    genericType = ((GenericArrayType) genericType).getGenericComponentType();
                }

                ParameterizedType objectType = (ParameterizedType) genericType;

                Collections.addAll(actualTypeList, objectType.getActualTypeArguments());
            }
        }

        return actualTypeList;
    }


    /**
     * Check if the given type has Generics.
     *
     * @param  type  type to check.
     *
     * @return  true if the type has Generics, false otherwise.
     */
    public static boolean hasGenerics(Type type) {

        // check if the type is a ParametrizedType or a GenericArrayType. In both cases, the field has Generics.
        return ParameterizedType.class.isAssignableFrom(type.getClass())
            || GenericArrayType.class.isAssignableFrom(type.getClass());
    }


    /**
     * Get the class of the given Type.
     *
     * @param  type  type to get the class for.
     *
     * @return  the Class of the type.
     *
     * @throws  ClassNotFoundException  if the class was not found.
     */
    public static Class getClassForType(Type type) throws ClassNotFoundException {

        // get the class of the generic type and create it
        String classString = type.toString().contains("class ") ? type.toString().split(" ")[1] : type.toString();

        // The generic Type has got another generic Type e.g. List<List<String>>
        if (classString.contains("<")) {
            classString = classString.split("<")[0];
        }

        return Class.forName(classString);
    }
}
