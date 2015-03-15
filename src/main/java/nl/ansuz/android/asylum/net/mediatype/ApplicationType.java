package nl.ansuz.android.asylum.net.mediatype;

/**
 * Application Internet Media Type.
 * @author wijnand
 */
public class ApplicationType extends InternetMediaType {

    private static final String APPLICATION_TYPE = "application";
    private static final String ATOM_TYPE = "atom+xml";
    private static final String DART_TYPE = VENDOR_PREFIX + "dart";
    private static final String ECMASCRIPT_TYPE = "ecmascript";
    private static final String EDI_X12_TYPE = "EDI-X12";
    private static final String EDIFACT_TYPE = "EDIFACT";
    private static final String JSON_TYPE = "json";
    private static final String JAVASCRIPT_TYPE = "javascript";
    private static final String OCTET_STREAM_TYPE = "octet-stream";
    private static final String OGG_TYPE = "ogg";
    private static final String DASH_XML_TYPE = "dash+xml";
    private static final String PDF_TYPE = "pdf";
    private static final String POSTSCRIPT_TYPE = "postscript";
    private static final String RDF_XML_TYPE = "rdf+xml";
    private static final String RSS_XML_TYPE = "rss+xml";
    private static final String SOAP_XML_TYPE = "soap+xml";
    private static final String FONT_WOFF_TYPE = "font-woff";
    private static final String XHTML_XML_TYPE = "xhtml+xml";
    private static final String XML_TYPE = "xml";
    private static final String XML_DTD_TYPE = "xml-dtd";
    private static final String XOP_XML_TYPE = "xop+xml";
    private static final String ZIP_TYPE = "zip";
    private static final String GZIP_TYPE = "gzip";
    private static final String EXAMPLE_TYPE = "example";
    private static final String NACL_TYPE = EXPERIMENTAL_PREFIX + "nacl";
    private static final String PNACL_TYPE = EXPERIMENTAL_PREFIX + "pnacl";

    public static final InternetMediaType ANY = new ApplicationType(WILDCARD);
    public static final InternetMediaType ATOM = new ApplicationType(ATOM_TYPE);
    public static final InternetMediaType DART = new ApplicationType(DART_TYPE);
    public static final InternetMediaType ECMASCRIPT = new ApplicationType(ECMASCRIPT_TYPE);
    public static final InternetMediaType EDI_X12 = new ApplicationType(EDI_X12_TYPE);
    public static final InternetMediaType EDIFACT = new ApplicationType(EDIFACT_TYPE);
    public static final InternetMediaType JSON = new ApplicationType(JSON_TYPE);
    public static final InternetMediaType JAVASCRIPT = new ApplicationType(JAVASCRIPT_TYPE);
    public static final InternetMediaType OCTET_STREAM = new ApplicationType(OCTET_STREAM_TYPE);
    public static final InternetMediaType OGG = new ApplicationType(OGG_TYPE);
    public static final InternetMediaType DASH_XML = new ApplicationType(DASH_XML_TYPE);
    public static final InternetMediaType PDF = new ApplicationType(PDF_TYPE);
    public static final InternetMediaType POSTSCRIPT = new ApplicationType(POSTSCRIPT_TYPE);
    public static final InternetMediaType RDF_XML = new ApplicationType(RDF_XML_TYPE);
    public static final InternetMediaType RSS_XML = new ApplicationType(RSS_XML_TYPE);
    public static final InternetMediaType SOAP_XML = new ApplicationType(SOAP_XML_TYPE);
    public static final InternetMediaType FONT_WOFF = new ApplicationType(FONT_WOFF_TYPE);
    public static final InternetMediaType XHTML_XML = new ApplicationType(XHTML_XML_TYPE);
    public static final InternetMediaType XML = new ApplicationType(XML_TYPE);
    public static final InternetMediaType XML_DTD = new ApplicationType(XML_DTD_TYPE);
    public static final InternetMediaType XOP_XML = new ApplicationType(XOP_XML_TYPE);
    public static final InternetMediaType ZIP = new ApplicationType(ZIP_TYPE);
    public static final InternetMediaType GZIP = new ApplicationType(GZIP_TYPE);
    public static final InternetMediaType EXAMPLE = new ApplicationType(EXAMPLE_TYPE);
    public static final InternetMediaType NACL = new ApplicationType(NACL_TYPE);
    public static final InternetMediaType PNACL = new ApplicationType(PNACL_TYPE);

    /**
     * Creates a new {@link ApplicationType} instance.
     * @param subtype String - Subtype name.
     */
    private ApplicationType(String subtype) {
        super(APPLICATION_TYPE, subtype);
    }
}
