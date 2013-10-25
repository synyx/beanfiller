package org.synyx.beanfiller.strategies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.services.CreatorRegistry;
import org.synyx.beanfiller.util.GenericsUtils;

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

    private static final Logger LOG = LoggerFactory.getLogger(AbstractCreatorStrategy.class);
    private final Integer priority;
    private CreatorRegistry creatorRegistry;
    private StrategyManager strategyManager;

    public AbstractCreatorStrategy(Integer priority) {

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
     * Creates an Object for the given ObjectInformation - In here it is checked if this step would create a creation
     * cycle and if it would, null is returned.
     *
     * @param  objectInformation
     *
     * @return  the created Object or null if an error occurred or if the Object would create a cycle.
     *
     * @throws  FillingException
     */
    public Object createObject(ObjectInformation objectInformation) throws FillingException {

        // if the next step would introduce a cycle, don't create that object!
        if (!objectInformation.getHistory().isRepeating()) {
            return createObjectInternal(objectInformation);
        } else {
            LOG.warn(String.format(
                    "Detected cycle introduced by class %s (field %s on class %s). Aborting creation of this branch! ",
                    objectInformation.getClazz().getName(), objectInformation.getField().getName(),
                    objectInformation.getField().getDeclaringClass()));

            return null;
        }
    }


    /**
     * Creates an Object for the given ObjectInformation.
     *
     * @param  objectInformation
     *
     * @return  the created Object
     *
     * @throws  FillingException
     */
    protected abstract Object createObjectInternal(ObjectInformation objectInformation) throws FillingException;


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

        // Highest priority first
        int i = this.priority.compareTo(o.priority) * -1;

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
     * @return  List of ObjectInformation
     *
     * @throws  CreationException
     */
    protected List<ObjectInformation> getTypeArgumentObjectInformation(ObjectInformation objectInformation)
        throws FillingException {

        List<ObjectInformation> typeArgumentObjectInformationList = new ArrayList<ObjectInformation>();

        List<Type> actualTypeArguments = GenericsUtils.getActualTypeArguments(objectInformation.getField());

        for (Type type : actualTypeArguments) {
            typeArgumentObjectInformationList.add(createObjectInformationForType(type, objectInformation));
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


    private ObjectInformation createObjectInformationForType(Type type, ObjectInformation parentObjectInformation)
        throws FillingException {

        try {
            Class clazz = GenericsUtils.getClassForType(type);

            return new ObjectInformation(clazz, parentObjectInformation.getField(), type, null, null,
                    parentObjectInformation);
        } catch (ClassNotFoundException ex) {
            throw new FillingException("Could not find the class of the type " + type.toString()
                + " on Filling the Generic Parameters of the class " + parentObjectInformation.getClazz().getName()
                + " (Tried to get it from the String '" + type.toString() + "'", ex);
        }
    }
}
