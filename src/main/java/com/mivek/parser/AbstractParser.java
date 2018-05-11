package com.mivek.parser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mivek.enums.CloudQuantity;
import com.mivek.enums.CloudType;
import com.mivek.enums.Descriptive;
import com.mivek.enums.Intensity;
import com.mivek.enums.Phenomenon;
import com.mivek.model.AbstractWeatherCode;
import com.mivek.model.Airport;
import com.mivek.model.Cloud;
import com.mivek.model.Country;
import com.mivek.model.WeatherCondition;
import com.mivek.model.Wind;
import com.mivek.utils.Converter;
import com.mivek.utils.Regex;
import com.opencsv.CSVReader;

import i18n.Messages;


/**
 * Abstract class for parser.
 * @author mivek
 * @param <T> a concrete subclass of {@link AbstractWeatherCode}.
 */
public abstract class AbstractParser<T extends AbstractWeatherCode> {
	/**
	 * Pattern regex for wind.
	 */
	protected static final String WIND_REGEX = "(\\w{3})(\\d{2})G?(\\d{2})?(KT|MPS|KM\\/H)";
	/**
	 * Pattern regex for extreme winds.
	 */
	protected static final String WIND_EXTREME_REGEX = "^(\\d{3})V(\\d{3})";
	/**
	 * Pattern for the main visibility.
	 */
	protected static final String MAIN_VISIBILITY_REGEX = "^(\\d\\d\\d\\d)$";
	/**
	 * Pattern to recognize clouds.
	 */
	protected static final String CLOUD_REGEX = "^(\\w{3})(\\d{3})?(\\w{2,3})?$";
	/**
	 * Pattern for the vertical visibility.
	 */
	protected static final String VERTICAL_VISIBILITY = "^VV(\\d{3})$";
	/**
	 * Pattern for the minimum visibility.
	 */
	protected static final String MIN_VISIBILITY_REGEX = "^(\\d\\d\\d\\d\\w)$";
	/**
	 * From shortcut constant.
	 */
	protected static final String FM = "FM";
	/**
	 * Tempo shortcut constant.
	 */
	protected static final String TEMPO = "TEMPO";
	/**
	 * BECMG shortcut constant.
	 */
	protected static final String BECMG = "BECMG";
	/**
	 * The logger.
	 */
	private static final Logger LOGGER = Logger.getLogger(AbstractParser.class.getName());
	/**
	 * Path of airport file.
	 */
	private final InputStream fAirportsFile = AbstractParser.class.getClassLoader()
			.getResourceAsStream("data/airports.dat");
	/**
	 * Path of countries file.
	 */
	private final InputStream fCountriesFile = AbstractParser.class.getClassLoader()
			.getResourceAsStream("data/countries.dat");
	/**
	 * Map of airports.
	 */
	private Map<String, Airport> fAirports;
	/**
	 * Map of countries.
	 */
	private Map<String, Country> fCountries;

	/**
	 * Constructor.
	 */
	protected AbstractParser() {
		initCountries();
		initAirports();
	}

	/**
	 * Initiate airports map.
	 */
	private void initAirports() {
		fAirports = new HashMap<>();
		CSVReader reader;
		try {
			reader = new CSVReader(new InputStreamReader(fAirportsFile));
			String[] line;
			while ((line = reader.readNext()) != null) {
				Airport airport = new Airport();
				airport.setName(line[1]);
				airport.setCity(line[2]);
				airport.setCountry(fCountries.get(line[3]));
				airport.setIata(line[4]);
				airport.setIcao(line[5]);
				airport.setLatitude(Double.parseDouble(line[6]));
				airport.setLongitude(Double.parseDouble(line[7]));
				airport.setAltitude(Integer.parseInt(line[8]));
				airport.setTimezone(line[9]);
				airport.setDst(line[10]);
				fAirports.put(airport.getIcao(), airport);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}

	/**
	 * Initiate countries map.
	 */
	private void initCountries() {
		fCountries = new HashMap<>();
		CSVReader reader;
		try {
			reader = new CSVReader(new InputStreamReader(fCountriesFile));
			String[] line;
			while ((line = reader.readNext()) != null) {
				Country country = new Country();
				country.setName(line[0]);
				fCountries.put(country.getName(), country);
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		}
	}

	/**
	 * This method parses the wind part of the metar code. It parses the generic
	 * part.
	 * @param pStringWind a string with wind elements.
	 * @return a Wind element with the informations.
	 */
	protected Wind parseWind(final String pStringWind) {
		Wind wind = new Wind();
		String[] windPart = Regex.pregMatch(WIND_REGEX, pStringWind);
		String directionPart = windPart[1];
		String direction = Converter.degreesToDirection(directionPart);
		wind.setDirection(direction);
		if (!direction.equals(Messages.CONVERTER_VRB)) {
			wind.setDirectionDegrees(Integer.parseInt(windPart[1]));
		}
		wind.setSpeed(Integer.parseInt(windPart[2]));
		if (windPart[3] != null) {
			wind.setGust(Integer.parseInt(windPart[3]));
		}
		wind.setUnit(windPart[4]);
		return wind;
	}

	/**
	 * This method parses the cloud part of the metar.
	 *
	 * @param pCloudString string with cloud elements.
	 * @return a decoded cloud with its quantity, its altitude and its type.
	 */
	protected Cloud parseCloud(final String pCloudString) {
		Cloud cloud = new Cloud();
		String[] cloudPart = Regex.pregMatch(CLOUD_REGEX, pCloudString);
		try {
			CloudQuantity cq = CloudQuantity.valueOf(cloudPart[1]);
			cloud.setQuantity(cq);
			if (cloudPart[2] != null) {
				cloud.setHeight(100 * Integer.parseInt(cloudPart[2]));
				if (cloudPart[3] != null) {
					CloudType ct = CloudType.valueOf(cloudPart[3]);
					cloud.setType(ct);
				}
			}
			return cloud;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	/**
	 * This method parses the weather conditions of the metar.
	 *
	 * @param weatherPart
	 *            String representing the weather.
	 * @return a weather condition with its intensity, its descriptor and the
	 *         phenomenon.
	 */
	protected WeatherCondition parseWeatherCondition(final String weatherPart) {
		WeatherCondition wc = new WeatherCondition();
		String match = null;
		if (Regex.find("^(-|\\+|VC)", weatherPart)) {
			match = Regex.findString("^(-|\\+|VC)", weatherPart);
			Intensity i = Intensity.getEnum(match);
			wc.setIntensity(i);
		}
		for (Descriptive des : Descriptive.values()) {
			if (Regex.findString("(" + des.getShortcut() + ")", weatherPart) != null) {
				wc.setDescriptive(des);
			}
		}
		for (Phenomenon phe : Phenomenon.values()) {
			if (Regex.findString("(" + phe.getShortcut() + ")", weatherPart) != null) {
				wc.addPhenomenon(phe);
			}
		}
		if (wc.isValid()) {
			return wc;
		}
		return null;
	}

	/**
	 * @return the airports
	 */
	public Map<String, Airport> getAirports() {
		return fAirports;
	}

	/**
	 * @return the countries
	 */
	public Map<String, Country> getCountries() {
		return fCountries;
	}

	/**
	 * Parse method.
	 * @param pCode the message to parse.
	 * @return the decoded object.
	 */
	public abstract T parse(String pCode);

}
