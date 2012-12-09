package nl.ansuz.android.asylum.net;

import android.content.Intent;
import android.graphics.Bitmap;

/**
 * Utility class to generate Intents to start/stop a download and send loaded
 * responses in an Intent.
 * 
 * @author Wijnand
 */
public class DownloadIntent {

	private static final String PREFIX = "nl.ansuz.android.asylum.net.DownloadIntent.";
	
	/**
	 * The different type of resources that can be downloaded.
	 * 
	 * @author Wijnand
	 */
	public enum ResourceType {
		PLAIN_TEXT, IMAGE, BINARY;
		
		private static final ResourceType[] resourceValues = ResourceType.values();
		
		public static ResourceType fromInteger(int i) {
			return resourceValues[i];
		}
	}
	
	/**
	 * Simple list of default actions for this Intent.
	 * 
	 * @author Wijnand
	 */
	public class Action {
		public static final String START_DOWNLOAD = PREFIX + "START_DOWNLOAD";
		public static final String STOP_DOWNLOAD = PREFIX + "STOP_DOWNLOAD";
		public static final String RESPONSE = PREFIX + "RESPONSE";
		public static final String ERROR = PREFIX + "ERROR";
	}
	
	/**
	 * Simple list of default extras for this Intent.
	 * 
	 * @author Wijnand
	 */
	public class Extra {
		public static final String URL = PREFIX + "URL";
		public static final String RESOURCE_TYPE = PREFIX + "RESOURCE_TYPE";
		
		public static final String TEXT_RESPONSE = PREFIX + "TEXT_RESPONSE";
		public static final String IMAGE_RESPONSE = PREFIX + "IMAGE_RESPONSE";
		public static final String ERROR_MESSAGE = PREFIX + "ERROR_MESSAGE";
	}
	
	/**
	 * Creates a generic intent with the URL already set as extra.
	 * 
	 * @param action The action for the intent.
	 * @param url The URL that is being used.
	 */
	private Intent createIntent(String action, String url) {
		Intent intent = new Intent(action);
		intent.putExtra(DownloadIntent.Extra.URL, url);
		
		return intent;
	}
	
	/**
	 * Creates an Intent that can be used to start downloading a plain text
	 * resource. 
	 * 
	 * @param url The URL to download from.
	 */
	public Intent createTextRequestIntent(String url) {
		Intent intent = createIntent(Action.START_DOWNLOAD, url);
		intent.putExtra(Extra.RESOURCE_TYPE, ResourceType.PLAIN_TEXT.ordinal());
		
		return intent;
	}
	
	/**
	 * Creates an Intent that can be used to start downloading an image resource. 
	 * 
	 * @param url The URL to download from.
	 */
	public Intent createImageRequestIntent(String url) {
		Intent intent = createIntent(Action.START_DOWNLOAD, url);
		intent.putExtra(Extra.RESOURCE_TYPE, ResourceType.IMAGE.ordinal());
		
		return intent;
	}
	
	/**
	 * Creates an Intent that can be used to stop downloading a resource. 
	 * 
	 * @param url The URL that is being downloaded.
	 */
	public Intent createStopRequestIntent(String url) {
		Intent intent = createIntent(Action.STOP_DOWNLOAD, url);

		return intent;
	}
	
	/**
	 * Creates an Intent that notifies the application about failed downloads.
	 * 
	 * @param url The URL that was being downloaded.
	 * @param message A message describing the error.
	 */
	public Intent createErrorResponseIntent(String url, String message) {
		Intent intent = createIntent(Action.ERROR, url);
		intent.putExtra(Extra.ERROR_MESSAGE, message);
		
		return intent;
	}
	
	/**
	 * Creates an Intent that notifies the application about a successful plain 
	 * text download.
	 * 
	 * @param url The URL that was being downloaded.
	 * @param response The response retrieved from the server.
	 */
	public Intent createTextResponseIntent(String url, String response) {
		Intent intent = createIntent(DownloadIntent.Action.RESPONSE, url);
		intent.putExtra(Extra.TEXT_RESPONSE, response);
		intent.putExtra(Extra.RESOURCE_TYPE, ResourceType.PLAIN_TEXT);
		
		return intent;
	}
	
	/**
	 * Creates an Intent that notifies the application about a successful image
	 * download.
	 * 
	 * @param url The URL that was being downloaded.
	 * @param response The response retrieved from the server.
	 */
	public Intent createImageResponseIntent(String url, Bitmap response) {
		Intent intent = createIntent(Action.RESPONSE, url);
		intent.putExtra(Extra.IMAGE_RESPONSE, response);
		intent.putExtra(Extra.RESOURCE_TYPE, ResourceType.IMAGE);
		
		return intent;
	}
	
}
