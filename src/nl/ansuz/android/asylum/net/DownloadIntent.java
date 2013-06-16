package nl.ansuz.android.asylum.net;

import android.content.Intent;
import android.graphics.Bitmap;
import nl.ansuz.android.asylum.events.DownloadEvent;

/**
 * Utility class to generate Intents to start/stop a download and send loaded
 * responses in an Intent.
 * 
 * @author Wijnand
 */
public class DownloadIntent {

	private static final String PREFIX = "nl.ansuz.android.asylum.net.DownloadIntent.";

	/**
	 *
	 */
	public class Action {
		public static final String START_DOWNLOAD = PREFIX + "START_DOWNLOAD";
	}

	/**
	 * Simple list of default extras for this Intent.
	 * 
	 * @author Wijnand
	 */
	public class Extra {
		public static final String DOWNLOAD_EVENT = PREFIX + "DOWNLOAD_EVENT";
	}

	/**
	 * Creates an Intent that can be used to start downloading a plain text
	 * resource. 
	 * 
	 * @param url The URL to download from.
	 */
	public Intent createTextRequestIntent(String url) {
		Intent intent = new Intent(Action.START_DOWNLOAD);
		intent.putExtra(Extra.DOWNLOAD_EVENT, new DownloadEvent(DownloadEvent.Action.START_DOWNLOAD, url,
				DownloadEvent.ResourceType.PLAIN_TEXT));

		return intent;
	}

	/**
	 * Creates an Intent that can be used to start downloading an image resource. 
	 * 
	 * @param url The URL to download from.
	 */
	public Intent createImageRequestIntent(String url) {
		Intent intent = new Intent(Action.START_DOWNLOAD);
		intent.putExtra(Extra.DOWNLOAD_EVENT, new DownloadEvent(DownloadEvent.Action.START_DOWNLOAD, url,
				DownloadEvent.ResourceType.IMAGE));

		return intent;
	}

}