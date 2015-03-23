package nl.ansuz.android.asylum.widget.pickers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * A {@link NumberPicker} that allows fractions.
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

        float value;
        for (int i = 0; i < totalSteps; i++) {
            value = mMinValue + i * mStepSize;
            mDisplayValues[i] = getFormattedValue(value, mDecimalPlaces);
        }

        return mDisplayValues;
    }

    /**
     * Sets the new set of custom display values.
     * @param values String[] - The new display values.
     */
    protected void setCustomDisplayValues(String[] values) {
        // Fun NumberPicker behaviour causing ArrayIndexOutOfBoundsException: http://stackoverflow.com/a/20867948
        int oldMax = getMaxValue();
        int newMax = values.length - 1;

        if (newMax > oldMax) {
            setMinValue(0);
            setValue(0);
            setDisplayedValues(values);
            setMaxValue(newMax);
        } else {
            setMinValue(0);
            setValue(0);
            setMaxValue(newMax);
            setDisplayedValues(values);
        }
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
     * @throws IllegalArgumentException When step {@code min} == {@code max}.
     */
    public void setMinMaxValues(float min, float max) {
        if (mMinValue == min && mMaxValue == max) {
            return;
        }

        if (min == max) {
            throw new IllegalArgumentException("Min == max!");
        }

        if (max < min) {
            mMinValue = max;
            mMaxValue = min;
        } else {
            mMinValue = min;
            mMaxValue = max;
        }

        setCustomDisplayValues(generateDisplayValues());
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
     * @throws IllegalArgumentException When step size <= 0.
     */
    public void setStepSize(float value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Step size should be > 0.");
        }

        if (value != mStepSize) {
            mStepSize = value;
            setCustomDisplayValues(generateDisplayValues());
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
            setCustomDisplayValues(generateDisplayValues());
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
