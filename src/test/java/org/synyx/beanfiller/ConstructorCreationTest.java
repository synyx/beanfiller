package org.synyx.beanfiller;

import org.junit.Test;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.testobjects.TestEnum;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

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

    public static class TestObjectWithConstructor{

        private final String[] array;
        private final TestEnum testEnum;
        private final InnerObject innerObject;

        TestObjectWithConstructor(String[] array, TestEnum testEnum, InnerObject innerObject){

            this.array = array;
            this.testEnum = testEnum;
            this.innerObject = innerObject;
        }

        public String[] getArray() {
            return array;
        }

        public TestEnum getTestEnum() {
            return testEnum;
        }

        public InnerObject getInnerObject() {
            return innerObject;
        }
    }

    public static class InnerObject{

        private final String foo;

        public InnerObject(String foo) {
            this.foo = foo;
        }

        public String getFoo() {
            return foo;
        }
    }
}
