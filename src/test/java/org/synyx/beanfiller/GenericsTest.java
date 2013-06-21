package org.synyx.beanfiller;

import org.junit.Assert;
import org.junit.Test;

import org.mockito.Mockito;

import org.synyx.beanfiller.builder.Builder;
import org.synyx.beanfiller.builder.GenericsBuilder;
import org.synyx.beanfiller.builder.ListBuilder;
import org.synyx.beanfiller.testobjects.GenericsObject;
import org.synyx.beanfiller.testobjects.SimpleGenericsObject;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Tests for Objects with generic Types.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class GenericsTest {

    private BeanFiller beanfiller = new BeanFiller();
    private GenericsObject genericsObject;

    @Test
    public void testGenericsObjectIsCreated() throws FillingException {

        genericsObject = beanfiller.fillBean(new GenericsObject());
        Assert.assertNotNull("GenericsObject is null!", genericsObject);
    }


    @Test
    public void testListIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(new GenericsObject());
        Assert.assertNotNull("StringList is null!", genericsObject.getStringList());
        Assert.assertFalse("StringList is empty!", genericsObject.getStringList().isEmpty());
    }


    @Test
    public void testMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(new GenericsObject());
        Assert.assertNotNull("StringMap is null!", genericsObject.getStringMap());
        Assert.assertFalse("StringMap is empty!", genericsObject.getStringMap().isEmpty());
    }


    @Test
    public void testListMapIsFilled() throws FillingException {

        genericsObject = beanfiller.fillBean(new GenericsObject());
        Assert.assertNotNull("StringListMap is null!", genericsObject.getStringListMap());
        Assert.assertFalse("StringListMap is empty!", genericsObject.getStringListMap().isEmpty());
    }


    @Test
    public void testAddedGenericsBuilderIsUsed() throws FillingException {

        GenericsBuilder genericsBuilder = mock(ListBuilder.class);
        when(genericsBuilder.buildWithGenerics(Mockito.anyList())).thenReturn(new ArrayList<String>());
        beanfiller.addBuilder(List.class, genericsBuilder);

        beanfiller.fillBean(new SimpleGenericsObject());

        // assert that our mock was called
        verify(genericsBuilder).buildWithGenerics(Mockito.anyList());
    }


    @Test
    public void testAddedSpecificGenericsBuilderIsUsed() throws FillingException {

        GenericsBuilder genericsBuilder = mock(ListBuilder.class);
        when(genericsBuilder.buildWithGenerics(Mockito.anyList())).thenReturn(new ArrayList<String>());
        beanfiller.addBuilderForClassAndAttribute(SimpleGenericsObject.class, "stringList", genericsBuilder);

        beanfiller.fillBean(new SimpleGenericsObject());

        // assert that our mock was called
        verify(genericsBuilder).buildWithGenerics(Mockito.anyList());
    }


    @Test(expected = WrongBuilderException.class)
    public void testWrongBuilderExceptionIsThrownIfNonGenericsBuilderIsUsed() throws FillingException {

        Builder stringBuilder = new org.synyx.beanfiller.builder.StringBuilder();

        beanfiller.addBuilderForClassAndAttribute(GenericsObject.class, "stringList", stringBuilder);

        beanfiller.fillBean(new GenericsObject());
    }
}
