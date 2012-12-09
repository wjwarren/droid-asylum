package nl.ansuz.android.asylum.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Loads a resource from a URL.
 * 
 * Most of the code is based on Erik Hellman's presentation about "Fast, 
 * user-friendly and power-efficient network communication on Android".
 * 
 * @author Wijnand
 */
public class UrlLoader {
	
	// Read buffer size.
	public static final int BUFFER_SIZE = 1024;

	private static final String LOG_TAG = "UrlLoader";
	
	private URL url;
	private HttpURLConnection urlConnection;
	
	private char[] charInputBuffer;
	private StringBuilder stringBuilder;
	
	/**
	 * CONSTRUCTOR
	 */
	public UrlLoader() {
		init();
	}

	/**
	 * Initializes this class.
	 */
	private void init() {
		url = null;
		urlConnection = null;
		
		charInputBuffer = new char[BUFFER_SIZE];
		stringBuilder = new StringBuilder(BUFFER_SIZE);
	}
	
	/**
	 * Safely read the entire InputStream and return the result as a String.
	 * 
	 * @param stream The stream to read from.
	 * @param charset The Charset of the data on the stream.
	 * 
	 * @throws IOException
	 */
	private String readStreamAsString(InputStream stream, Charset charset) throws IOException {
		stringBuilder.delete(0, stringBuilder.length());
		
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(stream, charset);
			int charsRead;
			while((charsRead = inputStreamReader.read(charInputBuffer)) != -1) {
				stringBuilder.append(charInputBuffer, 0, charsRead);
			}
		} finally {
			try {
				if(stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				Log.e(LOG_TAG, "IOException when closing stream.", e);
			}
		}
		
		return stringBuilder.toString();
	}
	
	/**
	 * Reads the entire InputStream and return the result as a Bitmap.
	 * 
	 * @param input The stream to read from.
	 */
	private Bitmap readStreamAsBitmap(InputStream input) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inDensity = 72;
		options.inTargetDensity = 72;
		
		return BitmapFactory.decodeStream(input, null, options);
	}
	
	/**
	 * Generic method that tries to load a resource from a URL.
	 * 
	 * @param result The result Object of type T to use.
	 * @param url The URL to download the resource from.
	 * @param charset The Charset to use for the String.
	 * 
	 * @throws UrlLoadException
	 */
	private <T> T loadUrl(T result, String url, Charset charset) throws UrlLoadException {
		try {
			this.url = new URL(url);
		} catch (MalformedURLException e) {
			Log.e(LOG_TAG, "Malformed URL: " + url, e);
			this.url = null;
			throw new UrlLoadException(UrlLoadException.MESSAGE_MALFORMED_URL, e);
		}
		
		try {
			urlConnection = (HttpURLConnection) this.url.openConnection();
			InputStream input = new BufferedInputStream(urlConnection.getInputStream());
			if(result instanceof String) {
				result = (T) readStreamAsString(input, charset);
			} else if(result instanceof Bitmap) {
				result = (T) readStreamAsBitmap(input);
			} else {
				Log.w(LOG_TAG, "Unknown result Type: " + result);
			}
		} catch (IOException e) {
			Log.e(LOG_TAG, "IOException when reading: " + url, e);
			throw new UrlLoadException(UrlLoadException.MESSAGE_READ_ERROR, e);
		} finally {
			stop();
		}
		
		return result;
	}
	
	/**
	 * Tries to load a resource from a URL as a String.
	 * 
	 * @param url The URL to download the resource from.
	 * @param charset The Charset to use for the String.
	 * 
	 * @throws UrlLoadException
	 */
	public String loadUrlAsString(String url, Charset charset) throws UrlLoadException {
		return loadUrl("", url, charset);
	}
	
	/**
	 * Tries to load a resource from a URL as a Bitmap.
	 * 
	 * @param url The URL to download the resource from.
	 * 
	 * @throws UrlLoadException
	 */
	public Bitmap loadUrlAsImage(String url) throws UrlLoadException {
		return loadUrl(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888), url, null);
	}
	
	/**
	 * Stops the download.
	 */
	public void stop() {
		urlConnection.disconnect();
		urlConnection = null;
	}
	
	/**
	 * Clean up.
	 */
	public void destroy() {
		url = null;
		if(urlConnection != null) {
			urlConnection.disconnect();
			urlConnection = null;
		}
		
		charInputBuffer = null;
		
		stringBuilder.delete(0, stringBuilder.length());
		stringBuilder = null;
	}
}
