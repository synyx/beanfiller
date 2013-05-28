
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.builder.Builder;
import org.synyx.beanfiller.builder.EnumBuilder;
import org.synyx.beanfiller.testobjects.EnumsObject;
import org.synyx.beanfiller.testobjects.TestEnum;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for Enums.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class EnumTest {

    private BeanFiller beanfiller = new BeanFiller();

    @Test
    public void testEnumIsFilled() throws FillingException {

        EnumsObject o = beanfiller.fillBean(new EnumsObject());

        Assert.assertNotNull("TestEnum was not filled!", o.getTestEnum());
    }


    @Test
    public void testErrorEnumIsNotFilled() throws FillingException {

        // The ErrorEnum has no Enum fields set and should therefore not be filled (because theres nothing to fill it with).
        EnumsObject o = beanfiller.fillBean(new EnumsObject());

        Assert.assertNull("ErrorEnum should not be filled!", o.getEmptyEnum());
    }


    @Test
    public void testAddedEnumBuilderIsUsed() throws FillingException {

        EnumBuilder enumBuilder = mock(EnumBuilder.class);
        when(enumBuilder.buildEnum(TestEnum.class)).thenReturn(TestEnum.TEST1);
        beanfiller.addBuilder(TestEnum.class, enumBuilder);

        beanfiller.fillBean(new EnumsObject());

        // assert that our mock was called instead of the default EnumBuilder
        verify(enumBuilder).buildEnum(TestEnum.class);
    }


    @Test
    public void testAddedSpecificEnumBuilderIsUsed() throws FillingException {

        EnumBuilder enumBuilder = mock(EnumBuilder.class);
        when(enumBuilder.buildEnum(TestEnum.class)).thenReturn(TestEnum.TEST1);

        beanfiller.addBuilderForClassAndAttribute(EnumsObject.class, "testEnum", enumBuilder);

        beanfiller.fillBean(new EnumsObject());

        // assert that our mock was called instead of the default EnumBuilder
        verify(enumBuilder).buildEnum(TestEnum.class);
    }


    @Test(expected = WrongBuilderException.class)
    public void testWrongBuilderExceptionIsThrownIfNonEnumBuilderIsUsed() throws FillingException {

        Builder stringBuilder = new org.synyx.beanfiller.builder.StringBuilder();

        beanfiller.addBuilderForClassAndAttribute(EnumsObject.class, "testEnum", stringBuilder);

        beanfiller.fillBean(new EnumsObject());
    }
}
