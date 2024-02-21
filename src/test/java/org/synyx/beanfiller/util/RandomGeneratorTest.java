
package org.synyx.beanfiller.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Tests for the (not-purely-wrapper-)methods of the RandomGenerator.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class RandomGeneratorTest {

    private RandomGenerator sut;

    @BeforeEach
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

        assertThat(hasReturnedMinimum, is(true));
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

        assertThat(hasReturnedMaximum, is(true));
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
