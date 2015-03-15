package nl.ansuz.android.asylum.net.mediatype;

/**
 * Image Internet Media Type.
 * @author wijnand
 */
public class ImageType extends InternetMediaType {

    private static final String IMAGE_TYPE = "image";
    private static final String GIF_TYPE = "gif";
    private static final String JPEG_TYPE = "jpeg";
    private static final String PJPEG_TYPE = "pjpeg";
    private static final String PNG_TYPE = "png";
    private static final String SVG_XML_TYPE = "svg+xml";
    private static final String TIFF_TYPE = "tiff";
    private static final String VND_DJVU_TYPE = "vnd.djvu";
    private static final String EXAMPLE_TYPE = "example";

    public static final InternetMediaType ANY = new ImageType(WILDCARD);
    public static final InternetMediaType GIF = new ImageType(GIF_TYPE);
    public static final InternetMediaType JPEG = new ImageType(JPEG_TYPE);
    public static final InternetMediaType PJPEG = new ImageType(PJPEG_TYPE);
    public static final InternetMediaType PNG = new ImageType(PNG_TYPE);
    public static final InternetMediaType SVG_XML = new ImageType(SVG_XML_TYPE);
    public static final InternetMediaType TIFF = new ImageType(TIFF_TYPE);
    public static final InternetMediaType VND_DJVU = new ImageType(VND_DJVU_TYPE);
    public static final InternetMediaType EXAMPLE = new ImageType(EXAMPLE_TYPE);

    /**
     * Creates a new {@link ImageType} instance.
     * @param subtype String - Subtype name.
     */
    private ImageType(String subtype) {
        super(IMAGE_TYPE, subtype);
    }
}
