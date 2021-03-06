package org.synyx.beanfiller.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;


/**
 * Contains Meta-Information about an Object that is needed in the creation process.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ObjectInformation {

    private Class<?> clazz;
    private Field field;
    private Method accessor;
    private Type type;
    private String path;
    private ObjectInformation parent;
    private History history;

    /**
     * @param  clazz  class of the object
     * @param  field  the field, the object should be put into
     * @param  type  type of the object to create.
     * @param  accessor  accessor to set the field.
     * @param  path  the object path of the object
     * @param  parent  the parent ObjectInformation of this one
     */
    public ObjectInformation(Class<?> clazz, Field field, Type type, Method accessor, String path,
        ObjectInformation parent) {

        this.clazz = clazz;
        this.field = field;
        this.type = type;
        this.accessor = accessor;
        this.path = path;
        createHistory(parent);
    }

    public Class<?> getClazz() {

        return clazz;
    }


    public void setClazz(Class<?> clazz) {

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


    /**
     * Returns a copy of the History of this ObjectInformation.
     *
     * @return  copy of the current History of this ObjectInformation.
     */
    public History getHistory() {

        return new History(history);
    }


    public ObjectInformation getParent() {

        return parent;
    }


    /**
     * Sets the parent ObjectInformation and (re-)creates the History for this ObjectInformation.
     *
     * @param  parent  the parent ObjectInformation to use.
     */
    public void setParent(ObjectInformation parent) {

        this.parent = parent;
        createHistory(parent);
    }


    @Override
    public String toString() {

        return "ObjectInformation{" + "clazz=" + clazz + ", field=" + field + ", accessor=" + accessor + ", type="
            + type + ", path=" + path + '}';
    }


    /**
     * Create the History for this ObjectInformation (based on the parents History if it is set).
     *
     * @param  parent  the parent ObjectInformation to use to create the History.
     */
    private void createHistory(ObjectInformation parent) {

        if (parent != null && parent.getHistory() != null) {
            this.history = new History(parent.getHistory());
            this.history.add(this);
        } else {
            this.history = new History(this);
        }
    }
}
