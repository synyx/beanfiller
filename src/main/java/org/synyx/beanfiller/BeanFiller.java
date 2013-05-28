package org.synyx.beanfiller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.synyx.beanfiller.builder.ArrayBuilder;
import org.synyx.beanfiller.builder.BigDecimalBuilder;
import org.synyx.beanfiller.builder.BigIntegerBuilder;
import org.synyx.beanfiller.builder.BooleanBuilder;
import org.synyx.beanfiller.builder.Builder;
import org.synyx.beanfiller.builder.ByteBuilder;
import org.synyx.beanfiller.builder.DoubleBuilder;
import org.synyx.beanfiller.builder.EnumBuilder;
import org.synyx.beanfiller.builder.FloatBuilder;
import org.synyx.beanfiller.builder.GenericsBuilder;
import org.synyx.beanfiller.builder.IntegerBuilder;
import org.synyx.beanfiller.builder.ListBuilder;
import org.synyx.beanfiller.builder.LongBuilder;
import org.synyx.beanfiller.builder.MapBuilder;
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
    private Map<String, Builder> builderMap;
    private Map<String, Builder> classAndAttributeSpecificBuilderMap = new HashMap<String, Builder>();
    private int depth = 0;

    /**
     * Create a new instance of the BeanFiller and use the default set of Builders that come with it (builders for
     * specific classes can be added with addOrReplaceBuilder, Builders for a specific field of a specific class can be
     * added with addBuilderForClassAndAttribute).
     */
    public BeanFiller() {

        this(null);
    }


    /**
     * Instanciates the BeanFiller with the given builderMap instead of the default one. The builder Map consists of a
     * mapping of the full qualified class names of classes to the Builders to use for them.
     *
     * @param  builderMap  specific builderMap .
     */
    public BeanFiller(Map<String, Builder> builderMap) {

        if (builderMap != null) {
            this.builderMap = builderMap;
        } else {
            this.builderMap = getDefaultBuilderMap();
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
     * Adds or replaces the builder for the given class. This builders have a lower priority than the ones specified for
     * the attributes of classes.<br/>
     * NOTES:<br/>
     * For replacing the default EnumBuilder, call with Enum.class<br/>
     * For replacing the default ArrayBuilder, call with org.synyx.beanfiller.builder.ArrayBuilder.class<br/>
     * For arrays, use the array classes - e.g. String[].class
     *
     * @param  clazz  class for which the builder should be used
     * @param  builder  builder that should be used for the given class
     */
    public void addBuilder(Class clazz, Builder builder) {

        if (clazz == null || builder == null) {
            LOG.warn(getSpaces() + "Class or Builder is null, abort adding the builder!");

            return;
        }

        LOG.debug(getSpaces() + "Adding  builder for class : " + clazz.getName()
            + ". Added builder: " + builder.getClass().getName());
        builderMap.put(clazz.getName(), builder);
    }


    /**
     * Add a builder that is only used for the given attribute of the given class. The builders specified here have
     * higher priority than the class specific ones.
     *
     * @param  clazz  class for which the builder should be used (for arrays, use the array classes - e.g.
     *                String[].class !)
     * @param  attributeName  attribute for which the builder should be used
     * @param  builder  builder that should be used for the given attribute of the given class
     */
    public void addBuilderForClassAndAttribute(Class clazz, String attributeName, Builder builder) {

        if (clazz == null || attributeName == null || builder == null) {
            LOG.warn("Class, attributeName, or Builder is null, abort adding the builder!");

            return;
        }

        LOG.debug(getSpaces() + "adding attribute specific builder for class and attribute: " + clazz.getName() + " - "
            + attributeName
            + ". Added builder: " + builder.getClass().getName());
        classAndAttributeSpecificBuilderMap.put(clazz.getName() + "." + attributeName, builder);
    }


    /**
     * @return  copy of the map with the Builders mapped with classnames
     */
    public Map<String, Builder> getBuilderMap() {

        return new HashMap<String, Builder>(builderMap);
    }


    /**
     * @return  copy of the map with the Builders mapped with class and fieldname
     */
    public Map<String, Builder> getClassAndAttributeSpecificBuilderMap() {

        return new HashMap<String, Builder>(classAndAttributeSpecificBuilderMap);
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
                    parameters[f] = buildObject(parameterClass, field);
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

                // call the setter with the built parameters
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
                        + " Probably a bug in the used Builder, or in the setter.", ex);
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
     * Get the builder for the given fieldname (variable name) in the given class. (exmple: 'name' of the class Customer
     * searches for a builder with the key 'Customer.name').
     *
     * @param  clazz  class to get the Builder for
     * @param  field  field to get the Builder for
     *
     * @return  the Builder or null, if none was found for this combination
     */
    private Builder getSpecificBuilder(Class clazz, Field field) {

        Builder b;

        // get the builder for the class and attribute
        LOG.debug(getSpaces() + "getting Builder by class name and field name: " + clazz.getName() + " - "
            + field.getName());
        b = classAndAttributeSpecificBuilderMap.get(clazz.getName() + "." + field.getName());

        return b;
    }


    /**
     * Tries to find a Builder in the two builder maps for the given class and field.
     *
     * @param  clazz  class to get the builder for
     * @param  field  field to get the builder for
     *
     * @return  a Builder or null if none was found.
     */
    private Builder getBuilderForClassAndField(Class clazz, Field field) {

        Builder b = getSpecificBuilder(field.getDeclaringClass(), field);

        if (b == null) {
            b = getBuilder(clazz);
        }

        return b;
    }


    /**
     * Gets the builder for the specified class.
     *
     * @param  clazz  class
     *
     * @return  the Builder if found, or null.
     */
    private Builder getBuilder(Class clazz) {

        Builder b;

        // if no specific builder for this field was set, get the builder for the class of the parameter
        // (class name because of primitive types)
        LOG.debug(getSpaces() + "getting Builder by class name: " + clazz.getName());
        b = builderMap.get(clazz.getName());

        return b;
    }


    /**
     * Builds the Object for the given class and field - If no builder is found for this combination, this method tries
     * to instanciate the given class and calls the fillObject method again to try to fill in the fields of that Object.
     *
     * @param  clazz  Class of the Object
     * @param  field  Field to fill (needed to get specific builders).
     *
     * @return  the built Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    public Object buildObjectOfClassAndField(Class clazz, Field field) throws FillingException {

        Object parameter;

        Builder b = getBuilderForClassAndField(clazz, field);

        if (b != null) {
            LOG.debug(getSpaces() + "Using Builder: " + b.getClass().getName());
            parameter = b.build();
        } else {
            try {
                // if we don't have a builder for it, instanciate the object and try to fill it.
                LOG.debug(getSpaces() + "No Builder for class: " + clazz.getName()
                    + ". Trying to create a new instance of it.");
                parameter = clazz.newInstance();
            } catch (InstantiationException ex) {
                throw new FillingException("There was no Builder set for the class " + clazz.getName() + " (field '"
                    + field.getName() + "' of class " + clazz.getDeclaringClass() + "). "
                    + " So we tried to instatiate it with the default constructor, but it failed! ", ex);
            } catch (IllegalAccessException ex) {
                throw new FillingException("There was no Builder set for the class " + clazz.getName()
                    + " So we tried to instatiate it with the default constructor, but couldn't access it!", ex);
            }

            fillBean(parameter);
        }

        return parameter;
    }


    /**
     * Builds an Object that has generic Types.
     *
     * @param  clazz  Class of the Object
     * @param  pt  ParameterizedType of the class
     * @param  field  Field to fill (needed to get specific builders).
     *
     * @return  the built Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object buildObjectWithGenericTypes(Class clazz, ParameterizedType pt, Field field) throws FillingException {

        Builder b = getBuilderForClassAndField(clazz, field);

        if (b != null) {
            if (!GenericsBuilder.class.isAssignableFrom(b.getClass())) {
                throw new WrongBuilderException("The Builder got for class " + clazz.getName() + " and field "
                    + field.getName() + " does not extend " + GenericsBuilder.class.getName());
            }

            GenericsBuilder gb = (GenericsBuilder) b;

            List<Object> typeObjects = new ArrayList<Object>();

            // fill the generic objects gb.getSize() times and add them to the list.

            for (int i = 0; i < gb.getSize(); i++) {
                for (Type type : pt.getActualTypeArguments()) {
                    // get the class of the generic type and build it
                    String classString = type.toString().contains("class ") ? type.toString().split(" ")[1]
                                                                            : type.toString();

                    // The generic Type has got another generic Type e.g. List<List<String>>
                    if (classString.contains("<")) {
                        classString = classString.split("<")[0];
                    }

                    Object typeObject;

                    try {
                        typeObject = buildObject(Class.forName(classString), field, type);
                    } catch (ClassNotFoundException ex) {
                        throw new FillingException("Could not find the class " + classString
                            + " on Filling the Generic Parameters of the class " + clazz.getName()
                            + " (Tried to get it from the String '" + type.toString() + "'", ex);
                    }

                    typeObjects.add(typeObject);
                }
            }

            LOG.debug(getSpaces() + "using GenericsBuilder: " + gb.getClass().getName());

            return gb.buildWithGenerics(typeObjects);
        }

        LOG.warn(getSpaces() + "Could not find any Builder for class " + clazz.getName() + " and field "
            + field.getName());

        return null;
    }


    /**
     * Builds the given Enum Object.
     *
     * @param  clazz  Class of the Object
     * @param  field  Field to fill (needed to get specific builders).
     *
     * @return  the built Enum (or null if no builder was found or the Enum had no Enum constants)
     */
    private Object buildEnum(Class clazz, Field field) throws FillingException {

        Builder b = getBuilderForClassAndField(clazz, field);

        if (b == null) {
            // get the generic enum builder
            b = (EnumBuilder) getBuilder(java.lang.Enum.class);
        }

        if (b != null) {
            if (!EnumBuilder.class.isAssignableFrom(b.getClass())) {
                throw new WrongBuilderException("The Builder got for class " + clazz.getName() + " and field "
                    + field.getName()
                    + " does not extend " + EnumBuilder.class.getName());
            }

            EnumBuilder eb = (EnumBuilder) b;

            LOG.debug(getSpaces() + "using EnumBuilder: " + eb.getClass().getName());

            return eb.buildEnum(clazz);
        }

        LOG.warn(getSpaces() + "Could not find any Builder for class " + clazz.getName() + " and field "
            + field.getName());

        return null;
    }


    /**
     * Wrapper method for building Objects, enriches the call with the Type of the Object.
     *
     * @param  parameterClass  Class of the Object
     * @param  field  Field to fill (needed to get specific builders).
     *
     * @return  the built Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object buildObject(Class parameterClass, Field field) throws FillingException {

        Type type = field.getGenericType();

        return buildObject(parameterClass, field, type);
    }


    /**
     * Wrapper method for building Objects - delegates the work to the specific methods for different usecases.
     *
     * @param  parameterClass  Class of the Object
     * @param  field  Field to fill (needed to get specific builders).
     * @param  type  Type of the Object
     *
     * @return  the built Object.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object buildObject(Class parameterClass, Field field, Type type) throws FillingException {

        LOG.debug(getSpaces() + "Calling buildObject with class: " + parameterClass.getName());
        depth++;

        Object object;

        if (parameterClass.isEnum()) {
            object = buildEnum(parameterClass, field);
        } else if (parameterClass.isArray()) {
            object = buildArray(parameterClass, field);
        } else if (ParameterizedType.class.isAssignableFrom(type.getClass())) {
            // if this is a ParameterizedType, the Object defines Generics
            ParameterizedType paramType = (ParameterizedType) type;

            object = buildObjectWithGenericTypes(parameterClass, paramType, field);
        } else {
            object = buildObjectOfClassAndField(parameterClass, field);
        }

        depth--;
        LOG.debug(getSpaces() + "Finished building Object: " + object);

        return object;
    }


    /**
     * Method for building Arrays.
     *
     * @param  clazz  class of the Object to build.
     * @param  field  field to fill.
     *
     * @return  the built Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object buildArray(Class clazz, Field field) throws FillingException {

        Class arrayType = clazz.getComponentType();

        List<Object> objectsForArray = new ArrayList<Object>();

        for (int i = 0; i < 3; i++) {
            objectsForArray.add(buildObjectForArray(arrayType, field));
        }

        Builder b = getBuilderForClassAndField(clazz, field);

        if (b == null) {
            // get the generic array builder
            b = getBuilder(ArrayBuilder.class);
        }

        if (b != null) {
            if (!ArrayBuilder.class.isAssignableFrom(b.getClass())) {
                throw new WrongBuilderException("The Builder got for class " + clazz.getName() + " and field "
                    + field.getName()
                    + " does not extend " + ArrayBuilder.class.getName());
            }

            ArrayBuilder ab = (ArrayBuilder) b;

            LOG.debug(getSpaces() + "Using ArrayBuilder: " + ab.getClass().getName());

            return ab.buildArray(objectsForArray, arrayType);
        }

        LOG.warn(getSpaces() + "Could not find any Builder for class " + clazz.getName() + " and field "
            + field.getName());

        return null;
    }


    /**
     * Wrapper method to build an Object for an Array that enriches the call with the Type of the field.
     *
     * @param  arrayType  ComponentType class of the Array.
     * @param  field  field to fill.
     *
     * @return  the built Object or null if an error occured.
     *
     * @throws  FillingException  if an error occurred.
     */
    private Object buildObjectForArray(Class arrayType, Field field) throws FillingException {

        Type type = field.getGenericType();

        if (GenericArrayType.class.isAssignableFrom(type.getClass())) {
            // if we have an Array of a Type with Generics, this is needed!
            type = ((GenericArrayType) type).getGenericComponentType();
        }

        return buildObject(arrayType, field, type);
    }


    /**
     * Gets the default builder Map that contains the basic set of builders.
     *
     * @return  Map of Builders
     */
    private Map<String, Builder> getDefaultBuilderMap() {

        Map<String, Builder> map = new HashMap<String, Builder>();

        map.put(String.class.getName(), new org.synyx.beanfiller.builder.StringBuilder());

        IntegerBuilder integerBuilder = new IntegerBuilder();
        map.put("int", integerBuilder);
        map.put(Integer.class.getName(), integerBuilder);

        FloatBuilder floatBuilder = new FloatBuilder();
        map.put("float", floatBuilder);
        map.put(Float.class.getName(), floatBuilder);

        LongBuilder longBuilder = new LongBuilder();
        map.put("long", longBuilder);
        map.put(Long.class.getName(), longBuilder);

        DoubleBuilder doubleBuilder = new DoubleBuilder();
        map.put("double", doubleBuilder);
        map.put(Double.class.getName(), doubleBuilder);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        map.put("boolean", booleanBuilder);
        map.put(Boolean.class.getName(), booleanBuilder);

        ByteBuilder byteBuilder = new ByteBuilder();
        map.put("byte", byteBuilder);
        map.put(Byte.class.getName(), byteBuilder);

        BigIntegerBuilder bigIntegerBuilder = new BigIntegerBuilder();
        map.put(BigInteger.class.getName(), bigIntegerBuilder);

        BigDecimalBuilder bigDecimalBuilder = new BigDecimalBuilder();
        map.put(BigDecimal.class.getName(), bigDecimalBuilder);

        MapBuilder mapBuilder = new MapBuilder(new MapCriteria());
        map.put("java.util.Map", mapBuilder);

        ListBuilder listBuilder = new ListBuilder(new ListCriteria());
        map.put("java.util.List", listBuilder);

        EnumBuilder enumBuilder = new EnumBuilder();
        map.put("java.lang.Enum", enumBuilder);

        ArrayBuilder arrayBuilder = new ArrayBuilder();
        map.put("org.synyx.beanfiller.builder.ArrayBuilder", arrayBuilder);

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
