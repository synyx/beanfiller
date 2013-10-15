
package org.synyx.beanfiller.criteria;

/**
 * Criteria for Strings.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public class StringCriteria implements Criteria<String> {

    private static final int MAX_LENGTH = 8;
    private static final int MIN_LENGTH = 6;

    private int minLength;
    private int maxLength;
    private String charset;

    /**
     * init with default values.
     */
    public StringCriteria() {

        this(MIN_LENGTH, MAX_LENGTH, "abcABCD0123äß!?");
    }


    /**
     * @param  minLength  minimum length of the string
     * @param  maxLength  maximum length of the string
     * @param  charset  charset from where the characters for the String are randomly picked.
     */
    public StringCriteria(int minLength, int maxLength, String charset) {

        this.minLength = minLength;
        this.maxLength = maxLength;
        this.charset = charset;
    }

    public int getMinLength() {

        return minLength;
    }


    public void setMinLength(int minLength) {

        this.minLength = minLength;
    }


    public int getMaxLength() {

        return maxLength;
    }


    public void setMaxLength(int maxLength) {

        this.maxLength = maxLength;
    }


    public String getCharset() {

        return charset;
    }


    /**
     * Set the charset from where the characters for the String are randomly picked.
     *
     * @param  charset  of String
     */
    public void setCharset(String charset) {

        this.charset = charset;
    }
}
