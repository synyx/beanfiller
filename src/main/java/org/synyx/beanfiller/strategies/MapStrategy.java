package org.synyx.beanfiller.strategies;

import org.synyx.beanfiller.ObjectInformation;
import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.MapCreator;
import org.synyx.beanfiller.exceptions.FillingException;

import java.util.ArrayList;
import java.util.List;


/**
 * Handles java.util.Map.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class MapStrategy extends AbstractCreatorStrategy {

    public MapStrategy() {

        super(PRIORITY_HIGHEST);
    }

    @Override
    public boolean canHandle(ObjectInformation objectInformation) {

        return java.util.Map.class.isAssignableFrom(objectInformation.getClazz());
    }


    @Override
    public Object createObject(ObjectInformation objectInformation) throws FillingException {

        Creator creator = getCreator(objectInformation);

        if (creator != null) {
            checkCreatorHasRightClass(creator, MapCreator.class, objectInformation);

            MapCreator mapCreator = (MapCreator) creator;

            // get the ObjectInformations from the TypeArguments of the map.

            List<ObjectInformation> typeArguments = getTypeArgumentObjectInformation(objectInformation);

            // get the keys -  because this is a map, the first entry of the list are the keys.
            List<Object> keys = getKeys(typeArguments.get(0), mapCreator);

            // get the values - because this is a map, the second entry of our list are the values.
            List<Object> values = getValues(typeArguments.get(1), mapCreator);

            return mapCreator.create(keys, values);
        }

        return null;
    }


    /**
     * Create the keys from the given ObjectInformation and MapCreator.
     *
     * @param  mapCreator
     * @param  keyObjectInformation
     *
     * @return
     *
     * @throws  CreationException
     */
    protected List<Object> getKeys(ObjectInformation keyObjectInformation, MapCreator mapCreator)
        throws FillingException {

        List<Object> keys;

        if (mapCreator.hasKeys()) {
            keys = mapCreator.getKeys();
        } else {
            keys = createObjectNumberOfTimes(keyObjectInformation, mapCreator.getSize());
        }

        return keys;
    }


    /**
     * Create the values from the given ObjectInformation and MapCreator.
     *
     * @param  valueInformation
     * @param  mapCreator
     *
     * @return
     *
     * @throws  CreationException
     */
    protected List<Object> getValues(ObjectInformation valueInformation, MapCreator mapCreator)
        throws FillingException {

        List<Object> values = new ArrayList<Object>();

        for (int i = 0; i < mapCreator.getSize(); i++) {
            Object typeObject = createObjectFromObjectInformation(valueInformation);
            values.add(typeObject);
        }

        return values;
    }
}
