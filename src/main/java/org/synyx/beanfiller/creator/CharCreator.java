package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.CharCriteria;
import org.synyx.beanfiller.exceptions.FillingException;
import org.synyx.beanfiller.util.RandomGenerator;


/**
 * Created by knell on 17.12.13.
 */
public class CharCreator implements SimpleCreator<Character> {

    private CharCriteria criteria;

    /**
     * Create a new CharCreator using the default CharCriteria.
     */
    public CharCreator() {

        this.criteria = new CharCriteria();
    }


    /**
     * Create a new CharCreator using the given criteria.
     *
     * @param  criteria
     */
    public CharCreator(CharCriteria criteria) {

        this.criteria = criteria;
    }

    @Override
    public Character create() throws FillingException {

        return criteria.getCharset().charAt(RandomGenerator.getRandomInt(criteria.getCharset().length()));
    }
}
