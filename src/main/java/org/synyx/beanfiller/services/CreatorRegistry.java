package org.synyx.beanfiller.services;

import org.synyx.beanfiller.creator.Creator;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;


/**
 * Responsible for managing the different Creators.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class CreatorRegistry {

    private final Map<String, Creator> creatorMap;
    private final Map<String, Creator> classAndAttributeSpecificCreatorMap = new HashMap<>();

    /**
     * Create a new CreatorRegistry using the given creatorMap.
     *
     * @param  creatorMap  Map of Creators to use.
     */
    public CreatorRegistry(Map<String, Creator> creatorMap) {

        this.creatorMap = creatorMap;
    }

    /**
     * Adds the given Creator to be used on the given attribute of the given class.
     *
     * @param  clazz  clazz of the attribute the creator should be used on
     * @param  attributeName  attribute name the creator should be used on
     * @param  creator  creator to add.
     */
    public void addCreatorForClassAndAttribute(Class clazz, String attributeName, Creator creator) {

        classAndAttributeSpecificCreatorMap.put(clazz.getName() + "." + attributeName, creator);
    }


    /**
     * Adds the given creator to be used for the given class.
     *
     * @param  clazz  class to use the creator on.
     * @param  creator  creator to add.
     */
    public void addCreator(Class clazz, Creator creator) {

        creatorMap.put(clazz.getName(), creator);
    }


    /**
     * Tries to find a Creator in the two creator maps for the given field.
     *
     * @param  clazz  class to get the creator for
     * @param  field  field to get the creator for
     *
     * @return  a Creator or null if none was found.
     */
    public Creator getCreator(Class clazz, Field field) {

        Creator c = null;

        if (field != null) {
            c = getSpecificCreator(field.getDeclaringClass(), field);
        }

        if (c == null) {
            c = getCreatorForClass(clazz);
        }

        return c;
    }


    /**
     * Get the creator for the given fieldname (variable name) in the given class. (example: 'name' of the class
     * Customer searches for a creator with the key 'Customer.name').
     *
     * @param  clazz  class to get the Creator for
     * @param  field  field to get the Creator for
     *
     * @return  the Creator or null, if none was found for this combination
     */
    private Creator getSpecificCreator(Class clazz, Field field) {

        // get the creator for the class and attribute
        return classAndAttributeSpecificCreatorMap.get(clazz.getName() + "." + field.getName());
    }


    /**
     * Gets the creator for the specified class.
     *
     * @param  clazz  class
     *
     * @return  the Creator if found, or null.
     */
    public Creator getCreatorForClass(Class clazz) {

        // if no specific creator for this field was set, get the creator for the class of the parameter
        // (class name because of primitive types)
        return creatorMap.get(clazz.getName());
    }


    /**
     * @return  the creatorMap.
     */
    public Map<String, Creator> getCreatorMap() {

        return creatorMap;
    }


    /**
     * @return  the map of the class and attribute specific Creators.
     */
    public Map<String, Creator> getClassAndAttributeSpecificCreatorMap() {

        return classAndAttributeSpecificCreatorMap;
    }
}
