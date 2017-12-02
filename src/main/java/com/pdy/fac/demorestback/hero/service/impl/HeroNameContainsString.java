package com.pdy.fac.demorestback.hero.service.impl;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

import com.pdy.fac.demorestback.hero.repository.entities.HeroEntity;

public class HeroNameContainsString implements Predicate<HeroEntity> {

	private final String partial;

	public HeroNameContainsString(final String partial) {
		this.partial = partial;
	}

	@Override
	public boolean test(final HeroEntity hero) {
		return StringUtils.containsIgnoreCase(hero.getNom(), partial);
	}

}
