package nl.ansuz.android.asylum.widget.pickers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * A {@link NumberPicker} that allows fractions.
 * TODO: TEST!!!
 * @author Wijnand
 */
public class FloatPicker extends NumberPicker {

    private float mMinValue;
    private float mMaxValue;
    private float mStepSize;
    private int mDecimalPlaces;

    private String[] mDisplayValues;

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
    private void init() {
        mMinValue = 0;
        mMaxValue = 1;
        mStepSize = 1;
        mDecimalPlaces = 1;
    }

    /**
     * Generates the String Array of display values.<br/>
     * The Array is generated based on the set min/max values and the step size.<br/>
     * Minimum and maximum values will be automatically swapped if they are in the incorrect order.
     */
    private void generateDisplayValues() {
        if (mMaxValue < mMinValue) {
            float oldMin = mMinValue;
            mMinValue = mMaxValue;
            mMaxValue = oldMin;
        }

        int totalSteps = (int) ((mMaxValue - mMinValue) / mStepSize) + 1;
        mDisplayValues = new String[totalSteps];

        float value;
        for (int i = 0; i < totalSteps; i++) {
            value = mMinValue + i * mStepSize;
            mDisplayValues[i] = getFormattedValue(value, mDecimalPlaces);
        }

        setDisplayedValues(mDisplayValues);
        setMinValue(0);
        setMaxValue(totalSteps - 1);
    }

    /**
     * @param value float - The value to format.
     * @param decimalPlaces int - Number of decimal places to use.
     * @return String - The formatted value.
     */
    private String getFormattedValue(float value, int decimalPlaces) {
        return String.format("%." + decimalPlaces + "f", value);
    }

    /**
     * @return float - Minimum value.
     */
    public float getMinValueAsFloat() {
        return mMinValue;
    }

    /**
     * Stores a new minimum value.
     * @param value float - Minimum value.
     */
    public void setMinValue(float value) {
        if (value != mMinValue) {
            mMinValue = value;
            generateDisplayValues();
        }
    }

    /**
     * @return float - Maximum value.
     */
    public float getMaxValueAsFloat() {
        return mMaxValue;
    }

    /**
     * Stores a new maximum value.
     * @param value float - Maximum value.
     */
    public void setMaxValue(float value) {
        if (value != mMaxValue) {
            mMaxValue = value;
            generateDisplayValues();
        }
    }

    /**
     * @return float - Step size.
     */
    public float getStepSizeAsFloat() {
        return mStepSize;
    }

    /**
     * Stores a new step size.<br/>
     * NOTE: value should be > 0.
     * @param value float - Step size.
     */
    public void setStepSize(float value) {
        if (value == 0) {
            throw new IllegalArgumentException("Step size should be > 0.");
        }

        if (value != mStepSize) {
            mStepSize = Math.abs(value);
            generateDisplayValues();
        }
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
     */
    public void setDecimalPlaces(int value) {
        if (value != mDecimalPlaces) {
            mDecimalPlaces = Math.abs(value);
            generateDisplayValues();
        }
    }

    /**
     * @see #getValue()
     * @return float - The float value of the selected item OR 0f if the item can't be parsed.
     */
    public float getValueAsFloat() {
        float value = 0f;
        int position = getValue();

        if (mDisplayValues != null && position < mDisplayValues.length) {
            try {
                value = Float.valueOf(mDisplayValues[position]);
            } catch (NumberFormatException nfe) {
                // Do nothing.
            }
        }

        return value;
    }

    /**
     * @see #setValue(int)
     */
    public void setValue(float target) {
        if (mDisplayValues == null) {
            return;
        }

        String formattedTarget = getFormattedValue(target, mDecimalPlaces);
        String value;
        for (int i = 0; i < mDisplayValues.length; i++) {
            value = mDisplayValues[i];

            if (formattedTarget.equals(value)) {
                setValue(i);
                break;
            }
        }
    }

}
