package org.synyx.beanfiller.services;

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

        // has only static methods, so we hide the default constructor
    }

    /**
     * Wrapper for Random.nextInt().
     *
     * @return
     */
    public static int getRandomInt() {

        return RANDOM.nextInt();
    }


    /**
     * Wrapper for Random.nextBoolean().
     *
     * @return
     */
    public static Boolean getRandomBoolean() {

        return RANDOM.nextBoolean();
    }


    public static byte[] getRandomBytes(byte[] bytes) {

        RANDOM.nextBytes(bytes);

        return bytes;
    }


    /**
     * Wrapper for Random.nextDouble() - returns a double between 0.0(inclusive) and 1.0 (exclusive).
     *
     * @return
     */
    public static double getRandomDouble() {

        return RANDOM.nextDouble();
    }


    /**
     * Wrapper for Random.nextFloat() - returns a float between 0.0(inclusive) and 1.0 (exclusive).
     *
     * @return
     */
    public static float getRandomFloat() {

        return RANDOM.nextFloat();
    }


    /**
     * Creates a random BigInteger with the given number of bits.
     *
     * @param  bitlenght  number of bits, the random BigInteger should have
     *
     * @return
     */
    public static BigInteger getRandomBigInteger(int bitlenght) {

        return new BigInteger(bitlenght, RANDOM);
    }


    /**
     * Wrapper for Random.nextInt(int n) - Gets a random int with the given maximum (exclusive).
     *
     * @return
     */
    public static int getRandomInt(int max) {

        return RANDOM.nextInt(max);
    }


    /**
     * Gets an int value between (inclusive) the given min and max values.
     *
     * @param  min
     * @param  max
     *
     * @return
     */
    public static int getRandomIntBetween(int min, int max) {

        return getRandomInt(max - min + 1) + min;
    }
}
