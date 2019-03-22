
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.DateCriteria;
import org.synyx.beanfiller.util.RandomGenerator;

import java.util.Date;


/**
 * Creator for Dates.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class DateCreator implements SimpleCreator<Date> {

    private final RandomGenerator randomGenerator;
    private final DateCriteria criteria;

    /**
     * Create a new DateCreator with the default DateCriteria.
     *
     * @param  randomGenerator
     */
    public DateCreator(RandomGenerator randomGenerator) {

        this(randomGenerator, new DateCriteria());
    }


    /**
     * Create a new DateCreator with the given criteria.
     *
     * @param  dateCriteria  the criteria to use.
     */
    public DateCreator(RandomGenerator randomGenerator, DateCriteria dateCriteria) {

        this.randomGenerator = randomGenerator;
        this.criteria = dateCriteria;
    }

    @Override
    public Date create() {

        // just use the long values for getting a random Date
        return new Date(Math.round(
                    randomGenerator.getRandomDouble() * (criteria.getMaxDate().getTime()
                        - criteria.getMinDate().getTime()) + criteria.getMinDate().getTime()));
    }
}
