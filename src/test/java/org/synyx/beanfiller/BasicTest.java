
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.synyx.beanfiller.builder.StringBuilder;
import org.synyx.beanfiller.testobjects.ArraysObject;
import org.synyx.beanfiller.testobjects.BaseObject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for the basic types.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class BasicTest {

    private BeanFiller beanfiller = new BeanFiller();
    private BaseObject baseObject;

    @Before
    public void setup() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
    }


    @Test
    public void testObjectIsCreated() {

        Assert.assertNotNull("BaseObject is null!", baseObject);
    }


    @Test
    public void testStringIsFilled() {

        Assert.assertNotNull("String is not beeing filled!", baseObject.getText());
    }


    @Test
    public void testIntegerIsFilled() {

        Assert.assertNotNull("Integer is not beeing filled!", baseObject.getIntegerNumber());
    }


    @Test
    public void testPrimitiveIntegerIsFilled() {

        Assert.assertNotNull("primitive Integer (int) is not beeing filled!", baseObject.getPrimitiveIntNumber());
    }


    @Test
    public void testFloatIsFilled() {

        Assert.assertNotNull("Float is not beeing filled!", baseObject.getFloatNumber());
    }


    @Test
    public void testPrimitiveFloatIsFilled() {

        Assert.assertNotNull("primitive Float (float) is not beeing filled!", baseObject.getPrimitiveFloatNumber());
    }


    @Test
    public void testLongIsFilled() {

        Assert.assertNotNull("Long is not beeing filled!", baseObject.getLongNumber());
    }


    @Test
    public void testPrimitiveLongIsFilled() {

        Assert.assertNotNull("primitive Long (long) is not beeing filled!", baseObject.getPrimitiveLongNumber());
    }


    @Test
    public void testDoubleIsFilled() {

        Assert.assertNotNull("Double is not beeing filled!", baseObject.getDoubleNumber());
    }


    @Test
    public void testPrimitiveDoubleIsFilled() {

        Assert.assertNotNull("primitive Double (double) is not beeing filled!", baseObject.getPrimitiveDouble());
    }


    @Test
    public void testBooleanIsFilled() {

        Assert.assertNotNull("Boolean is not beeing filled!", baseObject.getBooleanObject());
    }


    @Test
    public void testPrimitiveBooleanIsFilled() {

        Assert.assertNotNull("primitive Boolean (boolean) is not beeing filled!", baseObject.getPrimitiveBoolean());
    }


    @Test
    public void testByteIsFilled() {

        Assert.assertNotNull("Byte is not beeing filled!", baseObject.getByteObject());
    }


    @Test
    public void testPrimitiveByteIsFilled() {

        Assert.assertNotNull("primitive Byte (byte) is not beeing filled!", baseObject.getPrimitiveByte());
    }


    @Test
    public void testBigIntegerIsFilled() {

        Assert.assertNotNull("BigInteger is not beeing filled!", baseObject.getBigIntegerNumber());
    }


    @Test
    public void testBigDecimalIsFilled() {

        Assert.assertNotNull("BigDecimal is not beeing filled!", baseObject.getBigDecimalNumber());
    }


    @Test
    public void testDateIsFilled() {

        Assert.assertNotNull("Date is not beeing filled!", baseObject.getDate());
    }


    public void testAddedBuilderIsUsed() throws FillingException {

        StringBuilder stringBuilder = mock(StringBuilder.class);
        when(stringBuilder.build()).thenReturn("test");

        beanfiller.addBuilder(String.class, stringBuilder);

        beanfiller.fillBean(new ArraysObject());

        // assert that our mock was called
        verify(stringBuilder).build();
    }


    public void testAddedSpecificBuilderIsUsed() throws FillingException {

        StringBuilder stringBuilder = mock(StringBuilder.class);
        when(stringBuilder.build()).thenReturn("test");

        beanfiller.addBuilderForClassAndAttribute(BaseObject.class, "string", stringBuilder);

        beanfiller.fillBean(new ArraysObject());

        // assert that our mock was called
        verify(stringBuilder).build();
    }
}
