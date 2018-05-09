package com.mivek.model.trend;

import java.util.ArrayList;
import java.util.List;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.Metar;

/**
 * @author mivek
 *
 */
public abstract class AbstractMetarTrend extends AbstractTrend<Metar> {

	/**
	 * List containing the times properties of the trend.
	 */
	private List<AbstractMetarTrendTime> fTimes;
	/**
	 * Constructor.
	 * @param pType the WeatherChangeType to set.
	 */
	protected AbstractMetarTrend(final WeatherChangeType pType) {
		super(pType);
		fTimes = new ArrayList<>();
	}

	/**
	 * @return the times
	 */
	public List<AbstractMetarTrendTime> getTimes() {
		return fTimes;
	}

	/**
	 * Adds a AbstractMetarTrendTime to the list.
	 * @param pTime the element to add.
	 */
	public void addTime(final AbstractMetarTrendTime pTime) {
		fTimes.add(pTime);
	}

}
