package nl.ansuz.android.asylum.net;

/**
 * URL load exception that will be thrown when the UrlLoader class encounters a
 * problem while loading a URL.
 *   
 * @author Wijnand
 */
public class UrlLoadException extends Exception {

	private static final long serialVersionUID = 5649096004853977420L;
	
	public static final String MESSAGE_MALFORMED_URL = "Malformed URL.";
	public static final String MESSAGE_READ_ERROR = "Stream read error.";
	public static final String MESSAGE_UNKOWN_RESOURCE = "Unkown resource type: ";

	public UrlLoadException() {
		super();
	}

	public UrlLoadException(String detailMessage) {
		super(detailMessage);
	}

	public UrlLoadException(Throwable throwable) {
		super(throwable);
	}

	public UrlLoadException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

}
