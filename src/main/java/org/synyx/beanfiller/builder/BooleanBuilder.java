
package org.synyx.beanfiller.builder;

import java.util.Random;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class BooleanBuilder implements Builder<Boolean> {

    @Override
    public Boolean build() {

        Random rand = new Random(System.currentTimeMillis());

        return rand.nextBoolean();
    }
}
