package nl.ansuz.android.asylum.net.mediatype;

/**
 * Video Internet Media Type.
 * @author wijnand
 */
public class VideoType extends InternetMediaType {

    private static final String VIDEO_TYPE = "video";
    private static final String AVI_TYPE = "avi";
    private static final String EXAMPLE_TYPE = "example";
    private static final String QUICKTIME_TYPE = "quicktime";
    private static final String MATROSKA_TYPE = EXPERIMENTAL_PREFIX + "matroska";
    private static final String WMV_TYPE = EXPERIMENTAL_PREFIX + "ms-wmv";
    private static final String FLV_TYPE = "flv";

    public static final InternetMediaType ANY = new VideoType(WILDCARD);
    public static final InternetMediaType AVI = new VideoType(AVI_TYPE);
    public static final InternetMediaType EXAMPLE = new VideoType(EXAMPLE_TYPE);
    public static final InternetMediaType MPEG = new VideoType(AudioType.MPEG_TYPE);
    public static final InternetMediaType MP4 = new VideoType(AudioType.MP4_TYPE);
    public static final InternetMediaType OGG = new VideoType(AudioType.OGG_TYPE);
    public static final InternetMediaType QUICKTIME = new VideoType(QUICKTIME_TYPE);
    public static final InternetMediaType WEBM = new VideoType(AudioType.WEBM_TYPE);
    public static final InternetMediaType MATROSKA = new VideoType(MATROSKA_TYPE);
    public static final InternetMediaType WMV = new VideoType(WMV_TYPE);
    public static final InternetMediaType FLV = new VideoType(FLV_TYPE);

    /**
     * Creates a new {@link VideoType} instance.
     * @param subtype String - Subtype name.
     */
    private VideoType(String subtype) {
        super(VIDEO_TYPE, subtype);
    }
}
