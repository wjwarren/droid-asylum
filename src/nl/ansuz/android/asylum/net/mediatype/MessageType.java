package nl.ansuz.android.asylum.net.mediatype;

/**
 * Message Internet Media Type.
 * @author wijnand
 */
public class MessageType extends InternetMediaType {

    private static final String MESSAGE_TYPE = "message";
    private static final String HTTP_TYPE = "http";
    private static final String IMDN_XML_TYPE = "imdn+xml";
    private static final String PARTIAL_TYPE = "partial";
    private static final String RFC_822_TYPE = "rfc822";
    private static final String EXAMPLE_TYPE = "example";

    public static final InternetMediaType ANY = new MessageType(WILDCARD);
    public static final InternetMediaType HTTP = new MessageType(HTTP_TYPE);
    public static final InternetMediaType IMDN = new MessageType(IMDN_XML_TYPE);
    public static final InternetMediaType PARTIAL = new MessageType(PARTIAL_TYPE);
    public static final InternetMediaType RFC_822 = new MessageType(RFC_822_TYPE);
    public static final InternetMediaType EXAMPLE = new MessageType(EXAMPLE_TYPE);

    /**
     * Creates a new {@link MessageType} instance.
     * @param subtype String - Subtype name.
     */
    private MessageType(String subtype) {
        super(MESSAGE_TYPE, subtype);
    }
}
