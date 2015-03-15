package nl.ansuz.android.asylum.widget;


import nl.ansuz.android.asylum.text.style.WorkingImageSpan;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

/**
 * Creates a button with and icon + text centered on the button.
 * This depends on the leftDrawable being set (in XML).
 * 
 * |----------------------------|
 * |         Icon Text          |
 * |----------------------------|
 * 
 * @author Wijnand
 */
public class CenteredIconButton extends Button {

	private static final String LOG_TAG = "CenteredIconButton";

	public CenteredIconButton(Context context) {
		super(context);
		createSpannableText();
	}

	public CenteredIconButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		createSpannableText();
	}
	
	public CenteredIconButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		createSpannableText();
	}
	
	/**
	 * Creates the SpannableString that will hold the icon.
	 */
	private void createSpannableText() {
		Drawable leftIcon = getCompoundDrawables()[0];
		
		if(leftIcon != null) {
			Spannable buttonLabel = new SpannableString("  " + getText());
			WorkingImageSpan icon = new WorkingImageSpan(leftIcon, WorkingImageSpan.ALIGN_CENTER);
			buttonLabel.setSpan(icon, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			setText(buttonLabel);
			setCompoundDrawables(null, null, null, null);
		} else {
			Log.e(LOG_TAG, "No icon found!!!");
		}
		
	}
	
}
