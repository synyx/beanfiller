package org.synyx.beanfiller;

import org.junit.jupiter.api.Test;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.testobjects.ArrayConstructorObject;
import org.synyx.beanfiller.testobjects.ListConstructorObject;
import org.synyx.beanfiller.testobjects.MapConstructorObject;
import org.synyx.beanfiller.testobjects.TestEnum;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConstructorCreationTest {


    @Test
    public void createsObjectViaConstructor() throws FillingException {

        TestObjectWithConstructor object = new BeanFiller().fillBean(TestObjectWithConstructor.class);

        assertThat(object.array, notNullValue());
        assertThat(object.array.length, greaterThan(0));
        assertThat(object.testEnum, notNullValue());
        assertThat(object.innerObject, notNullValue());
        assertThat(object.innerObject.foo, notNullValue());
    }

    @Test
    public void createsObjectWithListInConstructor() throws FillingException {

        ListConstructorObject listConstructorObject = new BeanFiller().fillBean(ListConstructorObject.class);
        assertThat(listConstructorObject,notNullValue());
        assertThat(listConstructorObject.getValues(),notNullValue());
        assertThat(listConstructorObject.getValues().size(), is(greaterThan(0)));
    }

    @Test
    public void createsObjectWithArrayInConstructor() throws FillingException {

        ArrayConstructorObject object = new BeanFiller().fillBean(ArrayConstructorObject.class);
        assertThat(object,notNullValue());
        assertThat(object.getValues(),notNullValue());
        assertThat(object.getValues().length, is(greaterThan(0)));
    }

    @Test
    public void createsObjectWithMapInConstructor() throws FillingException {

        MapConstructorObject object = new BeanFiller().fillBean(MapConstructorObject.class);
        assertThat(object,notNullValue());
        assertThat(object.getValues(),notNullValue());
        assertThat(object.getValues().size(), is(greaterThan(0)));
    }

    public static class TestObjectWithConstructor{

        private final String[] array;
        private final TestEnum testEnum;
        private final InnerObject innerObject;

        TestObjectWithConstructor(String[] array, TestEnum testEnum, InnerObject innerObject) {

            this.array = array;
            this.testEnum = testEnum;
            this.innerObject = innerObject;
        }
    }

    public static class InnerObject{

        private final String foo;

        public InnerObject(String foo) {
            this.foo = foo;
        }
    }
}
