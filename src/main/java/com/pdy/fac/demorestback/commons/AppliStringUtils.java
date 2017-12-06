package com.pdy.fac.demorestback.commons;

import org.apache.commons.lang3.StringUtils;

public class AppliStringUtils {

	/** Pour forcer l'utilisation des méthodes statiques */
	private AppliStringUtils() {
		throw new IllegalAccessError();
	}

	/**
	 * On utilise pas d'optional ici, il faut que ça soit rapide
	 * @param text
	 * @return
	 */
	public static String escapeSpecialChars(final String text) {
		if (text != null) {
			return StringUtils.stripAccents(text).trim().replaceAll("œ", "oe");
		} else {
			return null;
		}
	}

	public static boolean containsIgnoreCaseAndSpecialChars(final String str, final String searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}

		return StringUtils.containsIgnoreCase(AppliStringUtils.escapeSpecialChars(str),
				AppliStringUtils.escapeSpecialChars(searchStr));
	}

}
