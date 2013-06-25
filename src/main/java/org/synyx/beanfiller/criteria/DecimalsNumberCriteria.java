package org.synyx.beanfiller.criteria;

/**
 * Abstract superclass for all Criterias for Numbers that have decimals.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public abstract class DecimalsNumberCriteria<T extends Number> extends NumberCriteria<T> {

    protected int numberOfDecimals;

    /**
     * @param  min  minimum value of the created number
     * @param  max  maximum value of the created number
     * @param  numberOfDecimals  number of decimals of the created number
     */
    protected DecimalsNumberCriteria(T min, T max, int numberOfDecimals) {

        super(max, min);
        this.numberOfDecimals = numberOfDecimals;
    }

    /**
     * Get the number of decimals the created number should have.
     *
     * @return  the number of decimals
     */
    public int getNumberOfDecimals() {

        return numberOfDecimals;
    }


    /**
     * Set the number of decimals the created number should have.
     *
     * @param  numberOfDecimals
     */
    public void setNumberOfDecimals(int numberOfDecimals) {

        this.numberOfDecimals = numberOfDecimals;
    }
}
