package org.synyx.beanfiller.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Builder class for Enums - Extend this if you write your own Enum Builders.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class EnumBuilder implements Builder<Object> {

    private static final Logger LOG = LoggerFactory.getLogger(EnumBuilder.class);

    /**
     * Build the enum of the given class.
     *
     * @param  clazz
     *
     * @return  the built Enum or null, if no EnumConstants are defined in the Enum class.
     */
    public Object buildEnum(Class clazz) {

        Object[] enumArray = clazz.getEnumConstants();

        if (enumArray.length > 0) {
            List<Object> enumList = Arrays.asList(enumArray);

            Collections.shuffle(enumList);

            return enumList.get(0);
        }

        LOG.warn("No EnumConstants defined in Enum class: " + clazz.getName());

        return null;
    }


    /**
     * Returns null for Enums, because it doesn't work without the actual class here.
     *
     * @return  null
     */
    @Override
    public Object build() {

        return null;
    }
}
