package nl.ansuz.android.asylum.net.mediatype;

/**
 * Text Internet Media Type.
 * @author wijnand
 */
public class TextType extends InternetMediaType {

    private static final String TEXT_TYPE = "text";

    private static final String CMD_TYPE = "cmd";
    private static final String CSS_TYPE = "css";
    private static final String CSV_TYPE = "csv";
    private static final String EXAMPLE_TYPE = "example";
    private static final String HTML_TYPE = "html";
    private static final String JAVASCRIPT_TYPE = "javascript";
    private static final String PLAIN_TYPE = "plain";
    private static final String RTF_TYPE = "rtf";
    private static final String VCARD_TYPE = "vcard";
    private static final String VND_A_TYPE = VENDOR_PREFIX + "a";
    private static final String VND_ABC_TYPE = VENDOR_PREFIX + "abc";
    private static final String XML_TYPE = "xml";

    public static final InternetMediaType ANY = new TextType(WILDCARD);
    public static final InternetMediaType CMD = new TextType(CMD_TYPE);
    public static final InternetMediaType CSS = new TextType(CSS_TYPE);
    public static final InternetMediaType CSV = new TextType(CSV_TYPE);
    public static final InternetMediaType EXAMPLE = new TextType(EXAMPLE_TYPE);
    public static final InternetMediaType HTML = new TextType(HTML_TYPE);
    public static final InternetMediaType JAVASCRIPT = new TextType(JAVASCRIPT_TYPE);
    public static final InternetMediaType PLAIN = new TextType(PLAIN_TYPE);
    public static final InternetMediaType RTF = new TextType(RTF_TYPE);
    public static final InternetMediaType VCARD = new TextType(VCARD_TYPE);
    public static final InternetMediaType VND_A = new TextType(VND_A_TYPE);
    public static final InternetMediaType VND_ABC = new TextType(VND_ABC_TYPE);
    public static final InternetMediaType XML = new TextType(XML_TYPE);

    /**
     * Creates a new {@link TextType} instance.
     * @param subtype String - Subtype name.
     */
    private TextType(String subtype) {
        super(TEXT_TYPE, subtype);
    }
}
