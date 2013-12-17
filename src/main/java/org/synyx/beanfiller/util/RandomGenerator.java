package org.synyx.beanfiller.util;

import java.math.BigInteger;

import java.util.Random;


/**
 * Encapsulates generating of random data.
 *
 * @author  Tobias Knell - knell@synyx.de
 */
public final class RandomGenerator {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private RandomGenerator() {

        // only has static methods, so we hide the default constructor
    }

    /**
     * Wrapper for Random.nextInt().
     *
     * @return  random int.
     */
    public static int getRandomInt() {

        return RANDOM.nextInt();
    }


    /**
     * Wrapper for Random.nextBoolean().
     *
     * @return  random Boolean.
     */
    public static Boolean getRandomBoolean() {

        return RANDOM.nextBoolean();
    }


    /**
     * @param  bytes  byte array to get random bytes for.
     *
     * @return  the byte array with filled in random bytes.
     */
    public static byte[] getRandomBytes(byte[] bytes) {

        RANDOM.nextBytes(bytes);

        return bytes;
    }


    /**
     * Wrapper for Random.nextDouble() - returns a double between 0.0(inclusive) and 1.0 (exclusive).
     *
     * @return  random double.
     */
    public static double getRandomDouble() {

        return RANDOM.nextDouble();
    }


    /**
     * Wrapper for Random.nextFloat() - returns a float between 0.0(inclusive) and 1.0 (exclusive).
     *
     * @return  random float.
     */
    public static float getRandomFloat() {

        return RANDOM.nextFloat();
    }


    /**
     * Creates a random BigInteger with the given number of bits.
     *
     * @param  bitlength  number of bits, the random BigInteger should have
     *
     * @return  random BigInteger with the given number of bits.
     */
    public static BigInteger getRandomBigInteger(int bitlength) {

        return new BigInteger(bitlength, RANDOM);
    }


    /**
     * Wrapper for Random.nextInt(int n) - Gets a random int with the given maximum (exclusive).
     *
     * @return  random int < max.
     */
    public static int getRandomInt(int max) {

        return RANDOM.nextInt(max);
    }


    /**
     * Gets an int value between (inclusive) the given min and max values.
     *
     * @param  min  minimum value.
     * @param  max  maximum value.
     *
     * @return  random int between the two values.
     */
    public static int getRandomIntBetween(int min, int max) {

        return getRandomInt(max - min + 1) + min;
    }
}
