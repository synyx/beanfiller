
package org.synyx.beanfiller.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.junit.runners.JUnit4;

import static org.junit.Assert.fail;


/**
 * Tests for the (not-purely-wrapper-)methods of the RandomGenerator.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
@RunWith(JUnit4.class)
public class RandomGeneratorTest {

    private RandomGenerator sut;

    @Before
    public void setUp() {

        sut = new RandomGenerator();
    }


    @Test
    public void testGetIntBetweenCanReturnMinimum() {

        int min = 0;
        int max = 2;

        boolean hasReturnedMinimum = false;

        for (int i = 0; i < 1000; i++) {
            int number = sut.getRandomIntBetween(min, max);

            if (number == min) {
                hasReturnedMinimum = true;

                break;
            }
        }

        Assert.assertTrue("Minimum was never returned!", hasReturnedMinimum);
    }


    @Test
    public void testGetIntBetweenCanReturnMaximum() {

        int min = 0;
        int max = 2;

        boolean hasReturnedMaximum = false;

        for (int i = 0; i < 1000; i++) {
            int number = sut.getRandomIntBetween(min, max);

            if (number == max) {
                hasReturnedMaximum = true;

                break;
            }
        }

        Assert.assertTrue("Maximum was never returned!", hasReturnedMaximum);
    }


    @Test
    public void testGetIntBetweenDoesNotExceedMinAndMax() {

        int min = 0;
        int max = 2;

        for (int i = 0; i < 1000; i++) {
            int number = sut.getRandomIntBetween(min, max);

            if (number < min || number > max) {
                fail("Random generator broke the bounds (%d - %d) with number: " + number);

                break;
            }
        }
    }
}
