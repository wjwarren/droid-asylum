package nl.ansuz.android.asylum.events;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * @author Wijnand
 */
public class DownloadEvent implements Parcelable {

	private static final String LOG_TAG = "DownloadEvent";

	/**
	 * The different type of resources that can be downloaded.
	 *
	 * @author Wijnand
	 */
	public enum ResourceType {
		PLAIN_TEXT,
		IMAGE,
		BINARY;

		private static final ResourceType[] resourceValues = ResourceType.values();

		public static ResourceType fromInteger(int i) {
			return resourceValues[i];
		}
	}

	/**
	 * Simple list of default actions for this event.
	 *
	 * @author Wijnand
	 */
	public enum Action {
		START_DOWNLOAD,
		PAUSE_DOWNLOAD,
		STOP_DOWNLOAD,
		RESPONSE,
		ERROR;

		private static final Action[] actionValues = Action.values();

		public static Action fromInteger(int i) {
			return actionValues[i];
		}
	}

	public static final Creator<DownloadEvent> CREATOR = new Creator<DownloadEvent>() {
		@Override
		public DownloadEvent createFromParcel(Parcel source) {
			return new DownloadEvent(source);
		}

		@Override
		public DownloadEvent[] newArray(int size) {
			return new DownloadEvent[size];
		}
	};

	private Action action;
	private String url;
	private ResourceType resourceType;

	private String plainTextData;
	private Bitmap imageData;
	private String errorText;

	/**
	 * CONSTRUCTOR
	 */
	private DownloadEvent() {
		action = null;
		url = "";
		resourceType = null;

		plainTextData = "";
		imageData = null;
		errorText = "";
	}

	/**
	 * CONSTRUCTOR
	 * @param source Parcel to use to (re)construct the data.
	 */
	private DownloadEvent(Parcel source) {
		this();

		action = Action.fromInteger(source.readInt());
		url = source.readString();
		resourceType = ResourceType.fromInteger(source.readInt());

		plainTextData = source.readString();
		errorText = source.readString();
		if (resourceType == ResourceType.IMAGE) {
			imageData = Bitmap.CREATOR.createFromParcel(source);
		}
	}

	/**
	 * CONSTRUCTOR
	 *
	 * @param action Action - The action to take / that was taken.
	 * @param url String - The URL for the download.
	 * @param resourceType ResourceType - Type of resource that we're dealing with.
	 */
	public DownloadEvent(Action action, String url, ResourceType resourceType) {
		this();
		this.action = action;
		this.url = url;
		this.resourceType = resourceType;
	}

	/**
	 * CONSTRUCTOR
	 *
	 * @param response Bitmap - The downloaded image.
	 * @param url String - The URL for the download.
	 */
	public DownloadEvent(Bitmap response, String url) {
		this(Action.RESPONSE, url, ResourceType.IMAGE);
		imageData = response;
	}

	/**
	 * CONSTRUCTOR
	 *
	 * @param response String - Download result.
	 * @param action Action - The action that was taken, use either ERROR or RESPONSE.
	 * @param url String - The URL for the download.
	 */
	public DownloadEvent(String response, Action action, String url) {
		this();
		this.action = action;
		this.url = url;

		if (action == Action.ERROR) {
			resourceType = null;
			errorText = response;
		} else {
			resourceType = ResourceType.PLAIN_TEXT;
			plainTextData = response;
		}
	}

	public Action getAction() {
		return action;
	}

	public String getUrl() {
		return url;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public String getPlainTextData() {
		return plainTextData;
	}

	public Bitmap getImageData() {
		return imageData;
	}

	public String getErrorText() {
		return errorText;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel destination, int flags) {
		destination.writeInt(action.ordinal());
		destination.writeString(url);
		destination.writeInt(resourceType.ordinal());

		destination.writeString(plainTextData);
		destination.writeString(errorText);
		if (imageData != null) {
			imageData.writeToParcel(destination, flags);
		}
	}
}
