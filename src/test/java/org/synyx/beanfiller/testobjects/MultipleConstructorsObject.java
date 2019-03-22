package org.synyx.beanfiller.testobjects;

public class MultipleConstructorsObject {

    private final ObjectWithBeans foo;
    private final ObjectWithBeans bar;

    public MultipleConstructorsObject(ObjectWithBeans foo) {

        this.foo = foo;
        this.bar = null;
    }


    public MultipleConstructorsObject(ObjectWithBeans foo, ObjectWithBeans bar) {

        this.foo = foo;
        this.bar = bar;
    }

    public ObjectWithBeans getFoo() {

        return foo;
    }


    public ObjectWithBeans getBar() {

        return bar;
    }
}
