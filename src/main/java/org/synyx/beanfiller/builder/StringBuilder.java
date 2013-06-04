package org.synyx.beanfiller.builder;

import org.synyx.beanfiller.criteria.StringCriteria;

import java.util.Random;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class StringBuilder implements Builder<String> {

    private StringCriteria criteria;

    public StringBuilder() {

        this(new StringCriteria());
    }


    public StringBuilder(StringCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public String build() {

        java.lang.StringBuilder builder = new java.lang.StringBuilder();
        Random rand = new Random(System.currentTimeMillis());
        int length = rand.nextInt(criteria.getMaxlength() - criteria.getMinlength() + 1) + criteria.getMinlength();

        for (int i = 0; i < length; i++) {
            builder.append(criteria.getCharset().charAt(rand.nextInt(criteria.getCharset().length())));
        }

        return builder.toString();
    }
}
