
package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import org.synyx.beanfiller.builder.ArrayBuilder;
import org.synyx.beanfiller.builder.Builder;
import org.synyx.beanfiller.builder.StringBuilder;
import org.synyx.beanfiller.testobjects.ArraysObject;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for Arrays of different Types.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class ArrayTest {

    private BeanFiller beanfiller = new BeanFiller();
    private ArraysObject arraysObject;

    @Test
    public void testObjectIsCreated() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ArraysObject is null!", arraysObject);
    }


    @Test
    public void testStringArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("StringArray is null!", arraysObject.getStringArray());
    }


    @Test
    public void testStringArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("StringArray Element is null!", arraysObject.getStringArray()[0]);
    }


    @Test
    public void testIntArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("IntArray is null!", arraysObject.getIntArray());
    }


    @Test
    public void testIntArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("IntArray Element is null!", arraysObject.getIntArray()[0]);
    }


    @Test
    public void testObjectArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ObjectArray is null!", arraysObject.getObjectArray());
    }


    @Test
    public void testObjectArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ObjectArray Element is null!", arraysObject.getObjectArray()[0]);
    }


    @Test
    public void testEnumArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("EnumArray is null!", arraysObject.getEnumArray());
    }


    @Test
    public void testEnumArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("EnumArray Element is null!", arraysObject.getEnumArray()[0]);
    }


    @Test
    public void testListArrayIsFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ListArray is null!", arraysObject.getListArray());
    }


    @Test
    public void testListArrayElementsAreFilled() throws FillingException {

        arraysObject = beanfiller.fillBean(new ArraysObject());
        Assert.assertNotNull("ListArray Element is null!", arraysObject.getListArray()[0]);
    }


    @Test
    public void testAddedArrayBuilderIsUsed() throws FillingException {

        ArrayBuilder arrayBuilder = mock(ArrayBuilder.class);
        when(arrayBuilder.buildArray(Mockito.anyList(), Mockito.any(Class.class))).thenReturn(null);

        beanfiller.addBuilder(String[].class, arrayBuilder);

        beanfiller.fillBean(new ArraysObject());

        // assert that our mock was called instead of the default ArrayBuilder
        verify(arrayBuilder).buildArray(Mockito.anyList(), Mockito.any(Class.class));
    }


    @Test
    public void testAddedSpecificArrayBuilderIsUsed() throws FillingException {

        ArrayBuilder arrayBuilder = mock(ArrayBuilder.class);
        when(arrayBuilder.buildArray(Mockito.anyList(), Mockito.any(Class.class))).thenReturn(null);

        beanfiller.addBuilderForClassAndAttribute(ArraysObject.class, "stringArray", arrayBuilder);

        beanfiller.fillBean(new ArraysObject());

        // assert that our mock was called instead of the default ArrayBuilder
        verify(arrayBuilder).buildArray(Mockito.anyList(), Mockito.any(Class.class));
    }


    @Test(expected = WrongBuilderException.class)
    public void testWrongBuilderExceptionIsThrownIfNonArrayBuilderIsUsed() throws FillingException {

        Builder stringBuilder = new StringBuilder();

        beanfiller.addBuilderForClassAndAttribute(ArraysObject.class, "stringArray", stringBuilder);

        beanfiller.fillBean(new ArraysObject());
    }
}
