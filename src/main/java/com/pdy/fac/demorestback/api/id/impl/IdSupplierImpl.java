package com.pdy.fac.demorestback.api.id.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.pdy.fac.demorestback.api.id.IdSupplier;

@Service
public class IdSupplierImpl implements IdSupplier {

	@Override
	public Long get() {
		return Integer.toUnsignedLong(Math.abs(new Random().nextInt()));
	}

}
