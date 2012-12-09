package nl.ansuz.android.asylum.net;

import java.nio.charset.Charset;

import nl.ansuz.android.asylum.net.DownloadIntent.ResourceType;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.LocalBroadcastManager;

/**
 * A Runnable that can download a resource.
 * 
 * @author Wijnand
 */
public class DownloadRunnable implements Runnable {

	private UrlLoader loader;
	private String url;
	private ResourceType resourceType;
	private Context context;
	private LocalBroadcastManager broadcaster;
	private DownloadIntent intentHelper;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param context
	 * @param url
	 * @param resourceType
	 */
	public DownloadRunnable(Context context, String url, ResourceType resourceType) {
		this.context = context;
		this.url = url;
		this.resourceType = resourceType;
		
		init();
	}
	
	/**
	 * Initializes this class.
	 */
	private void init() {
		loader = new UrlLoader();
		broadcaster = LocalBroadcastManager.getInstance(context);
		intentHelper = new DownloadIntent();
	}
	
	/**
	 * Starts the downloading or a resource based on its type.
	 */
	private void download() {
		switch(resourceType) {
		case IMAGE:
			downloadAsImage();
			break;
		case PLAIN_TEXT:
			downloadAsText();
			break;
		case BINARY:
		default:
			sendErrorIntent(UrlLoadException.MESSAGE_UNKOWN_RESOURCE + resourceType);
			break;
		}
	}

	/**
	 * Downloads an image.
	 */
	private void downloadAsImage() {
		try {
			Bitmap responseImage = loader.loadUrlAsImage(url);
			
			Intent intent = intentHelper.createImageResponseIntent(url, responseImage);
			broadcaster.sendBroadcast(intent);
		} catch (UrlLoadException e) {
			sendErrorIntent(e.getMessage());
		}
		
	}
	
	/**
	 * Downloads plain text.
	 */
	private void downloadAsText() {
		try {
			String responseText = loader.loadUrlAsString(url, Charset.defaultCharset());
			
			Intent intent = intentHelper.createTextResponseIntent(url, responseText);
			broadcaster.sendBroadcast(intent);
		} catch (UrlLoadException e) {
			sendErrorIntent(e.getMessage());
		}
	
	}

	/**
	 * Creates and sends an error intent when a download has failed.
	 * 
	 * @param message The error message to send.
	 */
	private void sendErrorIntent(String message) {
		Intent intent = intentHelper.createErrorResponseIntent(url, message);
		broadcaster.sendBroadcast(intent);
	}

	/**
	 * Cleans up this class.
	 * Call before losing the reference.
	 */
	private void destroy() {
		loader.destroy();
		loader = null;
		
		broadcaster = null;
		context = null;
	}

	@Override
	public void run() {
		download();
		destroy();
	}

}
