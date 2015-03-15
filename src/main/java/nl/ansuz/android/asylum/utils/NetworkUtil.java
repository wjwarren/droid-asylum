package nl.ansuz.android.asylum.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Various network utilities.
 *
 * Permissions required to use this class:
 * - {@link android.Manifest.permission#ACCESS_NETWORK_STATE}
 *
 * @author Wijnand
 */
public class NetworkUtil {

	private Context applicationContext;

	/**
	 * CONSTRUCTOR
	 */
	public NetworkUtil(Context application) {
		applicationContext = application;
	}

	/**
	 * @return Whether or not we have internet access.
	 */
	public boolean hasInternetAccess() {
		ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

		return networkInfo != null && networkInfo.isConnected();
	}
}
