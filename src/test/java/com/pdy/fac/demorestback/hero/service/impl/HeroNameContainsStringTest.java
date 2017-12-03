package com.pdy.fac.demorestback.hero.service.impl;

import org.junit.Assert;
import org.junit.Test;

import com.pdy.fac.demorestback.hero.repository.entities.HeroEntity;

public class HeroNameContainsStringTest {

	private HeroNameContainsString subject;

	@Test
	public void shoudBeOkWhenSameName() {
		// Given
		subject = new HeroNameContainsString("test");
		final HeroEntity hero = new HeroEntity("34l", "test");

		// Then
		Assert.assertTrue(subject.test(hero));
	}

	@Test
	public void shoudBeOkWhenContainsPartial() {
		// Given
		subject = new HeroNameContainsString("ar");
		final HeroEntity hero = new HeroEntity("34l", "Marcel");

		// Then
		Assert.assertTrue(subject.test(hero));
	}

	@Test
	public void shoudBeOkWhenContainsPartialWithNotSameCase() {
		// Given
		subject = new HeroNameContainsString("Ar");
		final HeroEntity hero = new HeroEntity("34l", "MaRcel");

		// Then
		Assert.assertTrue(subject.test(hero));
	}


	@Test
	public void shoudBeKoWhenHeroNameIsNull() {
		// Given
		subject = new HeroNameContainsString("Ar");
		final HeroEntity hero = new HeroEntity("34l", null);

		// Then
		Assert.assertFalse(subject.test(hero));
	}



}
