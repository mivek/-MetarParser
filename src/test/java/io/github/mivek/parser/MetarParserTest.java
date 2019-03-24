/**
 *
 */
package io.github.mivek.parser;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.enums.CloudType;
import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.enums.TimeIndicator;
import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.exception.ErrorCodes;
import io.github.mivek.exception.ParseException;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.Cloud;
import io.github.mivek.model.Metar;
import io.github.mivek.model.RunwayInfo;
import io.github.mivek.model.Visibility;
import io.github.mivek.model.WeatherCondition;
import io.github.mivek.model.Wind;
import io.github.mivek.model.trend.AbstractMetarTrend;

/**
 * Test class for {@link MetarParser}
 * @author mivek
 *
 */
public class MetarParserTest extends AbstractParserTest<Metar> {

    private MetarParser fSut;

    @Override
    protected MetarParser getSut() {
        return fSut;
    }

    @Before
    public void setUp() {
        fSut = MetarParser.getInstance();
    }

    /**
     * ======================== Test ParseRunWays ========================
     */
    @Test
    public void testParseRunwayActionSimple() {
        String riString = "R26/0600U";

        RunwayInfo ri = fSut.parseRunWayAction(riString);

        assertNotNull(ri);
        assertEquals("26", ri.getName());
        assertEquals(600, ri.getMinRange());
        assertEquals(Messages.getInstance().getString("Converter.U"), ri.getTrend());
    }

    @Test
    public void testParseRunWaysComplex() {
        String riString = "R26L/0550V700U";

        RunwayInfo ri = fSut.parseRunWayAction(riString);
        assertNotNull(ri);
        assertEquals("26L", ri.getName());
        assertEquals(550, ri.getMinRange());
        assertEquals(700, ri.getMaxRange());
        assertEquals(Messages.getInstance().getString("Converter.U"), ri.getTrend());
    }

    @Test
    public void testParseRunWayNull() {
        String riString = "R26R/AZEZFDFS";

        RunwayInfo ri = fSut.parseRunWayAction(riString);

        assertNull(ri);
    }

    /**
     * =========================== Test ParseMetarAction ===========================
     */

    @Test
    public void testParse() throws ParseException {
        String metarString = "LFPG 170830Z 00000KT 0350 R27L/0375N R09R/0175N R26R/0500D R08L/0400N R26L/0275D R08R/0250N R27R/0300N R09L/0200N FG SCT000 M01/M01 Q1026 NOSIG";

        Metar m = fSut.parse(metarString);

        assertNotNull(m);

        assertEquals(fSut.getAirports().get("LFPG"), m.getAirport());
        assertEquals(Integer.valueOf(17), m.getDay());
        assertEquals(8, m.getTime().getHour());
        assertEquals(30, m.getTime().getMinute());
        assertNotNull(m.getWind());
        assertEquals(0, m.getWind().getSpeed());
        assertEquals(Messages.getInstance().getString("Converter.N"), m.getWind().getDirection());
        assertEquals("KT", m.getWind().getUnit());
        assertEquals("350m", m.getVisibility().getMainVisibility());
        assertThat(m.getRunways(), is(not(empty())));
        assertThat(m.getRunways(), hasSize(8));
        // Check if runways are correctly parsed
        assertEquals("27L", m.getRunways().get(0).getName());
        assertEquals(375, m.getRunways().get(0).getMinRange());
        assertEquals(Messages.getInstance().getString("Converter.NSC"), m.getRunways().get(0).getTrend());
    }

    @Test
    public void testParseNullAirport() throws ParseException {
        String metarString = "AAAA 170830Z 00000KT 0350 R27L/0375N R09R/0175N R26R/0500D R08L/0400N R26L/0275D R08R/0250N R27R/0300N R09L/0200N FG SCT000 M01/M01 Q1026 NOSIG";
        thrown.expect(ParseException.class);
        thrown.expect(hasProperty("errorCode", is(ErrorCodes.ERROR_CODE_AIRPORT_NOT_FOUND)));
        fSut.parse(metarString);
    }

