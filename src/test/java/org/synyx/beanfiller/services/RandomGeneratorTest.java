
package org.synyx.beanfiller.services;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.powermock.api.mockito.PowerMockito;

import org.powermock.core.classloader.annotations.PrepareForTest;

import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;


/**
 * Tests for the (not-purely-wrapper-)methods of the RandomGenerator.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ RandomGenerator.class })
public class RandomGeneratorTest {

    @Test
    public void testGetIntBetweenCanReturnMinimum() {

        PowerMockito.mockStatic(RandomGenerator.class);

        int min = 0;
        int max = 5;

        when(RandomGenerator.getRandomInt(Matchers.anyInt())).thenReturn(min);

        int number = RandomGenerator.getRandomIntBetween(min, max);

        Assert.assertEquals("RandomGenerator did not return the minimum number!", min, number);
    }


    @Test
    public void testGetIntBetweenCanReturnMaximum() {

        PowerMockito.mockStatic(RandomGenerator.class);

        int min = 0;
        int max = 5;

        when(RandomGenerator.getRandomInt(Matchers.anyInt())).thenReturn(max);

        int number = RandomGenerator.getRandomIntBetween(min, max);

        Assert.assertEquals("RandomGenerator did not return the maximum number!", min, number);
    }
}
