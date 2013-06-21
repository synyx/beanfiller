
package org.synyx.beanfiller.creator;

import org.synyx.beanfiller.criteria.DateCriteria;
import org.synyx.beanfiller.services.RandomGenerator;

import java.util.Date;


/**
 * @author  Tobias Knell - knell@synyx.de
 */
public class DateCreator implements SimpleCreator<Date> {

    private DateCriteria criteria;

    public DateCreator() {

        this.criteria = new DateCriteria();
    }


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
