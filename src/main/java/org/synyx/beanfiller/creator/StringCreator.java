package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.StringCriteria;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class StringCreator implements SimpleCreator<String> {

    private final StringCriteria criteria;

    /**
     * Creates new StringCreator with the default StringCriteria.
     */
    public StringCreator() {

        this(new StringCriteria());
    }


    /**
     * Creates new StringCreator with the given Criteria.
     *
     * @param  criteria  StringCriteria to use.
     */
    public StringCreator(StringCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public String create() {

        StringBuilder builder = new StringBuilder();

        int length = RandomGenerator.getRandomIntBetween(criteria.getMinlength(), criteria.getMaxlength());

        for (int i = 0; i < length; i++) {
            builder.append(criteria.getCharset().charAt(RandomGenerator.getRandomInt(criteria.getCharset().length())));
        }

        return builder.toString();
    }
}
