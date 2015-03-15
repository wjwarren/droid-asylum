package nl.ansuz.android.asylum.net.mediatype;

/**
 * Multipart Internet Media Type.
 * @author wijnand
 */
public class MultipartType extends InternetMediaType {

    private static final String MULTIPART_TYPE = "multipart";
    private static final String MIXED_TYPE = "mixed";
    private static final String ALTERNATIVE_TYPE = "alternative";
    private static final String RELATED_TYPE = "related";
    private static final String FORM_DATA_TYPE = "form-data";
    private static final String SIGNED_TYPE = "signed";
    private static final String ENCRYPTED_TYPE = "encrypted";
    private static final String EXAMPLE_TYPE = "example";

    public static final InternetMediaType ANY = new MultipartType(WILDCARD);
    public static final InternetMediaType MIXED = new MultipartType(MIXED_TYPE);
    public static final InternetMediaType ALTERNATIVE = new MultipartType(ALTERNATIVE_TYPE);
    public static final InternetMediaType RELATED = new MultipartType(RELATED_TYPE);
    public static final InternetMediaType FORM_DATA = new MultipartType(FORM_DATA_TYPE);
    public static final InternetMediaType SIGNED = new MultipartType(SIGNED_TYPE);
    public static final InternetMediaType ENCRYPTED = new MultipartType(ENCRYPTED_TYPE);
    public static final InternetMediaType EXAMPLE = new MultipartType(EXAMPLE_TYPE);

    /**
     * Creates a new {@link MultipartType} instance.
     * @param subtype String - Subtype name.
     */
    private MultipartType(String subtype) {
        super(MULTIPART_TYPE, subtype);
    }
}
