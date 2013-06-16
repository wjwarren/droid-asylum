package nl.ansuz.android.asylum.net;

import java.nio.charset.Charset;

import android.content.Context;
import android.os.Handler;
import nl.ansuz.android.asylum.bus.SingletonBus;
import nl.ansuz.android.asylum.events.DownloadEvent;
import android.graphics.Bitmap;

/**
 * A Runnable that can download a resource.
 * 
 * @author Wijnand
 */
public class DownloadRunnable implements Runnable {

	private Context context;
	private String url;
	private DownloadEvent.ResourceType resourceType;

	private UrlLoader loader;
	private Handler mainHandler;

	/**
	 * CONSTRUCTOR
	 *
	 * @param context Context - Application context to use to post the results.
	 * @param url String - The url to download.
	 * @param resourceType - The type of resource that is being downloaded.
	 */
	public DownloadRunnable(Context context, String url, DownloadEvent.ResourceType resourceType) {
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
		mainHandler = new Handler(context.getMainLooper());
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
			postResult(new DownloadEvent(responseImage, url));
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
			postResult(new DownloadEvent(responseText, DownloadEvent.Action.RESPONSE, url));
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
		postResult(new DownloadEvent(message, DownloadEvent.Action.ERROR, url));
	}

	/**
	 * Posts the download result on the main thread.
	 *
	 * @param event DownloadEvent - The results to pass along.
	 */
	private void postResult(final DownloadEvent event) {
		Runnable resultRunnable = new Runnable() {
			@Override
			public void run() {
				SingletonBus.getInstance().post(event);
			}
		};

		mainHandler.post(resultRunnable);
	}

	/**
	 * Cleans up this class.
	 * Call before losing the reference.
	 */
	private void destroy() {
		loader.destroy();
		loader = null;
	}

	@Override
	public void run() {
		download();
		destroy();
	}

}
