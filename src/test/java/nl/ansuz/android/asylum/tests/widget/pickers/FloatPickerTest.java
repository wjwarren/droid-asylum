package nl.ansuz.android.asylum.tests.widget.pickers;

import android.os.Build;
import junit.framework.Assert;
import nl.ansuz.android.asylum.widget.pickers.FloatPicker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Tests for the {@link FloatPicker} class.
 * @author wijnand
 */
@Config(emulateSdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
@RunWith(RobolectricTestRunner.class)
public class FloatPickerTest {

    private static final float MIN_VALUE = 5f;
    private static final float MAX_VALUE = 11.5f;
    private static final float TEST_VALUE = 8.5f;
    private static final float STEP_SIZE = .5f;
    private static final int DECIMAL_PLACES = 2;
    private static final String[] EXPECTED_DISPLAY_VALUES = new String[]{
            "5.00", "5.50",
            "6.00", "6.50",
            "7.00", "7.50",
            "8.00", "8.50",
            "9.00", "9.50",
            "10.00", "10.50",
            "11.00", "11.50",
    };

    private static final float ILLEGAL_STEP_SIZE = -1f;

    private FloatPickerForTesting mPicker;

    /**
     * Populates the {@link FloatPicker} with test values.
     */
    private void populatePicker() {
        mPicker.setMinMaxValues(MIN_VALUE, MAX_VALUE);
        mPicker.setStepSize(STEP_SIZE);
        mPicker.setDecimalPlaces(DECIMAL_PLACES);
    }

    /**
     * Sets up this test.
     */
    @Before
    public void setUp() {
        mPicker = new FloatPickerForTesting(Robolectric.getShadowApplication().getApplicationContext());
    }

    /**
     * Given a new picker has been created.<br/>
     * When no changes have been made.<br/>
     * Then the default values should be set.<br/>
     */
    @Test
    public void defaultValuesAreSet() {
        Assert.assertEquals("Incorrect default min value,", 0f, mPicker.getMinValueAsFloat());
        Assert.assertEquals("Incorrect default max value,", 1f, mPicker.getMaxValueAsFloat());
        Assert.assertEquals("Incorrect default step size,", 1f, mPicker.getStepSizeAsFloat());
        Assert.assertEquals("Incorrect default value,", 0f, mPicker.getValueAsFloat());
    }

    /**
     * Given a new picker has been created.<br/>
     * When trying to set an incorrect step size.<br/>
     * Then the an {@link IllegalArgumentException} should be thrown.<br/>
     */
    @Test(expected = IllegalArgumentException.class)
    public void incorrectStepSizeThrowsError() {
        mPicker.setStepSize(ILLEGAL_STEP_SIZE);
    }

    /**
     * Given a new picker has been created.<br/>
     * When trying to set equal minimum and maximum values.<br/>
     * Then the an {@link IllegalArgumentException} should be thrown.<br/>
     */
    @Test(expected = IllegalArgumentException.class)
    public void incorrectMinMaxThrowsError() {
        mPicker.setMinMaxValues(MIN_VALUE, MIN_VALUE);
    }

    /**
     * Given a new picker has been created.<br/>
     * When the picker has been populated with testing values.<br/>
     * Then the minimum value should be set correctly.<br/>
     */
    @Test
    public void minValueIsSet() {
        populatePicker();
        Assert.assertEquals("Incorrect min value,", MIN_VALUE, mPicker.getMinValueAsFloat());
    }

    /**
     * Given a new picker has been created.<br/>
     * When the picker has been populated with testing values.<br/>
     * Then the maximum value should be set correctly.<br/>
     */
    @Test
    public void maxValueIsSet() {
        populatePicker();
        Assert.assertEquals("Incorrect max value,", MAX_VALUE, mPicker.getMaxValueAsFloat());
    }

    /**
     * Given a new picker has been created.<br/>
     * When the picker has been populated with testing values.<br/>
     * Then the step size value should be set correctly.<br/>
     */
    @Test
    public void stepSizeValueIsSet() {
        populatePicker();
        Assert.assertEquals("Incorrect step size,", STEP_SIZE, mPicker.getStepSizeAsFloat());
    }

    /**
     * Given a new picker has been created.<br/>
     * When the picker has been populated with testing values.<br/>
     * Then the generated display values should be generated correctly.<br/>
     */
    @Test
    public void correctDisplayValuesAreGenerated() {
        populatePicker();
        String[] generatedValues = mPicker.getDisplayedValues();

        Assert.assertNotNull("Displayed values should not be empty!", generatedValues);
        Assert.assertEquals("Incorrect display values list length,",
                EXPECTED_DISPLAY_VALUES.length,
                generatedValues.length);

        String expected;
        String actual;
        for (int i = 0; i < EXPECTED_DISPLAY_VALUES.length; i++) {
            expected = EXPECTED_DISPLAY_VALUES[i];
            actual = generatedValues[i];

            Assert.assertEquals("Incorrect display value, ", expected, actual);
        }
    }

    /**
     * Given a new picker has been created.<br/>
     * When the picker has been populated with testing values and a value has been selected.<br/>
     * Then the selected value should be returned correctly.<br/>
     */
    @Test
    public void valueIsSet() {
        populatePicker();
        mPicker.setValue(TEST_VALUE);

        Assert.assertEquals("Incorrect value,", TEST_VALUE, mPicker.getValueAsFloat());
    }

}
