
package org.synyx.beanfiller;

import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.creator.EnumCreator;
import org.synyx.beanfiller.creator.SimpleEnumCreator;
import org.synyx.beanfiller.creator.StringCreator;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.exceptions.WrongCreatorException;
import org.synyx.beanfiller.testobjects.BaseObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


/**
 * Tests for the basic types.
 *
 * @author Tobias Knell - knell@synyx.de
 */
public class BasicTest {

    private final BeanFiller beanfiller = new BeanFiller();
    private BaseObject baseObject;

    @Test
    public void testObjectIsCreated() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject, notNullValue());
    }


    @Test
    public void testStringIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getText(), notNullValue());
    }


    @Test
    public void testIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getIntegerNumber(), notNullValue());
    }


    @Test
    public void testPrimitiveIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getPrimitiveIntNumber(), greaterThan(0));
    }


    @Test
    public void testFloatIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getFloatNumber(), notNullValue());
    }


    @Test
    public void testPrimitiveFloatIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getPrimitiveFloatNumber(), greaterThan(0F));
    }


    @Test
    public void testLongIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getLongNumber(), notNullValue());
    }


    @Test
    public void testPrimitiveLongIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getPrimitiveLongNumber(), greaterThan(0L));
    }


    @Test
    public void testDoubleIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getDoubleNumber(), notNullValue());
    }


    @Test
    public void testPrimitiveDoubleIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getPrimitiveDouble(), greaterThan(0D));
    }


    @Test
    public void testBooleanIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getBooleanObject(), notNullValue());
    }


    @Test
    public void testPrimitiveBooleanIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getPrimitiveBoolean(), notNullValue());
    }


    @Test
    public void testByteIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getByteObject(), notNullValue());
    }


    @Test
    public void testPrimitiveByteIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getPrimitiveByte(), is(not(0)));
    }


    @Test
    public void testBigIntegerIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getBigIntegerNumber(), notNullValue());
    }


    @Test
    public void testBigDecimalIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getBigDecimalNumber(), notNullValue());
    }


    @Test
    public void testDateIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getDate(), notNullValue());
    }


    @Test
    public void testObjectShortIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getObjectShort(), notNullValue());
    }


    @Test
    public void testPrimitiveShortIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getPrimitiveShort(), greaterThan((short)0));
    }


    @Test
    public void testCharacterIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getCharacter(), notNullValue());
    }


    @Test
    public void testPrimitiveCharacterIsFilled() throws FillingException {

        baseObject = beanfiller.fillBean(BaseObject.class);
        assertThat(baseObject.getPrimitiveCharacter(), greaterThan((char)0));
    }


    @Test
    public void testAddedCreatorIsUsed() throws FillingException {

        StringCreator stringCreator = mock(StringCreator.class);
        when(stringCreator.create()).thenReturn("test");

        beanfiller.addCreator(String.class, stringCreator);

        beanfiller.fillBean(BaseObject.class);

        // assert that our mock was called
        verify(stringCreator).create();
    }


    @Test
    public void testAddedSpecificCreatorIsUsed() throws FillingException {

        StringCreator stringCreator = mock(StringCreator.class);
        when(stringCreator.create()).thenReturn("test");

        beanfiller.addCreatorForClassAndAttribute(BaseObject.class, "text", stringCreator);

        beanfiller.fillBean(BaseObject.class);

        // assert that our mock was called
        verify(stringCreator).create();
    }


    @Test
    public void testWrongCreatorExceptionIsThrownIfNonSimpleCreatorIsUsed() {

        EnumCreator enumCreator = new SimpleEnumCreator();
        beanfiller.addCreatorForClassAndAttribute(BaseObject.class, "text", enumCreator);

        assertThrows(WrongCreatorException.class, () -> beanfiller.fillBean(BaseObject.class));
    }


    @Test
    public void testSetterObjectDiffersFromFieldObject() throws FillingException {

        BaseObject object = beanfiller.fillBean(BaseObject.class);
        assertThat(object.getDateFromTimestamp(), greaterThan(0L));
    }
}