    @Test
    public void testParseWithTempo() throws ParseException {
        String metarString = "LFBG 081130Z AUTO 23012KT 9999 SCT022 BKN072 BKN090 22/16 Q1011 TEMPO 26015G25KT 3000 TSRA SCT025CB BKN050";

        Metar m = fSut.parse(metarString);
        assertNotNull(m);
        assertTrue(m.isAuto());
        assertThat(m.getClouds(), hasSize(3));
        assertThat(m.getTrends(), hasSize(1));
        AbstractMetarTrend trend = m.getTrends().get(0);
        assertThat(trend.getType(), is(WeatherChangeType.TEMPO));
        assertNotNull(trend.getWind());
        assertEquals(Integer.valueOf(260), trend.getWind().getDirectionDegrees());
        assertEquals(15, trend.getWind().getSpeed());
        assertEquals(25, trend.getWind().getGust());
        assertThat(trend.getTimes(), hasSize(0));
        assertNotNull(trend.getVisibility());
        assertEquals("3000m", trend.getVisibility().getMainVisibility());
        assertThat(trend.getWeatherConditions(), hasSize(1));
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.THUNDERSTORM, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertEquals(Phenomenon.RAIN, wc.getPhenomenons().get(0));
        assertThat(trend.getClouds(), hasSize(2));
        Cloud c1 = trend.getClouds().get(0);
        assertEquals(CloudQuantity.SCT, c1.getQuantity());
        assertEquals(2500, c1.getHeight());
        assertEquals(CloudType.CB, c1.getType());
        Cloud c2 = trend.getClouds().get(1);
        assertEquals(CloudQuantity.BKN, c2.getQuantity());
        assertEquals(5000, c2.getHeight());
    }

    @Test
    public void testParseWithTempoAndBecmg() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO SHRA BECMG SKC";

