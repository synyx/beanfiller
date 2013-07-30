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
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayStrategy extends AbstractCreatorStrategy {

    public ArrayStrategy() {

        super(PRIORITY_HIGH);
    }

    @Override
    public boolean canHandle(ObjectInformation objectInformation) {

        return isArray(objectInformation.getClazz());
    }


    @Override
    public Object createObject(ObjectInformation objectInformation) throws FillingException {

        Creator creator = getArrayCreator(objectInformation);

        if (creator != null) {
            checkCreatorHasRightClass(creator, ArrayCreator.class, objectInformation);

            ArrayCreator arrayCreator = (ArrayCreator) creator;

            Class arrayType = objectInformation.getClazz().getComponentType();

            List<Object> objectsForArray = createObjectsForArray(arrayType, objectInformation.getField(),
                    arrayCreator.getSize());

            return arrayCreator.createArray(objectsForArray, arrayType);
        }

        return null;
    }


    private <T> List<T> createObjectsForArray(Class<T> arrayType, Field field, int size) throws FillingException {

        Type type = field.getGenericType();

        if (GenericArrayType.class.isAssignableFrom(type.getClass())) {
            // if we have an Array of a Type with Generics, this is needed!
            type = ((GenericArrayType) type).getGenericComponentType();
        }

        ObjectInformation information = new ObjectInformation(arrayType, field, type, null, null);

        return (List<T>) createObjectNumberOfTimes(information, size);
    }


    private Creator getArrayCreator(ObjectInformation objectInformation) {

        Creator creator = getCreator(objectInformation);

        if (creator == null) {
            creator = getCreatorRegistry().getCreatorForClass(ArrayCreator.class);
        }

        return creator;
    }
}
