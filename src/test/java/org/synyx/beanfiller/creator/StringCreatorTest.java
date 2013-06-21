package org.synyx.beanfiller.creator;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;

import org.powermock.core.classloader.annotations.PrepareForTest;

import org.powermock.modules.junit4.PowerMockRunner;

import org.synyx.beanfiller.criteria.StringCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import static org.mockito.Mockito.when;


/**
 * Tests for the StringCreator class.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ RandomGenerator.class })
public class StringCreatorTest {

    @Test
    public void testStringCreatorUsesCorrectMinimumLength() {

        PowerMockito.mockStatic(RandomGenerator.class);

        int min = 2;
        int max = 5;

        // ensure that the RandomGenerator returns the min value as we only want to test what the StringCreator does with this value.
        when(RandomGenerator.getRandomIntBetween(min, max)).thenReturn(min);

        StringCreator stringCreator = new StringCreator(new StringCriteria(min, max, "A"));
        String string = stringCreator.create();

        Assert.assertEquals("StringCreator has not used the given minimum length!", min, string.length());
    }


    @Test
    public void testStringCreatorUsesCorrectMaximumLength() {

        PowerMockito.mockStatic(RandomGenerator.class);

        int min = 2;
        int max = 5;

        // ensure that the RandomGenerator returns the max value as we only want to test what the StringCreator does with this value.
        when(RandomGenerator.getRandomIntBetween(min, max)).thenReturn(max);

        StringCreator stringCreator = new StringCreator(new StringCriteria(min, max, "A"));
        String string = stringCreator.create();

        Assert.assertEquals("StringCreator has not used the given maximum length!", max, string.length());
    }


    @Test
    public void testStringCreatorUsesFirstLetterOfCharset() {

        PowerMockito.mockStatic(RandomGenerator.class);

        int min = 10;
        int max = 10;
        String charset = "ABCDEFG";

        // always return 0, to ensure the first possible character is used.
        when(RandomGenerator.getRandomInt(charset.length())).thenReturn(0);

        StringCreator stringCreator = new StringCreator(new StringCriteria(min, max, charset));
        String string = stringCreator.create();

        for (int i = 0; i < string.length(); i++) {
            Assert.assertEquals("StringCreator did not only use the first letter of the charset.", "A",
                string.charAt(i));
        }
    }


    @Test
    public void testStringCreatorUsesLastLetterOfCharset() {

        PowerMockito.mockStatic(RandomGenerator.class);

        int min = 10;
        int max = 10;
        String charset = "ABCDEFG";

        // always return the length of the charset, to ensure the last possible character is used.
        when(RandomGenerator.getRandomInt(charset.length())).thenReturn(charset.length());

        StringCreator stringCreator = new StringCreator(new StringCriteria(min, max, charset));
        String string = stringCreator.create();

        for (int i = 0; i < string.length(); i++) {
            Assert.assertEquals("StringCreator did not only use the last letter of the charset.", "G",
                string.charAt(i));
        }
    }
}
