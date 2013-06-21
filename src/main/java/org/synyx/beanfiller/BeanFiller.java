package org.synyx.beanfiller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.synyx.beanfiller.creator.ArrayCreator;
import org.synyx.beanfiller.creator.BigDecimalCreator;
import org.synyx.beanfiller.creator.BigIntegerCreator;
import org.synyx.beanfiller.creator.BooleanCreator;
import org.synyx.beanfiller.creator.ByteCreator;
import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.DateCreator;
import org.synyx.beanfiller.creator.DoubleCreator;
import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.creator.FloatCreator;
import org.synyx.beanfiller.creator.GenericsCreator;
import org.synyx.beanfiller.creator.IntegerCreator;
import org.synyx.beanfiller.creator.ListCreator;
import org.synyx.beanfiller.creator.LongCreator;
import org.synyx.beanfiller.creator.MapCreator;
import org.synyx.beanfiller.creator.SimpleArrayCreator;
import org.synyx.beanfiller.creator.SimpleCreator;
import org.synyx.beanfiller.creator.SimpleEnumCreator;
import org.synyx.beanfiller.creator.StringCreator;
import org.synyx.beanfiller.criteria.ListCriteria;
import org.synyx.beanfiller.criteria.MapCriteria;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class for recursively filling Beans with random data. As of now, this class only uses public setters for filling the
 * beans, so ensure that your beans you want to fill provide a setter for every variable that should be filled and that
 * it meets the naming convention for setters ('setVariableName').
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BeanFiller {

    private static final Logger LOG = LoggerFactory.getLogger(BeanFiller.class);
    private Map<String, Creator> creatorMap;
    private Map<String, Creator> classAndAttributeSpecificCreatorMap = new HashMap<String, Creator>();
    private int depth = 0;

    /**
     * Create a new instance of the BeanFiller and use the default set of Creators that come with it (Creators for
     * specific classes can be added with addOrReplaceCreator, Creators for a specific field of a specific class can be
     * added with addCreatorForClassAndAttribute).
     */
    public BeanFiller() {

        this(null);
    }


    /**
     * Instanciates the BeanFiller with the given creatorMap instead of the default one. The creator Map consists of a
     * mapping of the full qualified class names of classes to the Creator to use for them.
     *
     * @param  creatorMap  specific creatorMap .
     */
    public BeanFiller(Map<String, Creator> creatorMap) {

        if (creatorMap != null) {
            this.creatorMap = creatorMap;
        } else {
            this.creatorMap = getDefaultCreatorMap();
        }
    }

    /**
     * Fills the given Bean recursively with random data (the fields need to have correctly named ('setVariableName'),
     * public setters for this to work!).
     *
     * @param  object  Bean to fill.
     *
     * @return  the filled Bean.
     *
     * @throws  FillingException  if an error occurred.
     */
    public <T> T fillBean(T object) throws FillingException {

        LOG.debug(getSpaces() + "Filling class: " + object.getClass().getName());
        depth++;

        object = fillObjectInternal(object);

        depth--;
        LOG.debug(getSpaces() + "Finished filling class: " + object.getClass().getName());

        return object;
    }


    /**
     * Adds or replaces the Creator for the given class. This Creators have a lower priority than the ones specified for
     * the attributes of classes.<br/>
     * NOTES:<br/>
     * For replacing the default EnumCreator, call with Enum.class<br/>
     * For replacing the default ArrayCreator, call with org.synyx.beanfiller.creator.ArrayCreator.class<br/>
     * For arrays, use the array classes - e.g. String[].class
     *
     * @param  clazz  class for which the creator should be used
     * @param  creator  creator that should be used for the given class
     */
    public void addCreator(Class clazz, Creator creator) {

        if (clazz == null || creator == null) {
            LOG.warn(getSpaces() + "Class or Creator is null, abort adding the Creator!");

            return;
        }

        LOG.debug(getSpaces() + "Adding  Creator for class : " + clazz.getName()
            + ". Added Creator: " + creator.getClass().getName());
        creatorMap.put(clazz.getName(), creator);
    }


    /**
     * Add a creator that is only used for the given attribute of the given class. The creators specified here have
     * higher priority than the class specific ones.
     *
     * @param  clazz  class for which the creator should be used (for arrays, use the array classes - e.g.
     *                String[].class !)
     * @param  attributeName  attribute for which the creator should be used
     * @param  creator  creator that should be used for the given attribute of the given class
     */
    public void addCreatorForClassAndAttribute(Class clazz, String attributeName, Creator creator) {

        if (clazz == null || attributeName == null || creator == null) {
            LOG.warn("Class, attributeName, or Creator is null, abort adding the creator!");

            return;
        }

        LOG.debug(getSpaces() + "adding attribute specific creator for class and attribute: " + clazz.getName() + " - "
            + attributeName
            + ". Added creator: " + creator.getClass().getName());
        classAndAttributeSpecificCreatorMap.put(clazz.getName() + "." + attributeName, creator);
    }


    /**
     * @return  copy of the map with the Creators mapped with classnames
     */
    public Map<String, Creator> getCreatorMap() {

        return new HashMap<String, Creator>(creatorMap);
    }


    /**
     * @return  copy of the map with the Creators mapped with class and fieldname
     */
    public Map<String, Creator> getClassAndAttributeSpecificCreatorMap() {

        return new HashMap<String, Creator>(classAndAttributeSpecificCreatorMap);
    }


    /**
     * Entry method to fill an Object.
     *
     * @param  object  object to fill.
     *
     * @return  the filled Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    private <T> T fillObjectInternal(T object) throws FillingException {

        Class c = object.getClass();

        // get all setters of this class
        Map<String, Method> setters = getSetters(c);

        // get the fields (variables) of this class.
        Field[] fields = c.getDeclaredFields();

        for (Field field : fields) {
            LOG.debug(getSpaces() + "Filling field: " + field.getName() + " - " + field.getType());

            // get the setter for this field
            Method setter = setters.get("set" + field.getName().toLowerCase());

            if (setter != null) {
                // get the parameter types for the setter
                Class[] parameterTypes = setter.getParameterTypes();

                Object[] parameters = new Object[parameterTypes.length];

                // for each parameter, fill it with data.
                for (int f = 0; f < parameterTypes.length; f++) {
                    Class parameterClass = parameterTypes[f];
                    depth++;
                    parameters[f] = createObject(parameterClass, field);
                    depth--;
                }

                // get the setter
                Method method;

                try {
                    method = c.getMethod(setter.getName(), setter.getParameterTypes());
                } catch (NoSuchMethodException ex) {
                    throw new FillingException("Could not find the setter '" + setter.toString() + "' on object "
                        + c.getName()
                        + ". As we got the setters from the same object before, that's probably a bug in the BeanFiller code, please report.",
                        ex);
                } catch (SecurityException ex) {
                    throw new FillingException("Could not access the setter '" + setter.toString() + "' on object "
                        + c.getName(), ex);
                }

                // call the setter with the created parameters
                try {
                    method.invoke(object, parameters);
                } catch (IllegalAccessException ex) {
                    throw new FillingException("Could not access the setter '" + setter.toString() + "' on object "
                        + c.getName(), ex);
                } catch (IllegalArgumentException ex) {
                    throw new FillingException("Wrong arguments for setter '" + setter.toString() + "' on object "
                        + c.getName() + ". Parameters used: " + Arrays.toString(parameters)
                        + ". That's probably a bug in the BeanFiller code, please report.", ex);
                } catch (InvocationTargetException ex) {
                    throw new FillingException("Exception in the called setter '" + setter.toString() + "' on object "
                        + c.getName() + ". Parameters used: " + Arrays.toString(parameters)
                        + " Probably a bug in the used Creator, or in the setter.", ex);
                }
            }
        }

        return object;
    }


    /**
     * Gets the setter methods of the given class ("set*").
     *
     * @param  clazz  class to get the methods for
     *
     * @return  Map of the setters.
     */
    private Map<String, Method> getSetters(Class clazz) {

        // get the methods of the class
        Method[] methods = clazz.getMethods();

        Map<String, Method> setters = new HashMap<String, Method>();

        for (Method method : methods) {
            if (method.getName().startsWith("set") && Modifier.isPublic(method.getModifiers())) {
                setters.put(method.getName().toLowerCase(), method);
            }
        }

        return setters;
    }


    /**
     * Get the creator for the given fieldname (variable name) in the given class. (exmple: 'name' of the class Customer
     * searches for a creator with the key 'Customer.name').
     *
     * @param  clazz  class to get the Creator for
     * @param  field  field to get the Creator for
     *
     * @return  the Creator or null, if none was found for this combination
     */
    private Creator getSpecificCreator(Class clazz, Field field) {

        Creator b;

        // get the creator for the class and attribute
        LOG.debug(getSpaces() + "getting Creator by class name and field name: " + clazz.getName() + " - "
            + field.getName());
        b = classAndAttributeSpecificCreatorMap.get(clazz.getName() + "." + field.getName());

        return b;
    }


    /**
     * Tries to find a Creator in the two creator maps for the given class and field.
     *
     * @param  clazz  class to get the creator for
     * @param  field  field to get the creator for
     *
     * @return  a Creator or null if none was found.
     */
    private Creator getCreatorForClassAndField(Class clazz, Field field) {

        Creator b = getSpecificCreator(field.getDeclaringClass(), field);

        if (b == null) {
            b = getCreator(clazz);
        }

        return b;
    }


    /**
     * Gets the creator for the specified class.
     *
     * @param  clazz  class
     *
     * @return  the Creator if found, or null.
     */
    private Creator getCreator(Class clazz) {

        Creator b;

        // if no specific creator for this field was set, get the creator for the class of the parameter
        // (class name because of primitive types)
        LOG.debug(getSpaces() + "getting Creator by class name: " + clazz.getName());
        b = creatorMap.get(clazz.getName());

        return b;
    }


    /**
     * Creates the Object for the given class and field - If no Creator is found for this combination, this method tries
     * to instanciate the given class and calls the fillObject method again to try to fill in the fields of that Object.
     *
     * @param  clazz  Class of the Object
     * @param  field  Field to fill (needed to get specific creators).
     *
     * @return  the created Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    public Object createObjectOfClassAndField(Class clazz, Field field) throws FillingException {

        Object parameter;

        Creator c = getCreatorForClassAndField(clazz, field);

        if (c != null) {
            if (!SimpleCreator.class.isAssignableFrom(c.getClass())) {
                throw new WrongCreatorException("The Creator got for class " + clazz.getName() + " and field "
                    + field.getName() + " does not extend " + SimpleCreator.class.getName());
            }

            LOG.debug(getSpaces() + "Using Creator: " + c.getClass().getName());

            SimpleCreator sc = (SimpleCreator) c;

            parameter = sc.create();
        } else {
            try {
                // if we don't have a Creator for it, instanciate the object and try to fill it.
                LOG.debug(getSpaces() + "No Creator for class: " + clazz.getName()
                    + ". Trying to create a new instance of it.");
                parameter = clazz.newInstance();
            } catch (InstantiationException ex) {
                throw new FillingException("There was no Creator set for the class " + clazz.getName() + " (field '"
                    + field.getName() + "' of class " + clazz.getDeclaringClass() + "). "
                    + " So we tried to instatiate it with the default constructor, but it failed! ", ex);
            } catch (IllegalAccessException ex) {
                throw new FillingException("There was no Creator set for the class " + clazz.getName()
                    + " So we tried to instatiate it with the default constructor, but couldn't access it!", ex);
            }

            fillBean(parameter);
        }

        return parameter;
    }


    /**
     * Creates an Object that has generic Types.
     *
     * @param  clazz  Class of the Object
     * @param  pt  ParameterizedType of the class
     * @param  field  Field to fill (needed to get specific Creators).
     *
     * @return  the created Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object createObjectWithGenericTypes(Class clazz, ParameterizedType pt, Field field)
        throws FillingException {

        Creator c = getCreatorForClassAndField(clazz, field);

        if (c != null) {
            if (!GenericsCreator.class.isAssignableFrom(c.getClass())) {
                throw new WrongCreatorException("The Creator got for class " + clazz.getName() + " and field "
                    + field.getName() + " does not extend " + GenericsCreator.class.getName());
            }

            GenericsCreator gb = (GenericsCreator) c;

            List<Object> typeObjects = new ArrayList<Object>();

            // fill the generic objects gb.getSize() times and add them to the list.

            for (int i = 0; i < gb.getSize(); i++) {
                for (Type type : pt.getActualTypeArguments()) {
                    // get the class of the generic type and create it
                    String classString = type.toString().contains("class ") ? type.toString().split(" ")[1]
                                                                            : type.toString();

                    // The generic Type has got another generic Type e.g. List<List<String>>
                    if (classString.contains("<")) {
                        classString = classString.split("<")[0];
                    }

                    Object typeObject;

                    try {
                        typeObject = createObject(Class.forName(classString), field, type);
                    } catch (ClassNotFoundException ex) {
                        throw new FillingException("Could not find the class " + classString
                            + " on Filling the Generic Parameters of the class " + clazz.getName()
                            + " (Tried to get it from the String '" + type.toString() + "'", ex);
                    }

                    typeObjects.add(typeObject);
                }
            }

            LOG.debug(getSpaces() + "using GenericsCreator: " + gb.getClass().getName());

            return gb.createWithGenerics(typeObjects);
        }

        LOG.warn(getSpaces() + "Could not find any Creator for class " + clazz.getName() + " and field "
            + field.getName());

        return null;
    }


    /**
     * Creates the given Enum Object.
     *
     * @param  clazz  Class of the Object
     * @param  field  Field to fill (needed to get specific creators).
     *
     * @return  the created Enum (or null if no creator was found or the Enum had no Enum constants)
     */
    private Object createEnum(Class clazz, Field field) throws FillingException {

        Creator c = getCreatorForClassAndField(clazz, field);

        if (c == null) {
            // get the generic enum Creator
            c = getCreator(java.lang.Enum.class);
        }

        if (c != null) {
            if (!EnumCreator.class.isAssignableFrom(c.getClass())) {
                throw new WrongCreatorException("The Creator got for class " + clazz.getName() + " and field "
                    + field.getName()
                    + " does not extend " + EnumCreator.class.getName());
            }

            EnumCreator ec = (EnumCreator) c;

            LOG.debug(getSpaces() + "using EnumCreator: " + ec.getClass().getName());

            return ec.createEnum(clazz);
        }

        LOG.warn(getSpaces() + "Could not find any Creator for class " + clazz.getName() + " and field "
            + field.getName());

        return null;
    }


    /**
     * Wrapper method for creating Objects, enriches the call with the Type of the Object.
     *
     * @param  parameterClass  Class of the Object
     * @param  field  Field to fill (needed to get specific creators).
     *
     * @return  the created Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object createObject(Class parameterClass, Field field) throws FillingException {

        Type type = field.getGenericType();

        return createObject(parameterClass, field, type);
    }


    /**
     * Wrapper method for creating Objects - delegates the work to the specific methods for different usecases.
     *
     * @param  parameterClass  Class of the Object
     * @param  field  Field to fill (needed to get specific creators).
     * @param  type  Type of the Object
     *
     * @return  the created Object.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object createObject(Class parameterClass, Field field, Type type) throws FillingException {

        LOG.debug(getSpaces() + "Calling createObject with class: " + parameterClass.getName());
        depth++;

        Object object;

        if (parameterClass.isEnum()) {
            object = createEnum(parameterClass, field);
        } else if (parameterClass.isArray()) {
            object = createArray(parameterClass, field);
        } else if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
            // if this is a ParameterizedType, the Object defines Generics
            ParameterizedType paramType = (ParameterizedType) type;

            object = createObjectWithGenericTypes(parameterClass, paramType, field);
        } else {
            object = createObjectOfClassAndField(parameterClass, field);
        }

        depth--;
        LOG.debug(getSpaces() + "Finished creating Object: " + object);

        return object;
    }


    /**
     * Method for creating Arrays.
     *
     * @param  clazz  class of the Object to create.
     * @param  field  field to fill.
     *
     * @return  the created Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object createArray(Class clazz, Field field) throws FillingException {

        Class arrayType = clazz.getComponentType();

        List<Object> objectsForArray = new ArrayList<Object>();

        Creator b = getCreatorForClassAndField(clazz, field);

        if (b == null) {
            // get the generic array creator
            b = getCreator(ArrayCreator.class);
        }

        if (b != null) {
            if (!ArrayCreator.class.isAssignableFrom(b.getClass())) {
                throw new WrongCreatorException("The Creator got for class " + clazz.getName() + " and field "
                    + field.getName()
                    + " does not extend " + ArrayCreator.class.getName());
            }

            ArrayCreator ac = (ArrayCreator) b;

            LOG.debug(getSpaces() + "Using ArrayCreator: " + ac.getClass().getName());

            for (int i = 0; i < ac.getSize(); i++) {
                objectsForArray.add(createObjectForArray(arrayType, field));
            }

            return ac.createArray(objectsForArray, arrayType);
        }

        LOG.warn(getSpaces() + "Could not find any Creator for class " + clazz.getName() + " and field "
            + field.getName());

        return null;
    }


    /**
     * Wrapper method to create an Object for an Array that enriches the call with the Type of the field.
     *
     * @param  arrayType  ComponentType class of the Array.
     * @param  field  field to fill.
     *
     * @return  the created Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object createObjectForArray(Class arrayType, Field field) throws FillingException {

        Type type = field.getGenericType();

        if (GenericArrayType.class.isAssignableFrom(type.getClass())) {
            // if we have an Array of a Type with Generics, this is needed!
            type = ((GenericArrayType) type).getGenericComponentType();
        }

        return createObject(arrayType, field, type);
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
        map.put("java.util.Map", mapCreator);

        ListCreator listCreator = new ListCreator(new ListCriteria());
        map.put("java.util.List", listCreator);

        EnumCreator enumCreator = new SimpleEnumCreator();
        map.put("java.lang.Enum", enumCreator);

        ArrayCreator arrayCreator = new SimpleArrayCreator();
        map.put("org.synyx.beanfiller.creator.ArrayCreator", arrayCreator);

        DateCreator dateCreator = new DateCreator();
        map.put("java.util.Date", dateCreator);

        return map;
    }


    /**
     * Convenience method to get a number spaces for the log statements according to the depth of the call.
     *
     * @return  String with a number of spaces.
     */
    private String getSpaces() {

        // only indent if debug is enabled
        if (!LOG.isDebugEnabled()) {
            return "";
        }

        char[] charArray = new char[depth * 2];
        Arrays.fill(charArray, ' ');

        String spaces = new String(charArray);

        return spaces;
    }
}
