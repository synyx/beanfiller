
package org.synyx.beanfiller.strategies;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.lessThan;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class StrategyManagerTest {

    @Test
    public void testStrategiesInRightOrder() {

        StrategyManager manager = new StrategyManager(null);

        Integer lastPriority = null;

        for (AbstractCreatorStrategy strategy : manager.getStrategies()) {
            int currentPriority = strategy.getPriority();

            if (lastPriority != null) {
                Assert.assertThat(currentPriority, is(anyOf(lessThan(lastPriority), equalTo(lastPriority))));
            }

            lastPriority = currentPriority;
        }
    }
}
