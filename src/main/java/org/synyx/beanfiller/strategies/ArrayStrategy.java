package org.synyx.beanfiller.strategies;

import org.synyx.beanfiller.creator.ArrayCreator;
import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

import java.util.List;


/**
 * Handles all types of Arrays.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayStrategy extends AbstractCreatorStrategy {

    /**
     * Creates new ArrayStrategy.
     */
    public ArrayStrategy() {

        super(PRIORITY_HIGH);
    }

    @Override
    public boolean canHandle(ObjectInformation objectInformation) {

        return isArray(objectInformation.getClazz());
    }


    @Override
    public Object createObjectInternal(ObjectInformation objectInformation) throws FillingException {

        Creator creator = getArrayCreator(objectInformation);

        if (creator != null) {
            checkCreatorHasRightClass(creator, ArrayCreator.class, objectInformation);

            ArrayCreator arrayCreator = (ArrayCreator) creator;

            Class arrayType = objectInformation.getClazz().getComponentType();

            List<Object> objectsForArray = createObjectsForArray(arrayType, objectInformation.getField(),
                    arrayCreator.getSize(), objectInformation);

            return arrayCreator.createArray(objectsForArray, arrayType);
        }

        return null;
    }


    /**
     * Creates the values for the Array that should be created.
     *
     * @param  arrayType  Type of the Array.
     * @param  field  Field of the Array.
     * @param  size  size the array should have.
     * @param  parentObjectInformation  the ParentObjectInformation of the Array.
     * @param  <T>  the Class of the Type.
     *
     * @return  List of Type <T> with the created Objects for the Array.
     *
     * @throws  FillingException
     */
    private <T> List<T> createObjectsForArray(Class<T> arrayType, Field field, int size,
        ObjectInformation parentObjectInformation) throws FillingException {

        Type type = field == null ? null : field.getGenericType();

        if (type != null && GenericArrayType.class.isAssignableFrom(type.getClass())) {
            // if we have an Array of a Type with Generics, this is needed!
            type = ((GenericArrayType) type).getGenericComponentType();
        }

        ObjectInformation information = new ObjectInformation(arrayType, field, type, null, null,
                parentObjectInformation);

        return (List<T>) createObjectNumberOfTimes(information, size);
    }


    /**
     * Get the Creator for the given ObjectInformation.
     *
     * @param  objectInformation  ObjectInformation to get the Creator for.
     *
     * @return  the found Creator or null if none was found for the ObjectInformation.
     */
    private Creator getArrayCreator(ObjectInformation objectInformation) {

        Creator creator = getCreator(objectInformation);

        if (creator == null) {
            creator = getCreatorRegistry().getCreatorForClass(ArrayCreator.class);
        }

        return creator;
    }
}
