package org.synyx.beanfiller.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.synyx.beanfiller.util.GenericsUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;


/**
 * History of the filled classes to keep track of the State.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class History {

    private static final Logger LOG = LoggerFactory.getLogger(History.class);
    private List<Class> filledClasses;
    private boolean isRepeating = false;

    /**
     * Create new History with the given ObjectInformation as root.
     *
     * @param  objectInformation  root of the new History.
     */
    public History(ObjectInformation objectInformation) {

        filledClasses = new ArrayList<Class>();
        update(objectInformation);
    }


    /**
     * Create new History by copying the parent History.
     *
     * @param  parent  parent of the new History.
     */
    public History(History parent) {

        this.filledClasses = parent.getFilledClasses();
        this.isRepeating = parent.isRepeating;
    }

    /**
     * Get a copy of the current List of filledClasses.
     *
     * @return  List of previously filled classes.
     */
    public List<Class> getFilledClasses() {

        return new ArrayList<Class>(filledClasses);
    }


    public void setFilledClasses(List<Class> filledClasses) {

        this.filledClasses = filledClasses;
    }


    /**
     * Add a ObjectInformation to the History.
     *
     * @param  objectInformation  ObjectInformation to add to the history.
     */
    public void add(ObjectInformation objectInformation) {

        update(objectInformation);
    }


    /**
     * Returns whether the History contains a cycle.
     *
     * @return  true if history is repeating itself - false otherwise.
     */
    public boolean isRepeating() {

        return isRepeating;
    }


    /**
     * Updates the History with the given ObjectInformation.
     *
     * @param  objectInformation  ObjectInformation to update the History with.
     */
    private void update(ObjectInformation objectInformation) {

        Class currentClass = objectInformation.getClazz();
        Field currentField = objectInformation.getField();
        checkForCycle(currentClass, currentField);

        filledClasses.add(currentClass);
    }


    /**
     * Checks if the given class would introduce a cycle and sets the isRepeating flag appropriately.
     *
     * @param  currentClass  the current class to check.
     * @param  currentField  the current field to check.
     */
    private void checkForCycle(Class currentClass, Field currentField) {

        List<Class> classesToCheck = new ArrayList<Class>();
        classesToCheck.add(currentClass);

        // we also have to check for the actual type arguments for the case we have a class with generics.
        List<Type> actualTypeArguments = GenericsUtils.getActualTypeArguments(currentField);

        for (Type type : actualTypeArguments) {
            try {
                Class clazz = GenericsUtils.getClassForType(type);

                classesToCheck.add(clazz);
            } catch (ClassNotFoundException ex) {
                LOG.warn("Did not find class of type: " + type.toString()
                    + "! But as we are only checking for cycles here, we don't handle it!", ex);
            }
        }

        // if one of the classes is contained in the history, the given objectInformation is creating a cycle.
        for (Class clazz : classesToCheck) {
            if (filledClasses.contains(clazz)) {
                isRepeating = true;

                break;
            }
        }
    }
}
