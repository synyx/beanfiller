
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.synyx.beanfiller.creator.StringCreator;
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

    @Test
    public void testObjectIsCreated() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("BaseObject is null!", baseObject);
    }


    @Test
    public void testStringIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("String is not beeing filled!", baseObject.getText());
    }


    @Test
    public void testIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("Integer is not beeing filled!", baseObject.getIntegerNumber());
    }


    @Test
    public void testPrimitiveIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("primitive Integer (int) is not beeing filled!", baseObject.getPrimitiveIntNumber());
    }


    @Test
    public void testFloatIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("Float is not beeing filled!", baseObject.getFloatNumber());
    }


    @Test
    public void testPrimitiveFloatIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("primitive Float (float) is not beeing filled!", baseObject.getPrimitiveFloatNumber());
    }


    @Test
    public void testLongIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("Long is not beeing filled!", baseObject.getLongNumber());
    }


    @Test
    public void testPrimitiveLongIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("primitive Long (long) is not beeing filled!", baseObject.getPrimitiveLongNumber());
    }


    @Test
    public void testDoubleIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("Double is not beeing filled!", baseObject.getDoubleNumber());
    }


    @Test
    public void testPrimitiveDoubleIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("primitive Double (double) is not beeing filled!", baseObject.getPrimitiveDouble());
    }


    @Test
    public void testBooleanIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("Boolean is not beeing filled!", baseObject.getBooleanObject());
    }


    @Test
    public void testPrimitiveBooleanIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("primitive Boolean (boolean) is not beeing filled!", baseObject.getPrimitiveBoolean());
    }


    @Test
    public void testByteIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("Byte is not beeing filled!", baseObject.getByteObject());
    }


    @Test
    public void testPrimitiveByteIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("primitive Byte (byte) is not beeing filled!", baseObject.getPrimitiveByte());
    }


    @Test
    public void testBigIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("BigInteger is not beeing filled!", baseObject.getBigIntegerNumber());
    }


    @Test
    public void testBigDecimalIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("BigDecimal is not beeing filled!", baseObject.getBigDecimalNumber());
    }


    @Test
    public void testDateIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(new BaseObject());
        Assert.assertNotNull("Date is not beeing filled!", baseObject.getDate());
    }


    public void testAddedCreatorIsUsed() throws FillingException {

        StringCreator stringCreator = mock(StringCreator.class);
        when(stringCreator.create()).thenReturn("test");

        beanfiller.addCreator(String.class, stringCreator);

        beanfiller.fillBean(new ArraysObject());

        // assert that our mock was called
        verify(stringCreator).create();
    }


    public void testAddedSpecificCreatorIsUsed() throws FillingException {

        StringCreator stringCreator = mock(StringCreator.class);
        when(stringCreator.create()).thenReturn("test");

        beanfiller.addCreatorForClassAndAttribute(BaseObject.class, "string", stringCreator);

        beanfiller.fillBean(new ArraysObject());

        // assert that our mock was called
        verify(stringCreator).create();
    }
}
