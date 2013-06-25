
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.DateCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.util.Date;


/**
 * Creator for Dates.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class DateCreator implements SimpleCreator<Date> {

    private final DateCriteria criteria;

    /**
     * Create a new DateCreator with the default DateCriteria.
     */
    public DateCreator() {

        this.criteria = new DateCriteria();
    }


    /**
     * Create a new DateCreator with the given criteria.
     *
     * @param  criteria  the criteria to use.
     */
    public DateCreator(DateCriteria dateCriteria) {

        this.criteria = dateCriteria;
    }

    @Override
    public Date create() {

        // just use the long values for getting a random Date
        return new Date(Math.round(
                    RandomGenerator.getRandomDouble() * (criteria.getMaxDate().getTime()
                        - criteria.getMinDate().getTime()) + criteria.getMinDate().getTime()));
    }
}