        Metar m = fSut.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(2));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        WeatherCondition wc = m.getTrends().get(0).getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(m.getTrends().get(1).getType(), is(WeatherChangeType.BECMG));
        assertThat(m.getTrends().get(1).getClouds(), hasSize(1));
    }

    @Test
    public void testParseWithTempoAndAT() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO AT0800 SHRA ";

        Metar m = fSut.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(1));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        AbstractMetarTrend trend = m.getTrends().get(0);
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(trend.getTimes(), hasSize(1));
        assertEquals(TimeIndicator.AT, trend.getTimes().get(0).getType());
        assertEquals(8, trend.getTimes().get(0).getTime().getHour());
        assertEquals(0, trend.getTimes().get(0).getTime().getMinute());
    }

    @Test
    public void testParseWithTempoAndTL() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO TL1830 SHRA ";

        Metar m = fSut.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(1));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        AbstractMetarTrend trend = m.getTrends().get(0);
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(trend.getTimes(), hasSize(1));
        assertEquals(TimeIndicator.TL, trend.getTimes().get(0).getType());
        assertEquals(18, trend.getTimes().get(0).getTime().getHour());
        assertEquals(30, trend.getTimes().get(0).getTime().getMinute());
    }

    @Test
    public void testParseWithTempoAndFM() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO FM1830 SHRA ";

        Metar m = fSut.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(1));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        AbstractMetarTrend trend = m.getTrends().get(0);
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(trend.getTimes(), hasSize(1));
        assertEquals(TimeIndicator.FM, trend.getTimes().get(0).getType());
        assertEquals(18, trend.getTimes().get(0).getTime().getHour());
        assertEquals(30, trend.getTimes().get(0).getTime().getMinute());
    }

    @Test
    public void testParseWithTempoAndFMAndTL() throws ParseException {
        String metarString = "LFRM 081630Z AUTO 30007KT 260V360 9999 24/15 Q1008 TEMPO FM1700 TL1830 SHRA ";

        Metar m = fSut.parse(metarString);

        assertNotNull(m);
        assertThat(m.getTrends(), hasSize(1));
        assertThat(m.getTrends().get(0).getType(), is(WeatherChangeType.TEMPO));
        assertThat(m.getTrends().get(0).getWeatherConditions(), hasSize(1));
        AbstractMetarTrend trend = m.getTrends().get(0);
        WeatherCondition wc = trend.getWeatherConditions().get(0);
        assertEquals(Descriptive.SHOWERS, wc.getDescriptive());
        assertThat(wc.getPhenomenons(), hasSize(1));
        assertThat(trend.getTimes(), hasSize(2));
        assertEquals(TimeIndicator.FM, trend.getTimes().get(0).getType());
        assertEquals(17, trend.getTimes().get(0).getTime().getHour());
        assertEquals(0, trend.getTimes().get(0).getTime().getMinute());
        assertEquals(TimeIndicator.TL, trend.getTimes().get(1).getType());
        assertEquals(18, trend.getTimes().get(1).getTime().getHour());
        assertEquals(30, trend.getTimes().get(1).getTime().getMinute());
    }

    @Test
    public void testParseWithMinVisibility() throws ParseException {
        String code = "LFPG 161430Z 24015G25KT 5000 1100w";

        Metar m = fSut.parse(code);

        assertNotNull(m);
        assertEquals(16, m.getDay().intValue());
        assertEquals(14, m.getTime().getHour());
        assertEquals(30, m.getTime().getMinute());
        assertNotNull(m.getWind());
        Wind w = m.getWind();
        assertEquals(240, w.getDirectionDegrees().intValue());
        assertEquals(15, w.getSpeed());
        assertEquals(25, w.getGust());
        assertNotNull(m.getVisibility());
        Visibility v = m.getVisibility();
        assertEquals("5000m", v.getMainVisibility());
        assertEquals(1100, v.getMinVisibility());
        assertEquals("w", v.getMinDirection());
    }

    @Test
    public void testParseWithMaximalWind() throws ParseException {
        // Given a code with wind variation.
        String code = "LFPG 161430Z 24015G25KT 180V300";
        //WHEN parsing the code.
        Metar m = fSut.parse(code);
        // THEN the wind contains information on variation
        assertNotNull(m);
        assertEquals(240, m.getWind().getDirectionDegrees().intValue());
        assertEquals(15, m.getWind().getSpeed());
        assertEquals(25, m.getWind().getGust());
        assertEquals("KT", m.getWind().getUnit());
        assertEquals(180, m.getWind().getExtreme1());
        assertEquals(300, m.getWind().getExtreme2());

    }

    @Test
    public void testParseWithVerticalVisibility() throws ParseException {
        String code = "LFLL 160730Z 28002KT 0350 FG VV002";

        Metar m = fSut.parse(code);

        assertNotNull(m);
        assertEquals(16, m.getDay().intValue());
        assertEquals(7, m.getTime().getHour());
        assertEquals(30, m.getTime().getMinute());
        assertNotNull(m.getWind());
        Wind w = m.getWind();
        assertEquals(280, w.getDirectionDegrees().intValue());
        assertEquals(2, w.getSpeed());

        assertNotNull(m.getVisibility());
        assertEquals("350m", m.getVisibility().getMainVisibility());
        assertThat(m.getWeatherConditions(), hasSize(1));
        assertEquals(Phenomenon.FOG, m.getWeatherConditions().get(0).getPhenomenons().get(0));
        assertNotNull(m.getVerticalVisibility());
        assertEquals(200, m.getVerticalVisibility().intValue());
    }

    @Test
    public void testParseVisibilityWithNDV() throws ParseException {
        String code = "LSZL 300320Z AUTO 00000KT 9999NDV BKN060 OVC074 00/M04 Q1001\n" + "RMK=";
        Metar m = fSut.parse(code);
        assertNotNull(m);
        assertEquals(">10km", m.getVisibility().getMainVisibility());
    }

    @Test
    public void testParseWithCavok() throws ParseException {
        // GIVEN a metar with token CAVOK
        String code = "LFPG 212030Z 03003KT CAVOK 09/06 Q1031 NOSIG";
        // WHEN parsing the metar.
        Metar m = fSut.parse(code);
        // THEN the attribute cavok is true and the main visibility is > 10km.
        assertNotNull(m);
        assertTrue(m.isCavok());
        assertEquals(">10km", m.getVisibility().getMainVisibility());
    }

    @Test
    public void testParseWithAltimeterInMercury() throws ParseException {
        // GIVEN a metar with altimeter in inches of mercury
        String code = "KTTN 051853Z 04011KT 9999 VCTS SN FZFG BKN003 OVC010 M02/M02 A3006";
        // WHEN parsing the metar
        Metar m = fSut.parse(code);
        // THEN the altimeter is converted in HPa
        assertNotNull(m);
        assertEquals(Integer.valueOf(1017), m.getAltimeter());
    }

    @Test
    public void testParseWithRMK() throws ParseException {
        //GIVEN a metar with RMK
        String code = "CYWG 172000Z 30015G25KT 1 3/4SM R36/4000FT/D -SN BLSN BKN008 OVC040 M05/M08 Q1001 RMK SF5NS3 SLP134";
        // WHEN parsing the metar
        Metar m = fSut.parse(code);
        // THEN the remark is not null
        assertNotNull(m);
        assertNotNull(m.getVisibility());
        assertEquals("1 3/4SM", m.getVisibility().getMainVisibility());
        assertThat(m.getRemark(), containsString("SF5NS3 " + Messages.getInstance().getString("Remark.Sea.Level.Pressure", "1013.4")));
    }
}
