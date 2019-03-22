package org.synyx.beanfiller.testobjects;

public class NoDefaultConstructorObject {

    private final String foo;

    public NoDefaultConstructorObject(String foo) {

        this.foo = foo;
    }

    public String getFoo() {

        return foo;
    }
}
