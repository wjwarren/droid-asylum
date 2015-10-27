package nl.ansuz.android.asylum.widget.pickers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * An {@link ObjectPicker} that allows fractions.
 * @author Wijnand
 */
public class FloatPicker extends ObjectPicker<Float> {

    private float mMinValue;
    private float mMaxValue;
    private float mStepSize;
    private int mDecimalPlaces;

    private String[] mDisplayValues;
    private Float[] mFloatValues;

    /**
     * @see NumberPicker#NumberPicker
     * @param context See {@link NumberPicker#NumberPicker}.
     */
    public FloatPicker(Context context) {
        this(context, null);
    }

    /**
     * @see NumberPicker#NumberPicker
     * @param context See {@link NumberPicker#NumberPicker}.
     * @param attrs See {@link NumberPicker#NumberPicker}.
     */
    public FloatPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Initializes this class.
     */
    protected void init() {
        mMinValue = 0;
        mMaxValue = 1;
        mStepSize = 1;
        mDecimalPlaces = 1;
    }

    /**
     * Generates the String Array of display values.<br/>
     * The Array is generated based on the set min/max values and the step size.
     */
    protected String[] generateDisplayValues() {
        int totalSteps = (int) ((mMaxValue - mMinValue) / mStepSize) + 1;
        mDisplayValues = new String[totalSteps];
        mFloatValues = new Float[totalSteps];

        float value;
        for (int i = 0; i < totalSteps; i++) {
            value = mMinValue + i * mStepSize;
            mDisplayValues[i] = getFormattedValue(value, mDecimalPlaces);
            mFloatValues[i] = value;
        }

        return mDisplayValues;
    }

    /**
     * @param value float - The value to format.
     * @param decimalPlaces int - Number of decimal places to use.
     * @return String - The formatted value.
     */
    protected String getFormattedValue(float value, int decimalPlaces) {
        return String.format("%." + decimalPlaces + "f", value);
    }

    /**
     * @return float - Minimum value.
     */
    public float getMinValueAsFloat() {
        return mMinValue;
    }

    /**
     * @return float - Maximum value.
     */
    public float getMaxValueAsFloat() {
        return mMaxValue;
    }

    /**
     * Stores new minimum and maximum values.<br/>
     * Minimum and maximum values will be automatically swapped if they are in the incorrect order.
     * @param min float - Minimum value.
     * @param max float - Maximum value.
     * @return Whether new min/max have been stored.
     * @throws IllegalArgumentException When step {@code min} == {@code max}.
     */
    public boolean setMinMaxValues(float min, float max) {
        if (mMinValue == min && mMaxValue == max) {
            return false;
        } else if (min == max) {
            throw new IllegalArgumentException("Min == max!");
        } else if (max < min) {
            mMinValue = max;
            mMaxValue = min;
        } else {
            mMinValue = min;
            mMaxValue = max;
        }

        setDisplayedValues(generateDisplayValues(), mFloatValues);
        return true;
    }

    /**
     * @return float - Step size.
     */
    public float getStepSizeAsFloat() {
        return mStepSize;
    }

    /**
     * Stores a new step size.
     * @param value float - Step size.
     * @return Whether the step size has changed.
     * @throws IllegalArgumentException When step size <= 0.
     */
    public boolean setStepSize(float value) {
        boolean changed = false;

        if (value <= 0) {
            throw new IllegalArgumentException("Step size should be > 0.");
        } else if (value != mStepSize) {
            mStepSize = value;
            setDisplayedValues(generateDisplayValues(), mFloatValues);
            changed = true;
        }

        return changed;
    }

    /**
     * @return int - The number of decimal places to use for display.
     */
    public int getDecimalPlaces() {
        return mDecimalPlaces;
    }

    /**
     * Store a new number of decimal places to use.
     * @param value int - The number of decimal places to use for display.
     * @return Whether the decimal places has changed.
     */
    public boolean setDecimalPlaces(int value) {
        boolean changed = false;

        if (value != mDecimalPlaces) {
            mDecimalPlaces = Math.abs(value);
            setDisplayedValues(generateDisplayValues(), mFloatValues);
            changed = true;
        }

        return changed;
    }

    /**
     * @see #getValue()
     * @return float - The float value of the selected item OR 0f if the item can't be parsed.
     */
    public float getValueAsFloat() {
        Float value = getTypedValue();

        if (value == null) {
            value = 0f;
        }

        return value;
    }

    /**
     * @see #setValue(int)
     */
    public void setValue(float target) {
        super.setValue(target);
    }
}
