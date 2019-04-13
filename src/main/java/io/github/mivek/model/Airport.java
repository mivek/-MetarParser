package io.github.mivek.model;

/**
 * Represents an airport.
 * @author mivek
 */
public class Airport {
    /** Name of the airport. */
    private String name;
    /** Name of the city. */
    private String city;
    /** Country of the airport. */
    private Country country;
    /** Iata code of the airport. */
    private String iata;
    /** Icao code of the airport. */
    private String icao;
    /** Latitude of the airport. */
    private double latitude;
    /** Longitude of the airport. */
    private double longitude;
    /** Altitude of the airport. */
    private int altitude;
    /** Timezone of the airport. */
    private String timezone;
    /** DST of the airport. */
    private String dst;
    /** tzdatabase of the aiport. */
    private String tzDatabase;

    /**
     * Getter of name.
     * @return string name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name.
     * @param pName name of the airport.
     */
    public void setName(final String pName) {
        name = pName;
    }

    /**
     * Getter of city.
     * @return string of city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter of the city.
     * @param pCity string of the name of the city.
     */
    public void setCity(final String pCity) {
        city = pCity;
    }

    /**
     * Getter of country.
     * @return a country object.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Setter of country.
     * @param pCountry The country to set.
     */
    public void setCountry(final Country pCountry) {
        country = pCountry;
    }

    /**
     * Getter of iata.
     * @return string of iata.
     */
    public String getIata() {
        return iata;
    }

    /**
     * Setter of iata code.
     * @param pIata string of iata.
     */
    public void setIata(final String pIata) {
        iata = pIata;
    }

    /**
     * Getter of Icao code.
     * @return string icao code.
     */
    public String getIcao() {
        return icao;
    }

    /**
     * Setter of icao.
     * @param pIcao string of icao.
     */
    public void setIcao(final String pIcao) {
        icao = pIcao;
    }

    /**
     * Getter of latitude.
     * @return latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Setter of latitude.
     *
     * @param pLatitude Latitude to set.
     */
    public void setLatitude(final double pLatitude) {
        latitude = pLatitude;
    }

    /**
     * Getter of longitude.
     * @return longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter of longitude.
     *
     * @param pLongitude to set.
     */
    public void setLongitude(final double pLongitude) {
        longitude = pLongitude;
    }

    /**
     * Getter of altitude.
     * @return altitude.
     */
    public int getAltitude() {
        return altitude;
    }

    /**
     * Setter of altitude.
     *
     * @param pAltitude the altitude to set.
     */
    public void setAltitude(final int pAltitude) {
        altitude = pAltitude;
    }

    /**
     * Getter of timezone.
     * @return string of timezone.
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Setter of timezone.
     * @param pTimezone timezone string to set.
     */
    public void setTimezone(final String pTimezone) {
        timezone = pTimezone;
    }

    /**
     * Getter of DST.
     * @return string of dst.
     */
    public String getDst() {
        return dst;
    }

    /**
     * Setter of DST.
     * @param pDst the dst to set.
     */
    public void setDst(final String pDst) {
        dst = pDst;
    }

    /**
     * Getter of tzDatabase.
     * @return string of tzDatabase.
     */
    public String getTzDatabase() {
        return tzDatabase;
    }

    /**
     * Setter of tzDatabase.
     * @param pTzDatabase The tzDatabase to set.
     */
    public void setTzDatabase(final String pTzDatabase) {
        tzDatabase = pTzDatabase;
    }

    @Override
    public final boolean equals(final Object pObj) {
        if (pObj instanceof Airport) {
            return icao.equals(((Airport) pObj).getIcao());
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int result;
        result = 31 * icao.hashCode();
        return result;
    }

    @Override
    public final String toString() {
        return name + " (" + icao + ")";
    }
}
