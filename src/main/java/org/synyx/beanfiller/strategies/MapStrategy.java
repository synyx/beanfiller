package org.synyx.beanfiller.strategies;

import org.synyx.beanfiller.creator.Creator;
import org.synyx.beanfiller.creator.MapCreator;
import org.synyx.beanfiller.domain.ObjectInformation;
import org.synyx.beanfiller.exceptions.FillingException;

import java.util.ArrayList;
import java.util.List;


/**
 * Handles java.util.Map.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class MapStrategy extends AbstractCreatorStrategy {

    /**
     * Creates new MapStrategy.
     */
    public MapStrategy() {

        super(PRIORITY_HIGHEST);
    }

    @Override
    public boolean canHandle(ObjectInformation objectInformation) {

        return java.util.Map.class.isAssignableFrom(objectInformation.getClazz());
    }


    @Override
    public Object createObjectInternal(ObjectInformation objectInformation) throws FillingException {

        Creator creator = getCreator(objectInformation);

        if (creator != null) {
            checkCreatorHasRightClass(creator, MapCreator.class, objectInformation);

            MapCreator mapCreator = (MapCreator) creator;

            // get the ObjectInformation from the TypeArguments of the map.

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
     * @param  mapCreator  MapCreator to use.
     * @param  keyObjectInformation  ObjectInformation of the Maps Key Type.
     *
     * @return  List of the created Keys.
     *
     * @throws  FillingException
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
     * @param  valueInformation  ObjectInformation of the Maps value Type.
     * @param  mapCreator  MapCreator to use.
     *
     * @return  List of the created values.
     *
     * @throws  FillingException
     */
    protected List<Object> getValues(ObjectInformation valueInformation, MapCreator mapCreator)
        throws FillingException {

        List<Object> values = new ArrayList<>();

        for (int i = 0; i < mapCreator.getSize(); i++) {
            Object typeObject = createObjectFromObjectInformation(valueInformation);
            values.add(typeObject);
        }

        return values;
    }
}
