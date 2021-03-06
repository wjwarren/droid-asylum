package nl.ansuz.android.asylum.widget.pickers;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.NumberPicker;

/**
 * A {@link NumberPicker} that displays any object.
 * @author Wijnand
 */
public class ObjectPicker<T> extends NumberPicker {

    private T[] mOriginalValues;

    /**
     * @see NumberPicker#NumberPicker
     */
    public ObjectPicker(Context context) {
        this(context, null);
    }

    /**
     * @see NumberPicker#NumberPicker
     */
    public ObjectPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Sets the new set of custom display values.
     * @param displayValues String[] - The new display values.
     */
    protected void setCustomDisplayValues(String[] displayValues) {
        // Fun NumberPicker behaviour causing ArrayIndexOutOfBoundsException: http://stackoverflow.com/a/20867948
        int oldMax = getMaxValue();
        int newMax = displayValues.length - 1;

        if (newMax > oldMax) {
            setMinValue(0);
            setValue(0);
            storeDisplayValues(displayValues);
            setMaxValue(newMax);
        } else {
            setMinValue(0);
            setValue(0);
            setMaxValue(newMax);
            storeDisplayValues(displayValues);
        }
    }

    /**
     * Sets the values to be displayed.
     * @param displayValues The displayed values.
     */
    protected void storeDisplayValues(String[] displayValues) {
        super.setDisplayedValues(displayValues);
    }

    /**
     * Do not use! Use {@link #setDisplayedValues(String[], Object[])} instead!<br />
     * This method will throw an Exception if you try and use it!
     */
    @Override
    public void setDisplayedValues(String[] displayedValues) {
        throw new IllegalArgumentException("Use setDisplayedValues(String[], Object[]) instead!");
    }

    /**
     * Sets the values to be displayed.
     * @param customValues List of custom display values.
     * @param originalValues List of the original values.
     * @throws IllegalArgumentException When the arrays don't have a matching size.
     */
    public void setDisplayedValues(String[] customValues, T[] originalValues) {
        if (customValues.length != originalValues.length) {
            throw new IllegalArgumentException("Both arrays should have the same length!");
        }

        mOriginalValues = originalValues;
        setCustomDisplayValues(customValues);
    }

    /**
     * @see #getValue()
     * @return The typed {@link T} value of the selected item OR {@code null} if not found.
     */
    public T getTypedValue() {
        T result = null;
        int position = getValue();

        if (mOriginalValues != null && position < mOriginalValues.length) {
            result = mOriginalValues[position];
        }

        return result;
    }

    /**
     * @see #setValue(int)
     */
    public void setValue(T value) {
        if (mOriginalValues == null) {
            return;
        }

        for (int position = 0; position < mOriginalValues.length; position++) {
            if (mOriginalValues[position].equals(value)) {
                setValue(position);
                break;
            }
        }
    }
}
