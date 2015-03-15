package nl.ansuz.android.asylum.utils;

import java.lang.reflect.Array;

/**
 * Helper to quickly generate a good hash code based on Joshua Bloch's recommendations in his book 'Effective Java'.
 * <br/>
 * Some code taken from: http://www.javapractices.com/topic/TopicAction.do?Id=28
 * @author Wijnand
 */
public class HashCodeUtil {

    public static final int ARBITRARY_SEED = 19;

    private static final int PRIME_SEED = 83;
    private static final short INTEGER_BIT_SIZE = 32;

    /**
     * @param seed int - Number to multiply.
     * @return int - The seed multiplied by the {@link #PRIME_SEED}.
     */
    private int multiply(int seed) {
        return PRIME_SEED * seed;
    }

    /**
     * @param anObject Object - The Object to check.
     * @return boolean - Whether or not the passed in Object is an array.
     */
    private boolean isArray(Object anObject) {
        boolean array = false;

        if (anObject != null && anObject.getClass() != null) {
            array = anObject.getClass().isArray();
        }

        return array;
    }

    /**
     * @param seed int - Hash seed.
     * @param aBoolean boolean - The boolean to add to the hash code.
     * @return int - The new hash code.
     */
    public int hash(int seed, boolean aBoolean) {
        int value = 0;
        if (aBoolean) {
            value = 1;
        }

        return multiply(seed) + value;
    }

    /**
     * @param seed int - Hash seed.
     * @param aChar char - The character to add to the hash code.
     * @return int - The new hash code.
     */
    public int hash(int seed, char aChar) {
        return multiply(seed) + (int) aChar;
    }

    /**
     * Note that byte and short are can be handled by this method, through implicit conversion.
     * @param seed int - Hash seed.
     * @param anInt int - The integer to add to the hash code.
     * @return int - The new hash code.
     */
    public int hash(int seed, int anInt) {
        return multiply(seed) + anInt;
    }

    /**
     * @param seed int - Hash seed.
     * @param aLong long - The long to add to the hash code.
     * @return int - The new hash code.
     */
    public int hash(int seed , long aLong) {
        return multiply(seed) + (int) (aLong ^ (aLong >>> INTEGER_BIT_SIZE));
    }

    /**
     * @param seed int - Hash seed.
     * @param aFloat float - The float to add to the hash code.
     * @return int - The new hash code.
     */
    public int hash(int seed , float aFloat) {
        return hash(seed, Float.floatToIntBits(aFloat));
    }

    /**
     * @param seed int - Hash seed.
     * @param aDouble double - The double to add to the hash code.
     * @return int - The new hash code.
     */
    public int hash(int seed , double aDouble) {
        return hash(seed, Double.doubleToLongBits(aDouble));
    }

    /**
     * If the passed in Object is an Array, will calculate and add each element in a recursive fashion.<br/>
     * Else uses the result of {@link Object#hashCode()} or 0 if the object is null.
     * @param seed int - Hash seed.
     * @param anObject Object - The Object to add to the hash code.
     * @return int - The new hash code.
     */
    public int hash(int seed , Object anObject) {
        int result = seed;

        if (anObject == null) {
            // If the Object is null, use 0.
            result = hash(result, 0);
        } else if (!isArray(anObject)) {
            // If the Object isn't an Array, use the hashCode() method.
            result = hash(result, anObject.hashCode());
        } else {
            // The Object is an Array, loop over all items.
            int length = Array.getLength(anObject);
            for (int i = 0; i < length; ++i) {
                Object item = Array.get(anObject, i);
                // If an item in the array references the array itself, prevent infinite looping.
                if (!(item == anObject)) {
                    result = hash(result, item);
                }
            }
        }

        return result;
    }

}
