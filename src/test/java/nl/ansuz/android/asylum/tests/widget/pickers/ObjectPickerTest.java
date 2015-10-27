package nl.ansuz.android.asylum.tests.widget.pickers;

import nl.ansuz.android.asylum.widget.pickers.ObjectPicker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowContentProvider;

import static org.hamcrest.CoreMatchers.nullValue;

/**
 * Tests the {@link ObjectPicker} class.
 * @author Wijnand
 */
@RunWith(RobolectricTestRunner.class)
public class ObjectPickerTest {

    static final Float[] EXPECTED_ORIGINAL_VALUES = new Float[]{
            5.00f, 5.50f,
            6.00f, 6.50f,
            7.00f, 7.50f,
            8.00f, 8.50f,
            9.00f, 9.50f,
            10.00f, 10.50f,
            11.00f, 11.50f,
    };
    static final String[] EXPECTED_DISPLAY_VALUES = new String[]{
            "5.00", "5.50",
            "6.00", "6.50",
            "7.00", "7.50",
            "8.00", "8.50",
            "9.00", "9.50",
            "10.00", "10.50",
            "11.00", "11.50",
    };

    static final float ACCEPTABLE_DELTA = 0.0001f;
    static final float TEST_VALUE = 8.5f;

    private ObjectPicker<Float> mPicker;

    /**
     * Sets up this test.
     */
    @Before
    public void setUp() {
        mPicker = new ObjectPickerForTesting<>(new ShadowContentProvider().getContext());
    }

    /**
     * Given a new picker has been created.<br />
     * When trying to call {@link ObjectPicker#setDisplayedValues(String[])} directly.<br />
     * Then an {@link IllegalArgumentException} should be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void callingSetDisplayedValuesThrowsException() {
        mPicker.setDisplayedValues(EXPECTED_DISPLAY_VALUES);
    }

    /**
     * Given a new picker has been created.<br />
     * When passing two arrays of different sizes to the {@link ObjectPicker#setDisplayedValues(String[], Object[])}
     * method.<br />
     * Then an {@link IllegalArgumentException} should be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void nonMatchingArrayLengthsThrowsError() {
        mPicker.setDisplayedValues(EXPECTED_DISPLAY_VALUES, new Float[EXPECTED_DISPLAY_VALUES.length - 1]);
    }

    /**
     * Given a new picker has been created<br />
     *  AND no call to {@link ObjectPicker#setDisplayedValues(String[], Object[])} has been made.<br />
     * When trying to store a new value<br />
     *  AND retrieving the new value.<br />
     * Then no value should be returned.
     */
    @Test
    public void nullOriginalValuesResultsInNoTypedValue() {
        mPicker.setValue(TEST_VALUE);
        Assert.assertThat("No value should be available.", mPicker.getTypedValue(), nullValue());
    }

    /**
     * Given a new picker has been created.<br />
     * When the picker has been populated with testing values and a value has been selected.<br />
     * Then the selected value should be returned correctly.
     */
    @Test
    public void valueIsSet() {
        mPicker.setDisplayedValues(EXPECTED_DISPLAY_VALUES, EXPECTED_ORIGINAL_VALUES);
        mPicker.setValue(TEST_VALUE);

        Assert.assertEquals("Incorrect value.", TEST_VALUE, mPicker.getTypedValue(), ACCEPTABLE_DELTA);
    }

}
