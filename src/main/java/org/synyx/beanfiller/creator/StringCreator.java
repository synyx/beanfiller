package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.StringCriteria;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class StringCreator implements SimpleCreator<String> {

    private final RandomGenerator randomGenerator;
    private final StringCriteria criteria;

    /**
     * Creates new StringCreator with the default StringCriteria.
     */
    public StringCreator(RandomGenerator randomGenerator) {

        this(randomGenerator, new StringCriteria());
    }


    /**
     * Creates new StringCreator with the given Criteria.
     *
     * @param  criteria  StringCriteria to use.
     */
    public StringCreator(RandomGenerator randomGenerator, StringCriteria criteria) {

        this.criteria = criteria;
        this.randomGenerator = randomGenerator;
    }

    @Override
    public String create() {

        StringBuilder builder = new StringBuilder();

        int length = randomGenerator.getRandomIntBetween(criteria.getMinlength(), criteria.getMaxlength());

        for (int i = 0; i < length; i++) {
            builder.append(criteria.getCharset().charAt(randomGenerator.getRandomInt(criteria.getCharset().length())));
        }

        return builder.toString();
    }
}
