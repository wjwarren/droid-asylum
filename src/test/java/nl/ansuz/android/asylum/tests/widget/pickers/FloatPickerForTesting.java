package nl.ansuz.android.asylum.tests.widget.pickers;

import android.content.Context;
import android.util.AttributeSet;
import nl.ansuz.android.asylum.widget.pickers.FloatPicker;

/**
 * Small tweak of the {@link FloatPicker} class for testing purposes.
 * @author wijnand
 */
public class FloatPickerForTesting extends FloatPicker {

    /**
     * @see FloatPicker#FloatPicker
     * @param context See {@link FloatPicker#FloatPicker}.
     */
    public FloatPickerForTesting(Context context) {
        super(context);
    }

    /**
     * @see FloatPicker#FloatPicker
     * @param context See {@link FloatPicker#FloatPicker}.
     * @param attrs See {@link FloatPicker#FloatPicker}.
     */
    public FloatPickerForTesting(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Tweaked method for testing, somehow the "fix" for production doesn't agree with testing.
     * @param values String[] - The new display values.
     * @see FloatPicker#setCustomDisplayValues
     */
    @Override
    protected void setCustomDisplayValues(String[] values) {
        int newMax = values.length - 1;

        setMinValue(0);
        setMaxValue(newMax);
        setDisplayedValues(values);
    }
}
