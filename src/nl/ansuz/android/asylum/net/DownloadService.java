package nl.ansuz.android.asylum.net;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import nl.ansuz.android.asylum.events.DownloadEvent;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Background service to download from URLs.
 * 
 * @author Wijnand
 */
public class DownloadService extends Service {

	/**
	 * Class for clients to access. Because we know this service always runs in
	 * the same process as its clients, we don't need to deal with IPC.
	 */
	public class LocalBinder extends Binder {
		public DownloadService getService() {
			return DownloadService.this;
		}
	}

	private static final String LOG_TAG = "DownLoadService";

	private ExecutorService threadPool;
	private Binder binder;

	/**
	 * Analyses the passed in Intent and executes a method based on the Intent's
	 * action.
	 * 
	 * @param intent The Intent to analyse.
	 */
	protected void handleIntent(Intent intent) {
		Log.v(LOG_TAG, "handleIntent()");

		DownloadEvent event = intent.getParcelableExtra(DownloadIntent.Extra.DOWNLOAD_EVENT);
		if (event == null) {
			Log.w(LOG_TAG, "Ignoring this request as no DownloadEvent was sent!!!");
		} else {
			startDownload(event.getUrl(), event.getResourceType());
		}
	}

	/**
	 * Creates the thread pool to use when downloading multiple items at once.
	 */
	private void createThreadPool() {
		threadPool = Executors.newCachedThreadPool();
	}

	/**
	 * Shuts down the thread pool and waits for termination.
	 */
	private void shutdownAndAwaitTermination() {
		// Disable new tasks from being submitted.
		threadPool.shutdown(); 
		try {
			// Wait a while for existing tasks to terminate.
			if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
				// Cancel currently executing tasks.
				threadPool.shutdownNow();
				// Wait a while for tasks to respond to being cancelled.
				if (!threadPool.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");

			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted.
			threadPool.shutdownNow();
			// Preserve interrupt status.
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Downloads a resource from a URL.
	 * 
	 * @param url The URL to download.
	 * @param resourceType The type of resource to download.
	 */
	protected void startDownload(String url, DownloadEvent.ResourceType resourceType) {
		Log.v(LOG_TAG, "startDownload()");
		
		threadPool.execute(new DownloadRunnable(getApplicationContext(), url, resourceType));
	}

	/**
	 * Stops the downloading of a resource.
	 * 
	 * @param url The URL to cancel downloading from.
	 */
	protected void stopDownload(String url) {
		// TODO How to cancel a Runnable?
	}

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	@Override
	public void onCreate() {
		Log.v(LOG_TAG, "onCreate()");

		binder = new LocalBinder();

		createThreadPool();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		handleIntent(intent);

		// If doesn't matter if the Service is shut down intermittently.
		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.v(LOG_TAG, "onDestroy()");

		// Shut down threads.
		shutdownAndAwaitTermination();
		threadPool = null;

		binder = null;
	}
}
