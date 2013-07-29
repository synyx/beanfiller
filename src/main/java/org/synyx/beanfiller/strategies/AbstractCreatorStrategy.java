package org.synyx.beanfiller.strategies;

import org.synyx.beanfiller.ObjectInformation;
import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.services.CreatorRegistry;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Abstract superclass for all Strategies.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public abstract class AbstractCreatorStrategy implements Comparable<AbstractCreatorStrategy> {

    /**
     * Use this if the Strategy takes just anything that the others don't cover.
     */
    public static final int PRIORITY_LOW = 10;

    /**
     * Use this if the Strategy has some prequesites for the object.
     */
    public static final int PRIORITY_MID = 50;

    /**
     * Use this if the Strategy has some more specific prequesites for the object.
     */
    public static final int PRIORITY_HIGH = 100;

    /**
     * Use this if the Strategy is for some explicit Type of Object and should always be used for it.
     */
    public static final int PRIORITY_HIGHEST = 999;
    private int priority;
    private CreatorRegistry creatorRegistry;
    private StrategyManager strategyManager;

    public AbstractCreatorStrategy(int priority) {

        this.priority = priority;
    }

    /**
     * Returns if this Strategy can handle the specified object.
     *
     * @param  objectInformation
     *
     * @return  true if the given class can be handled by this Strategy, false otherwise.
     */
    public abstract boolean canHandle(ObjectInformation objectInformation);


    /**
     * Creates an Object of the given class.
     *
     * @param  objectInformation
     *
     * @return
     *
     * @throws  CreationException
     */
    public abstract Object createObject(ObjectInformation objectInformation) throws FillingException;


    public int getPriority() {

        return priority;
    }


    public void setCreatorRegistry(CreatorRegistry creatorRegistry) {

        this.creatorRegistry = creatorRegistry;
    }


    public CreatorRegistry getCreatorRegistry() {

        return creatorRegistry;
    }


    public StrategyManager getStrategyManager() {

        return strategyManager;
    }


    public void setStrategyManager(StrategyManager strategyManager) {

        this.strategyManager = strategyManager;
    }


    @Override
    public int compareTo(AbstractCreatorStrategy o) {

        int i = Integer.compare(this.priority, o.priority) * -1;

        if (i == 0) {
            return this.getClass().getName().compareTo(o.getClass().getName());
        }

        return i;
    }


    /**
     * Check if the given class is an Enum.
     *
     * @param  clazz
     *
     * @return
     */
    protected boolean isEnum(Class clazz) {

        return clazz.isEnum();
    }


    /**
     * Check if the given class is an Array.
     *
     * @param  clazz
     *
     * @return
     */
    protected boolean isArray(Class clazz) {

        return clazz.isArray();
    }


    /**
     * Check if the given type has Generics.
     *
     * @param  clazz
     *
     * @return
     */
    protected boolean hasGenerics(Type type) {

        // check if the type is a ParameterizedType or a GenericArrayType. In both cases, the field has Generics.
        if (ParameterizedType.class.isAssignableFrom(type.getClass())
                || GenericArrayType.class.isAssignableFrom(type.getClass())) {
            return true;
        }

        return false;
    }


    /**
     * Check if the given class is a java.util.Collection.
     *
     * @param  clazz
     *
     * @return
     */
    protected boolean isCollection(Class clazz) {

        return Collection.class.isAssignableFrom(clazz);
    }


    /**
     * Gets a creator for the given objectInformation.
     *
     * @param  objectInformation
     *
     * @return  a creator, or null if none was found
     */
    protected Creator getCreator(ObjectInformation objectInformation) {

        return getCreatorRegistry().getCreator(objectInformation.getClazz(), objectInformation.getField());
    }


    /**
     * Gets a List of ObjectInformation of the generic types of the given Object.
     *
     * @param  objectInformation
     *
     * @return
     *
     * @throws  CreationException
     */
    protected List<ObjectInformation> getTypeArgumentObjectInformation(ObjectInformation objectInformation)
        throws FillingException {

        List<ObjectInformation> typeArgumentObjectInformationList = new ArrayList<ObjectInformation>();

        Type genericType = objectInformation.getField().getGenericType();

        if (hasGenerics(genericType)) {
            if (GenericArrayType.class.isAssignableFrom(genericType.getClass())) {
                // if we have an Array of a Type with Generics, this is needed!
                genericType = ((GenericArrayType) genericType).getGenericComponentType();
            }

            ParameterizedType objectType = (ParameterizedType) genericType;

            for (Type type : objectType.getActualTypeArguments()) {
                String classString = null;

                try {
                    // get the class of the generic type and create it
                    classString = type.toString().contains("class ") ? type.toString().split(" ")[1] : type.toString();

                    // The generic Type has got another generic Type e.g. List<List<String>>
                    if (classString.contains("<")) {
                        classString = classString.split("<")[0];
                    }

                    ObjectInformation typeArgumentObjectInformation = new ObjectInformation(Class.forName(classString),
                            objectInformation.getField(), type, null, null);
                    typeArgumentObjectInformationList.add(typeArgumentObjectInformation);
                } catch (ClassNotFoundException ex) {
                    throw new FillingException("Could not find the class " + classString
                        + " on Filling the Generic Parameters of the class " + objectInformation.getClazz().getName()
                        + " (Tried to get it from the String '" + type.toString() + "'", ex);
                }
            }
        }

        return typeArgumentObjectInformationList;
    }


    protected Object createObjectFromObjectInformation(ObjectInformation objectInformation) throws FillingException {

        AbstractCreatorStrategy strategy = getStrategyManager().getStrategyFor(objectInformation);

        return strategy.createObject(objectInformation);
    }


    protected List<Object> createObjectNumberOfTimes(ObjectInformation objectInformation, int number)
        throws FillingException {

        List<Object> objectList = new ArrayList<Object>();

        for (int i = 0; i < number; i++) {
            Object typeObject = createObjectFromObjectInformation(objectInformation);
            objectList.add(typeObject);
        }

        return objectList;
    }


    /**
     * Checks if the given creator is or derives from the given desiredCreatorClass.
     *
     * @param  creator
     * @param  desiredCreatorClass
     * @param  objectInformation
     *
     * @throws  WrongCreatorException  if the creator is not or does not derive from the desiredCreatorClass
     */
    protected void checkCreatorHasRightClass(Creator creator, Class desiredCreatorClass,
        ObjectInformation objectInformation) throws WrongCreatorException {

        if (!desiredCreatorClass.isAssignableFrom(creator.getClass())) {
            throw new WrongCreatorException("The Creator got for class " + objectInformation.getClazz().getName()
                + " and field " + objectInformation.getField().getName()
                + "  is not or does not derive from " + desiredCreatorClass.getName() + ", but is: "
                + creator.getClass().getName());
        }
    }
}
