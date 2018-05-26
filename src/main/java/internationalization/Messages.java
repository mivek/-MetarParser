package internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Messages class for internationalization.
 * @author mivek
 */
public final class Messages {
    /**
     * Name of the bundle.
     */
    private static final String BUNDLE_NAME = "internationalization.messages"; //$NON-NLS-1$

    /**
     * Bundle variable.
     */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());
    /**
     * Cloud quantity broken.
     */
    public static final String CLOUD_QUANTITY_BKN = RESOURCE_BUNDLE.getString("CloudQuantity.BKN");
    /**
     * Cloud quantity few.
     */
    public static final String CLOUD_QUANTITY_FEW = RESOURCE_BUNDLE.getString("CloudQuantity.FEW");
    /**
     * Cloud quantity overcast.
     */
    public static final String CLOUD_QUANTITY_OVC = RESOURCE_BUNDLE.getString("CloudQuantity.OVC");
    /**
     * Cloud quantity scattered.
     */
    public static final String CLOUD_QUANTITY_SCT = RESOURCE_BUNDLE.getString("CloudQuantity.SCT");
    /**
     * Cloud quantity sky clear.
     */
    public static final String CLOUD_QUANTITY_SKC = RESOURCE_BUNDLE.getString("CloudQuantity.SKC");
    /**
     * Cloud type cumulonimbus.
     */
    public static final String CLOUD_TYPE_CB = RESOURCE_BUNDLE.getString("CloudType.CB");
    /**
     * Cloud type towering cumulus.
     */
    public static final String CLOUD_TYPE_TCU = RESOURCE_BUNDLE.getString("CloudType.TCU");
    /**
     * CloudType altocumulus.
     */
    public static final String CLOUD_TYPE_AC = RESOURCE_BUNDLE.getString("CloudType.AC");
    /**
     * Cloud type cirrus.
     */
    public static final String CLOUD_TYPE_CI = RESOURCE_BUNDLE.getString("CloudType.CI");
    /**
     * Cloud type corrocumulus.
     */
    public static final String CLOUD_TYPE_CC = RESOURCE_BUNDLE.getString("CloudType.CC");
    /**
     * Cloud type cirrostratus.
     */
    public static final String CLOUD_TYPE_CS = RESOURCE_BUNDLE.getString("CloudType.CS");
    /**
     * Cloud type stratus.
     */
    public static final String CLOUD_TYPE_ST = RESOURCE_BUNDLE.getString("CloudType.ST");
    /**
     * Cloud type Cumulus.
     */
    public static final String CLOUD_TYPE_CU = RESOURCE_BUNDLE.getString("CloudType.CU");
    /**
     * Cloud type Astrostratus.
     */
    public static final String CLOUD_TYPE_AS = RESOURCE_BUNDLE.getString("CloudType.AS");
    /**
     * Cloud type Nimbostratus.
     */
    public static final String CLOUD_TYPE_NS = RESOURCE_BUNDLE.getString("CloudType.NS");
    /**
     * Cloud type stratocumulus.
     */
    public static final String CLOUD_TYPE_SC = RESOURCE_BUNDLE.getString("CloudType.SC");
    /**
     * Descriptive patches.
     */
    public static final String DESCRIPTIVE_BC = RESOURCE_BUNDLE.getString("Descriptive.BC");
    /**
     * Descriptive blowing.
     */
    public static final String DESCRIPTIVE_BL = RESOURCE_BUNDLE.getString("Descriptive.BL");
    /**
     * Descriptive drifting.
     */
    public static final String DESCRIPTIVE_DR = RESOURCE_BUNDLE.getString("Descriptive.DR");
    /**
     * Descriptive freezing.
     */
    public static final String DESCRIPTIVE_FZ = RESOURCE_BUNDLE.getString("Descriptive.FZ");
    /**
     * Descriptive shallow.
     */
    public static final String DESCRIPTIVE_MI = RESOURCE_BUNDLE.getString("Descriptive.MI");
    /**
     * Descriptive partial.
     */
    public static final String DESCRIPTIVE_PR = RESOURCE_BUNDLE.getString("Descriptive.PR");
    /**
     * Descriptive shower.
     */
    public static final String DESCRIPTIVE_SH = RESOURCE_BUNDLE.getString("Descriptive.SH");
    /**
     * Descriptive thunderstorm.
     */
    public static final String DESCRIPTIVE_TS = RESOURCE_BUNDLE.getString("Descriptive.TS");

    /**
     * Intensity light.
     */
    public static final String INTENSITY_LIGHT = RESOURCE_BUNDLE.getString("Intensity.-");
    /**
     * Intensity heavy.
     */
    public static final String INTENSITY_HEAVY = RESOURCE_BUNDLE.getString("Intensity.+");
    /**
     * Intensity in vincinity.
     */
    public static final String INTENSITY_VC = RESOURCE_BUNDLE.getString("Intensity.VC");
    /**
     * Phenomenon mist.
     */
    public static final String PHENOMENON_BR = RESOURCE_BUNDLE.getString("Phenomenon.BR");
    /**
     * Phenomenon duststorm.
     */
    public static final String PHENOMENON_DS = RESOURCE_BUNDLE.getString("Phenomenon.DS");
    /**
     * Phenomenon widespread dust.
     */
    public static final String PHENOMENON_DU = RESOURCE_BUNDLE.getString("Phenomenon.DU");
    /**
     * Phenomenon drizzle.
     */
    public static final String PHENOMENON_DZ = RESOURCE_BUNDLE.getString("Phenomenon.DZ");
    /**
     * Phenomenon funnel cloud.
     */
    public static final String PHENOMENON_FC = RESOURCE_BUNDLE.getString("Phenomenon.FC");
    /**
     * Phenomenon fog.
     */
    public static final String PHENOMENON_FG = RESOURCE_BUNDLE.getString("Phenomenon.FG");
    /**
     * Phenomenon smoke.
     */
    public static final String PHENOMENON_FU = RESOURCE_BUNDLE.getString("Phenomenon.FU");
    /**
     * Phenomenon hail.
     */
    public static final String PHENOMENON_GR = RESOURCE_BUNDLE.getString("Phenomenon.GR");
    /**
     * Phenomenon small hail.
     */
    public static final String PHENOMENON_GS = RESOURCE_BUNDLE.getString("Phenomenon.GS");
    /**
     * Phenomenon haze.
     */
    public static final String PHENOMENON_HZ = RESOURCE_BUNDLE.getString("Phenomenon.HZ");
    /**
     * Phenomenon ice crystals.
     */
    public static final String PHENOMENON_IC = RESOURCE_BUNDLE.getString("Phenomenon.IC");
    /**
     * Phenomenon ice pellets.
     */
    public static final String PHENOMENON_PL = RESOURCE_BUNDLE.getString("Phenomenon.PL");
    /**
     * Phenomenon dust.
     */
    public static final String PHENOMENON_PO = RESOURCE_BUNDLE.getString("Phenomenon.PO");
    /**
     * Phenomenon spray.
     */
    public static final String PHENOMENON_PY = RESOURCE_BUNDLE.getString("Phenomenon.PY");
    /**
     * Phenomenon rain.
     */
    public static final String PHENOMENON_RA = RESOURCE_BUNDLE.getString("Phenomenon.RA");
    /**
     * Phenomenon sand.
     */
    public static final String PHENOMENON_SA = RESOURCE_BUNDLE.getString("Phenomenon.SA");
    /**
     * Phenomenon snow grains.
     */
    public static final String PHENOMENON_SG = RESOURCE_BUNDLE.getString("Phenomenon.SG");
    /**
     * Phenomenon snow.
     */
    public static final String PHENOMENON_SN = RESOURCE_BUNDLE.getString("Phenomenon.SN");
    /**
     * Phenomenon squall.
     */
    public static final String PHENOMENON_SQ = RESOURCE_BUNDLE.getString("Phenomenon.SQ");
    /**
     * Phenomenon sandstorm.
     */
    public static final String PHENOMENON_SS = RESOURCE_BUNDLE.getString("Phenomenon.SS");
    /**
     * Phenomenon unknown precipitation.
     */
    public static final String PHENOMENON_UP = RESOURCE_BUNDLE.getString("Phenomenon.UP");
    /**
     * Phenomenon volcanic ashes.
     */
    public static final String PHENOMENON_VA = RESOURCE_BUNDLE.getString("Phenomenon.VA");
    /**
     * Invalid icao message.
     */
    public static final String INVALID_ICAO = RESOURCE_BUNDLE.getString("MetarFacade.InvalidIcao");

    /**
     * Decreasing.
     */
    public static final String CONVERTER_D = RESOURCE_BUNDLE.getString("Converter.D");
    /**
     * East.
     */
    public static final String CONVERTER_E = RESOURCE_BUNDLE.getString("Converter.E");
    /**
     * North.
     */
    public static final String CONVERTER_N = RESOURCE_BUNDLE.getString("Converter.N");
    /**
     * North east.
     */
    public static final String CONVERTER_NE = RESOURCE_BUNDLE.getString("Converter.NE");
    /**
     * No significant change.
     */
    public static final String CONVERTER_NSC = RESOURCE_BUNDLE.getString("Converter.NSC");
    /**
     * North West.
     */
    public static final String CONVERTER_NW = RESOURCE_BUNDLE.getString("Converter.NW");
    /**
     * South.
     */
    public static final String CONVERTER_S = RESOURCE_BUNDLE.getString("Converter.S");
    /**
     * South East.
     */
    public static final String CONVERTER_SE = RESOURCE_BUNDLE.getString("Converter.SE");
    /**
     * South West.
     */
    public static final String CONVERTER_SW = RESOURCE_BUNDLE.getString("Converter.SW");
    /**
     * Uprising.
     */
    public static final String CONVERTER_U = RESOURCE_BUNDLE.getString("Converter.U");
    /**
     * Variable.
     */
    public static final String CONVERTER_VRB = RESOURCE_BUNDLE.getString("Converter.VRB");
    /**
     * West.
     */
    public static final String CONVERTER_W = RESOURCE_BUNDLE.getString("Converter.W");

    /**
     * TEMPO.
     */
    public static final String TEMPO = RESOURCE_BUNDLE.getString("WeatherChangeType.TEMPO");
    /**
     * FROM.
     */
    public static final String FROM = RESOURCE_BUNDLE.getString("WeatherChangeType.FM");
    /**
     * BECMG.
     */
    public static final String BECMG = RESOURCE_BUNDLE.getString("WeatherChangeType.BECMG");
    /**
     * PROB.
     */
    public static final String PROB = RESOURCE_BUNDLE.getString("WeatherChangeType.PROB");
    /**
     * AT.
     */
    public static final String AT = RESOURCE_BUNDLE.getString("TimeIndicator.AT");
    /**
     * TL.
     */
    public static final String TL = RESOURCE_BUNDLE.getString("TimeIndicator.TL");
    /**
     * Private constructor.
     */
    private Messages() {
    }

}
