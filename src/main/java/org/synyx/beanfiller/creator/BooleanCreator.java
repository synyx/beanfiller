
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.util.RandomGenerator;


/**
 * Creator for Booleans.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BooleanCreator implements SimpleCreator<Boolean> {

    private final RandomGenerator randomGenerator;

    public BooleanCreator(RandomGenerator randomGenerator) {

        this.randomGenerator = randomGenerator;
    }

    @Override
    public Boolean create() {

        return randomGenerator.getRandomBoolean();
    }
}
