package nl.ansuz.android.asylum.text;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Empty implementation of a {@link TextWatcher}. Allows for easy implementation when only one of the interface methods
 * is required.
 * @author Wijnand
 */
public class BaseTextWatcher implements TextWatcher {
    /** {@inheritDoc} */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Do nothing. */ }

    /** {@inheritDoc} */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { /* Do nothing. */ }

    /** {@inheritDoc} */
    @Override
    public void afterTextChanged(Editable s) { /* Do nothing. */ }
}
