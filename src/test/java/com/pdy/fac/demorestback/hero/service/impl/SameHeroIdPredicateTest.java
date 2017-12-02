package com.pdy.fac.demorestback.hero.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.pdy.fac.demorestback.hero.Hero;

class SameHeroIdPredicateTest {

	private SameHeroIdPredicate subject;

	@Test
	@DisplayName("Shoud be ok when same long")
	void shoudBeOkWhenTwoLongs() {
		// Given
		subject = new SameHeroIdPredicate("34");
		final Hero hero = new Hero("34", "nom");

		// Then
		Assert.assertTrue(subject.test(hero));
	}

	@Test
	@DisplayName("Shoud be ko when hero have no id")
	void shoudKoWhenHeroHaveNoId() {
		// Given
		subject = new SameHeroIdPredicate("34");
		final Hero hero = new Hero(null, "nom");

		// Then
		Assert.assertFalse(subject.test(hero));
	}

	@Test
	@DisplayName("Shoud be ko when not the same id")
	void shoudKoWhenHeroHaveDifferentId() {
		// Given
		subject = new SameHeroIdPredicate("34l");
		final Hero hero = new Hero("1032l", "nom");

		// Then
		Assert.assertFalse(subject.test(hero));
	}

}
