package nl.ansuz.android.asylum.tests.widget.pickers;

import android.os.Build;
import org.junit.Assert;
import nl.ansuz.android.asylum.widget.pickers.FloatPicker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.equalTo;

/**
 * Tests for the {@link FloatPicker} class.
 * @author wijnand
 */
@Config(emulateSdk = Build.VERSION_CODES.JELLY_BEAN_MR2)
@RunWith(RobolectricTestRunner.class)
public class FloatPickerTest {

    private static final float MIN_VALUE = 5f;
    private static final float MAX_VALUE = 11.5f;
    private static final float STEP_SIZE = .5f;
    private static final int DECIMAL_PLACES = 2;

    private static final float ILLEGAL_STEP_SIZE = -1f;

    private FloatPicker mPicker;

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
     * Given a new picker has been created.<br />
     * When no changes have been made.<br />
     * Then the default values should be set.
     */
    @Test
    public void defaultValuesAreSet() {
        Assert.assertEquals("Incorrect default min value,",
                0f, mPicker.getMinValueAsFloat(), ObjectPickerTest.ACCEPTABLE_DELTA);
        Assert.assertEquals("Incorrect default max value,",
                1f, mPicker.getMaxValueAsFloat(), ObjectPickerTest.ACCEPTABLE_DELTA);
        Assert.assertEquals("Incorrect default step size,",
                1f, mPicker.getStepSizeAsFloat(), ObjectPickerTest.ACCEPTABLE_DELTA);
        Assert.assertEquals("Incorrect default value,", 0f,
                mPicker.getValueAsFloat(), ObjectPickerTest.ACCEPTABLE_DELTA);
    }

    /**
     * Given a new picker has been created.<br />
     * When trying to set an incorrect step size.<br />
     * Then the an {@link IllegalArgumentException} should be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void incorrectStepSizeThrowsError() {
        mPicker.setStepSize(ILLEGAL_STEP_SIZE);
    }

    /**
     * Given a new picker has been created.<br />
     * When trying to set equal minimum and maximum values.<br />
     * Then the an {@link IllegalArgumentException} should be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void incorrectMinMaxThrowsError() {
        mPicker.setMinMaxValues(MIN_VALUE, MIN_VALUE);
    }

    /**
     * Given a new picker has been created AND populated with values.<br />
     * When trying to set the same minimum and maximum values again.<br />
     * Then this action should be ignored.
     */
    @Test
    public void settingSameMinMaxValuesAgainAreIgnored() {
        populatePicker();
        Assert.assertFalse("No new values should be stored.", mPicker.setMinMaxValues(MIN_VALUE, MAX_VALUE));
    }

    /**
     * Given a new picker has been created AND populated with values.<br />
     * When trying to set a minimum value that is larger than the maximum value.<br />
     * Then the minimum and maximum values should be swapped.
     */
    @Test
    public void minMaxAreSwappedWhenPassedIncorrectly() {
        populatePicker();
        mPicker.setMinMaxValues(MAX_VALUE, MIN_VALUE);

        Assert.assertThat("Incorrect min value.", mPicker.getMinValueAsFloat(), equalTo(MIN_VALUE));
        Assert.assertThat("Incorrect max value.", mPicker.getMaxValueAsFloat(), equalTo(MAX_VALUE));
    }

    /**
     * Given a new picker has been created.<br />
     * When the picker has been populated with testing values.<br />
     * Then the minimum value should be set correctly.
     */
    @Test
    public void minValueIsSet() {
        populatePicker();
        Assert.assertEquals("Incorrect min value,",
                MIN_VALUE, mPicker.getMinValueAsFloat(), ObjectPickerTest.ACCEPTABLE_DELTA);
    }

    /**
     * Given a new picker has been created.<br />
     * When the picker has been populated with testing values.<br />
     * Then the maximum value should be set correctly.
     */
    @Test
    public void maxValueIsSet() {
        populatePicker();
        Assert.assertEquals("Incorrect max value,",
                MAX_VALUE, mPicker.getMaxValueAsFloat(), ObjectPickerTest.ACCEPTABLE_DELTA);
    }

    /**
     * Given a new picker has been created.<br />
     * When the picker has been populated with testing values.<br />
     * Then the step size value should be set correctly.
     */
    @Test
    public void stepSizeValueIsSet() {
        populatePicker();
        Assert.assertEquals("Incorrect step size,",
                STEP_SIZE, mPicker.getStepSizeAsFloat(), ObjectPickerTest.ACCEPTABLE_DELTA);
    }

    /**
     * Given a new picker has been created AND populated with values.<br />
     * When trying to set the step size again.<br />
     * Then this action should be ignored.
     */
    @Test
    public void settingSameStepSizeIsIgnored() {
        populatePicker();
        Assert.assertFalse("No new value should be stored.", mPicker.setStepSize(STEP_SIZE));
    }

    /**
     * Given a new picker has been created AND populated with values.<br />
     * When trying to set the same decimal places again.<br />
     * Then this action should be ignored.
     */
    @Test
    public void settingSameDecimalPlacesIsIgnored() {
        populatePicker();
        Assert.assertFalse("No new value should be stored.", mPicker.setDecimalPlaces(DECIMAL_PLACES));
    }

    /**
     * Given a new picker has been created AND populated with values.<br />
     * When trying to set a negative number of decimal places.<br />
     * Then the absolute value should be used for the decimal places.
     */
    @Test
    public void setNegativeDecimalPlaces() {
        populatePicker();
        mPicker.setDecimalPlaces(DECIMAL_PLACES * -1);
        Assert.assertThat("Incorrect decimal places.", mPicker.getDecimalPlaces(), equalTo(DECIMAL_PLACES));
    }

    /**
     * Given a new picker has been created.<br />
     * When the picker has been populated with testing values.<br />
     * Then the generated display values should be generated correctly.
     */
    @Test
    public void correctDisplayValuesAreGenerated() {
        populatePicker();
        String[] generatedValues = mPicker.getDisplayedValues();

        Assert.assertNotNull("Displayed values should not be empty!", generatedValues);
        Assert.assertEquals("Incorrect display values list length,",
                ObjectPickerTest.EXPECTED_DISPLAY_VALUES.length,
                generatedValues.length);

        String expected;
        String actual;
        for (int i = 0; i < ObjectPickerTest.EXPECTED_DISPLAY_VALUES.length; i++) {
            expected = ObjectPickerTest.EXPECTED_DISPLAY_VALUES[i];
            actual = generatedValues[i];

            Assert.assertEquals("Incorrect display value, ", expected, actual);
        }
    }

    /**
     * Given a new picker has been created
     *  AND populated with a list of values
     *  AND a value has been selected.<br />
     * When requesting the set value.<br />
     * Then the correct value should be returned.
     */
    @Test
    public void valueIsSet() {
        populatePicker();
        mPicker.setValue(ObjectPickerTest.TEST_VALUE);
        Assert.assertEquals("Incorrect value.",
                ObjectPickerTest.TEST_VALUE, mPicker.getValueAsFloat(), ObjectPickerTest.ACCEPTABLE_DELTA);
    }
}
