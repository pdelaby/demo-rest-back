package com.pdy.fac.demorestback.hero.service.impl;

import java.util.function.Predicate;

import com.pdy.fac.demorestback.hero.Hero;

public class SameHeroIdPredicate implements Predicate<Hero> {

	private final String id;

	public SameHeroIdPredicate(final String id) {
		this.id = id;
	}

	@Override
	public boolean test(final Hero hero) {
		return hero.map(Hero::getId).map(i -> i.equals(id)).orElse(false);
	}

}
