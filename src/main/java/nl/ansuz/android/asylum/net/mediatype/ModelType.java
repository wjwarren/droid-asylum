package nl.ansuz.android.asylum.net.mediatype;

/**
 * Model Internet Media Type.
 * @author wijnand
 */
public class ModelType extends InternetMediaType {

    private static final String MODEL_TYPE = "model";
    private static final String IGES_TYPE = "iges";
    private static final String MESH_TYPE = "mesh";
    private static final String VRML_TYPE = "vrml";
    private static final String X3D_BINARY_TYPE = "x3d+binary";
    private static final String X3D_FASTINFOSET_TYPE = "x3d+fastinfoset";
    private static final String X3D_VRML_TYPE = "x3d-vrml";
    private static final String X3D_XML_TYPE = "x3d+xml";
    private static final String EXAMPLE_TYPE = "example";

    public static final InternetMediaType ANY = new ModelType(WILDCARD);
    public static final InternetMediaType IGES = new ModelType(IGES_TYPE);
    public static final InternetMediaType MESH = new ModelType(MESH_TYPE);
    public static final InternetMediaType VRML = new ModelType(VRML_TYPE);
    public static final InternetMediaType X3D_BINARY = new ModelType(X3D_BINARY_TYPE);
    public static final InternetMediaType X3D_FASTINFOSET = new ModelType(X3D_FASTINFOSET_TYPE);
    public static final InternetMediaType X3D_VRML = new ModelType(X3D_VRML_TYPE);
    public static final InternetMediaType X3D_XML = new ModelType(X3D_XML_TYPE);
    public static final InternetMediaType EXAMPLE = new ModelType(EXAMPLE_TYPE);

    /**
     * Creates a new {@link ModelType} instance.
     * @param subtype String - Subtype name.
     */
    private ModelType(String subtype) {
        super(MODEL_TYPE, subtype);
    }
}
