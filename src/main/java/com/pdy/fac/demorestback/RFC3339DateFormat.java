package com.pdy.fac.demorestback;

import java.text.FieldPosition;
import java.util.Date;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

public class RFC3339DateFormat extends ISO8601DateFormat {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6549934226285553992L;

	@Override
	public StringBuffer format(final Date date, final StringBuffer toAppendTo, final FieldPosition fieldPosition) {
		final String value = ISO8601Utils.format(date, true);
		toAppendTo.append(value);
		return toAppendTo;
	}

}