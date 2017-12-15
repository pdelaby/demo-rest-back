package com.pdy.fac.demorestback.hero.service.impl;

import org.junit.Assert;
import org.junit.Test;

import com.pdy.fac.demorestback.hero.Hero;

public class SameHeroIdPredicateTest {

	private SameHeroIdPredicate subject;

	@Test
	public void shoudBeOkWhenTwoLongs() {
		// Given
		subject = new SameHeroIdPredicate("34");
		final Hero hero = new Hero("34", "nom");

		// Then
		Assert.assertTrue(subject.test(hero));
	}

	@Test
	public void shoudKoWhenHeroHaveNoId() {
		// Given
		subject = new SameHeroIdPredicate("34");
		final Hero hero = new Hero(null, "nom");

		// Then
		Assert.assertFalse(subject.test(hero));
	}

	@Test
	public void shoudKoWhenHeroHaveDifferentId() {
		// Given
		subject = new SameHeroIdPredicate("34l");
		final Hero hero = new Hero("1032l", "nom");

		// Then
		Assert.assertFalse(subject.test(hero));
	}

}
