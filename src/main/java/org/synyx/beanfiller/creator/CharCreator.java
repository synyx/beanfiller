package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.CharCriteria;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * @author  Tobias Knell - knell@synyx.de.
 */
public class CharCreator implements SimpleCreator<Character> {

    private final RandomGenerator randomGenerator;
    private final CharCriteria criteria;

    /**
     * Create a new CharCreator using the default CharCriteria.
     *
     * @param  randomGenerator
     */
    public CharCreator(RandomGenerator randomGenerator) {

        this(randomGenerator, new CharCriteria());
    }


    /**
     * Create a new CharCreator using the given criteria.
     *
     * @param  randomGenerator
     * @param  criteria  the criteria to use.
     */
    public CharCreator(RandomGenerator randomGenerator, CharCriteria criteria) {

        this.randomGenerator = randomGenerator;
        this.criteria = criteria;
    }

    @Override
    public Character create() {

        return criteria.getCharset().charAt(randomGenerator.getRandomInt(criteria.getCharset().length()));
    }
}
