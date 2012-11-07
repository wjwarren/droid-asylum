package nl.ansuz.android.asylum.text.style;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.style.ImageSpan;

/**
 * A further implemented ImageSpan that provides more alignment options.
 * 
 * @author Wijnand
 */
public class WorkingImageSpan extends ImageSpan {

	public static final int ALIGN_CENTER = 2;

	private static final String LOG_TAG = "WorkingImageSpan";

	private WeakReference<Drawable> mDrawableRef;

	public WorkingImageSpan(Drawable d) {
		super(d);
	}

	public WorkingImageSpan(Context context, Bitmap b) {
		super(context, b);
	}

	public WorkingImageSpan(Drawable d, int verticalAlignment) {
		super(d, verticalAlignment);
	}

	public WorkingImageSpan(Drawable d, String source) {
		super(d, source);
	}

	public WorkingImageSpan(Context context, Uri uri) {
		super(context, uri);
	}

	public WorkingImageSpan(Context context, int resourceId) {
		super(context, resourceId);
	}

	public WorkingImageSpan(Context context, Bitmap b, int verticalAlignment) {
		super(context, b, verticalAlignment);
	}

	public WorkingImageSpan(Drawable d, String source, int verticalAlignment) {
		super(d, source, verticalAlignment);
	}

	public WorkingImageSpan(Context context, Uri uri, int verticalAlignment) {
		super(context, uri, verticalAlignment);
	}

	public WorkingImageSpan(Context context, int resourceId,
			int verticalAlignment) {
		super(context, resourceId, verticalAlignment);
	}
	
	/**
	 * Copied from:
	 * http://grepcode.com/file_/repository.grepcode.com/java/ext/com.google.android/android/1.5_r4/android/text/style/DynamicDrawableSpan.java/?v=source
	 */
	private Drawable getCachedDrawable() {
		WeakReference<Drawable> wr = mDrawableRef;
		Drawable d = null;

		if (wr != null)
			d = wr.get();

		if (d == null) {
			d = getDrawable();
			mDrawableRef = new WeakReference<Drawable>(d);
		}

		return d;
	}

	/**
	 * Mainly copied from:
	 * http://grepcode.com/file_/repository.grepcode.com/java/ext/com.google.android/android/1.5_r4/android/text/style/DynamicDrawableSpan.java/?v=source
	 * 
	 * @see super.draw(...)
	 */
	@Override
	public void draw(Canvas canvas, CharSequence text, int start, int end,
			float x, int top, int y, int bottom, Paint paint) {

		Drawable b = getCachedDrawable();
		canvas.save();

		// Start by BOTTOM aligning the icon with the text.
		int transY = bottom - b.getBounds().bottom;
		switch (mVerticalAlignment) {
		case ALIGN_BASELINE:
			// Move up for baseline.
			transY -= paint.getFontMetricsInt().descent;
			break;
		case ALIGN_CENTER:
			// Move down to center align the image. 
			FontMetricsInt metrics = paint.getFontMetricsInt();
			int textHeight = metrics.bottom - metrics.top;
			int drawableHeight = b.getBounds().height();
			// The amount to move down is 1/2 the difference between Drawable 
			// height and text height.
			transY += (drawableHeight - textHeight) / 2;
			break;

		case ALIGN_BOTTOM:
		default:
			// Do nothing
			break;
		}

		canvas.translate(x, transY);
		b.draw(canvas);
		canvas.restore();
	}

}