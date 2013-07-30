
package org.synyx.beanfiller.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;


/**
 * Contains Meta-Information about an Object that is needed in the creation proccess.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ObjectInformation {

    private Class clazz;
    private Field field;
    private Method accessor;
    private Type type;
    private String path;

    /**
     * @param  clazz  class of the object
     * @param  field  the field, the object should be put into
     * @param  type  type of the object to create.
     * @param  accessor  accessor to set the field.
     * @param  path  the object path of the object
     */
    public ObjectInformation(Class clazz, Field field, Type type, Method accessor, String path) {

        this.clazz = clazz;
        this.field = field;
        this.type = type;
        this.accessor = accessor;
        this.path = path;
    }

    public Class getClazz() {

        return clazz;
    }


    public void setClazz(Class clazz) {

        this.clazz = clazz;
    }


    public Field getField() {

        return field;
    }


    public void setField(Field field) {

        this.field = field;
    }


    public Type getType() {

        return type;
    }


    public void setType(Type type) {

        this.type = type;
    }


    public Method getAccessor() {

        return accessor;
    }


    public void setAccessor(Method accessor) {

        this.accessor = accessor;
    }


    public String getPath() {

        return path;
    }


    public void setPath(String path) {

        this.path = path;
    }


    @Override
    public String toString() {

        return "ObjectInformation{" + "clazz=" + clazz + ", field=" + field + ", accessor=" + accessor + ", type="
            + type + ", path=" + path + '}';
    }
}
