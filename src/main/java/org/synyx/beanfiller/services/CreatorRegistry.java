package org.synyx.beanfiller.services;

import org.synyx.beanfiller.creator.ArrayCreator;
import org.synyx.beanfiller.creator.BigDecimalCreator;
import org.synyx.beanfiller.creator.BigIntegerCreator;
import org.synyx.beanfiller.creator.BooleanCreator;
import org.synyx.beanfiller.creator.ByteCreator;
import org.synyx.beanfiller.creator.CharCreator;
import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.DateCreator;
import org.synyx.beanfiller.creator.DoubleCreator;
import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.creator.FloatCreator;
import org.synyx.beanfiller.creator.IntegerCreator;
import org.synyx.beanfiller.creator.ListCreator;
import org.synyx.beanfiller.creator.LongCreator;
import org.synyx.beanfiller.creator.MapCreator;
import org.synyx.beanfiller.creator.ShortCreator;
import org.synyx.beanfiller.creator.SimpleArrayCreator;
import org.synyx.beanfiller.creator.SimpleEnumCreator;
import org.synyx.beanfiller.creator.StringCreator;
import org.synyx.beanfiller.criteria.CollectionCriteria;
import org.synyx.beanfiller.criteria.MapCriteria;

import java.lang.reflect.Field;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Responsible for managing the different Creators.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class CreatorRegistry {

    private final Map<String, Creator> creatorMap;
    private final Map<String, Creator> classAndAttributeSpecificCreatorMap = new HashMap<String, Creator>();

    public CreatorRegistry() {

        this(null);
    }


    public CreatorRegistry(Map<String, Creator> creatorMap) {

        if (creatorMap != null) {
            this.creatorMap = creatorMap;
        } else {
            this.creatorMap = getDefaultCreatorMap();
        }
    }

    public void addCreatorForClassAndAttribute(Class clazz, String attributeName, Creator creator) {

        classAndAttributeSpecificCreatorMap.put(clazz.getName() + "." + attributeName, creator);
    }


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


    public Map<String, Creator> getCreatorMap() {

        return creatorMap;
    }


    public Map<String, Creator> getClassAndAttributeSpecificCreatorMap() {

        return classAndAttributeSpecificCreatorMap;
    }


    /**
     * Gets the default creator Map that contains the basic set of creators.
     *
     * @return  Map of Creators
     */
    private Map<String, Creator> getDefaultCreatorMap() {

        Map<String, Creator> map = new HashMap<String, Creator>();

        map.put(String.class.getName(), new StringCreator());

        IntegerCreator integerCreator = new IntegerCreator();
        map.put("int", integerCreator);
        map.put(Integer.class.getName(), integerCreator);

        FloatCreator floatCreator = new FloatCreator();
        map.put("float", floatCreator);
        map.put(Float.class.getName(), floatCreator);

        LongCreator longCreator = new LongCreator();
        map.put("long", longCreator);
        map.put(Long.class.getName(), longCreator);

        DoubleCreator doubleCreator = new DoubleCreator();
        map.put("double", doubleCreator);
        map.put(Double.class.getName(), doubleCreator);

        BooleanCreator booleanCreator = new BooleanCreator();
        map.put("boolean", booleanCreator);
        map.put(Boolean.class.getName(), booleanCreator);

        ByteCreator byteCreator = new ByteCreator();
        map.put("byte", byteCreator);
        map.put(Byte.class.getName(), byteCreator);

        BigIntegerCreator bigIntegerCreator = new BigIntegerCreator();
        map.put(BigInteger.class.getName(), bigIntegerCreator);

        BigDecimalCreator bigDecimalCreator = new BigDecimalCreator();
        map.put(BigDecimal.class.getName(), bigDecimalCreator);

        MapCreator mapCreator = new MapCreator(new MapCriteria());
        map.put(Map.class.getName(), mapCreator);

        ListCreator listCreator = new ListCreator(new CollectionCriteria());
        map.put(List.class.getName(), listCreator);

        EnumCreator enumCreator = new SimpleEnumCreator();
        map.put(Enum.class.getName(), enumCreator);

        ArrayCreator arrayCreator = new SimpleArrayCreator();
        map.put("org.synyx.beanfiller.creator.ArrayCreator", arrayCreator);

        DateCreator dateCreator = new DateCreator();
        map.put(Date.class.getName(), dateCreator);

        CharCreator charCreator = new CharCreator();
        map.put(Character.class.getName(), charCreator);
        map.put("char", charCreator);

        ShortCreator shortCreator = new ShortCreator();
        map.put(Short.class.getName(), shortCreator);
        map.put("short", shortCreator);

        return map;
    }
}
