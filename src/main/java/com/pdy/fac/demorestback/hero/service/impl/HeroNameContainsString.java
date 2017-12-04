package com.pdy.fac.demorestback.hero.service.impl;

import java.util.function.Predicate;

import com.pdy.fac.demorestback.commons.AppliStringUtils;
import com.pdy.fac.demorestback.hero.repository.entities.HeroEntity;

public class HeroNameContainsString implements Predicate<HeroEntity> {

	private final String partial;

	public HeroNameContainsString(final String partial) {
		this.partial = partial;
	}

	@Override
	public boolean test(final HeroEntity hero) {
		return AppliStringUtils.containsIgnoreCaseAndSpecialChars(hero.getNom(), partial);
	}

}
