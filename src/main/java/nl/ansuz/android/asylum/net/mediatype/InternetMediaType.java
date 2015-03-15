package nl.ansuz.android.asylum.net.mediatype;

/**
 * Simple representation of an Internet Media Type.<br/>
 * "An Internet media type is a standard identifier used on the Internet to indicate the type of data that a file
 * contains."<br/>
 * See <a href="http://en.wikipedia.org/wiki/Internet_media_type">Internet Media Type</a> on Wikipedia.
 * @author wijnand
 */
public class InternetMediaType {

    private static final String TYPE_DIVIDER = "/";
    static final String WILDCARD = "*";
    static final String VENDOR_PREFIX = "vnd.";
    static final String EXPERIMENTAL_PREFIX = "x-";

    private final String mType;
    private final String mSubtype;

    public static final InternetMediaType ANY = new InternetMediaType(WILDCARD, WILDCARD);

    /**
     * Creates a new {@link InternetMediaType} instance.
     * @param type String - Top-level type name.
     * @param subtype String - Subtype name.
     */
    InternetMediaType(String type, String subtype) {
        mType = type;
        mSubtype = subtype;
    }

    /**
     * @return String - Top-level type name.
     */
    public String getType() {
        return mType;
    }

    /**
     * @return String - Subtype name.
     */
    public String getSubtype() {
        return mSubtype;
    }

    /**
     * @return String - String representation of this media type.
     */
    @Override
    public String toString() {
        return getType() + TYPE_DIVIDER + getSubtype();
    }

}
