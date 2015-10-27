package nl.ansuz.android.asylum.tests.widget.pickers;

import android.content.Context;
import nl.ansuz.android.asylum.widget.pickers.ObjectPicker;

/**
 * Small tweak of the {@link ObjectPicker} class for testing purposes.
 * @author wijnand
 */
public class ObjectPickerForTesting<T> extends ObjectPicker<T> {

    /**
     * @see ObjectPicker#ObjectPicker
     * @param context See {@link ObjectPicker#ObjectPicker}.
     */
    public ObjectPickerForTesting(Context context) {
        super(context);
    }

    /**
     * Tweaked method for testing, somehow the "fix" for production doesn't agree with testing.
     * @param values String[] - The new display values.
     * @see ObjectPicker#setCustomDisplayValues
     */
    @Override
    protected void setCustomDisplayValues(String[] values) {
        int newMax = values.length - 1;

        setMinValue(0);
        setMaxValue(newMax);
        storeDisplayValues(values);
    }
}
