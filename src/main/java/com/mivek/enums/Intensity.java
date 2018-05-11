package com.mivek.enums;

import i18n.Messages;

/**
 * Enumeration for indicator.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author mivek
 *
 */
public enum Intensity {
	/**
	 * Light intensity.
	 */
	LIGHT("-", Messages.INTENSITY_LIGHT), //$NON-NLS-1$
	/**
	 * Heavy intensity.
	 */
	HEAVY("+", Messages.INTENSITY_HEAVY), //$NON-NLS-1$
	/**
	 * In vicinity.
	 */
	IN_VICINITY("VC", Messages.INTENSITY_VC); //$NON-NLS-1$

	/**
	 * The shortcut of the intensity.
	 */
	private String fShortcut = ""; //$NON-NLS-1$
	/**
	 * The meaning of the intensity.
	 */
	private String fName = ""; //$NON-NLS-1$

	/**
	 * Constructor.
	 *
	 * @param pShortcut
	 *            A String for the shortcut.
	 * @param pName
	 *            A string for the meaning.
	 */
	Intensity(final String pShortcut, final String pName) {
		fShortcut = pShortcut;
		fName = pName;
	}

	@Override
	public String toString() {
		return fName;
	}

	/**
	 * Returns shortcut.
	 *
	 * @return string.
	 */
	public String getShortcut() {
		return fShortcut;
	}

	/**
	 * Returns the enum with the same shortcut than the value.
	 *
	 * @param value
	 *            String of the intensity searched.
	 * @return a intensity with the same shortcut.
	 * @throws IllegalArgumentException
	 *             error if not found.
	 */
	public static Intensity getEnum(final String value) throws IllegalArgumentException {
		for (Intensity v : values()) {
			if (v.getShortcut().equalsIgnoreCase(value)) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}
}
