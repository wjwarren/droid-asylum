package nl.ansuz.android.asylum.net.mediatype;

/**
 * Audio Internet Media Type.
 * @author wijnand
 */
public class AudioType extends InternetMediaType {

    private static final String AUDIO_TYPE = "audio";
    private static final String BASIC_TYPE = "basic";
    private static final String L24_TYPE = "L24";
    static final String MP4_TYPE = "mp4";
    static final String MPEG_TYPE = "mpeg";
    static final String OGG_TYPE = "ogg";
    private static final String FLAC_TYPE = "flac";
    private static final String OPUS_TYPE = "opus";
    private static final String VORBIS_TYPE = "vorbis";
    private static final String REAL_AUDIO_TYPE = VENDOR_PREFIX + "rn-realaudio";
    private static final String WAVE_TYPE = VENDOR_PREFIX + "wave";
    static final String WEBM_TYPE = "webm";
    private static final String EXAMPLE_TYPE = "example";

    public static final InternetMediaType ANY = new AudioType(WILDCARD);
    public static final InternetMediaType BASIC = new AudioType(BASIC_TYPE);
    public static final InternetMediaType L24 = new AudioType(L24_TYPE);
    public static final InternetMediaType MP4 = new AudioType(MP4_TYPE);
    public static final InternetMediaType MPEG = new AudioType(MPEG_TYPE);
    public static final InternetMediaType OGG = new AudioType(OGG_TYPE);
    public static final InternetMediaType FLAC = new AudioType(FLAC_TYPE);
    public static final InternetMediaType OPUS = new AudioType(OPUS_TYPE);
    public static final InternetMediaType VORBIS = new AudioType(VORBIS_TYPE);
    public static final InternetMediaType REAL_AUDIO = new AudioType(REAL_AUDIO_TYPE);
    public static final InternetMediaType WAVE = new AudioType(WAVE_TYPE);
    public static final InternetMediaType WEBM = new AudioType(WEBM_TYPE);
    public static final InternetMediaType EXAMPLE = new AudioType(EXAMPLE_TYPE);

    /**
     * Creates a new {@link AudioType} instance.
     * @param subtype String - Subtype name.
     */
    private AudioType(String subtype) {
        super(AUDIO_TYPE, subtype);
    }
}
