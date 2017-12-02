package com.pdy.fac.demorestback.hero.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.pdy.fac.demorestback.hero.repository.entities.HeroEntity;

class HeroNameContainsStringTest {

	private HeroNameContainsString subject;

	@Test
	@DisplayName("Shoud be ok when same name")
	void shoudBeOkWhenSameName() {
		// Given
		subject = new HeroNameContainsString("test");
		final HeroEntity hero = new HeroEntity("34l", "test");

		// Then
		Assert.assertTrue(subject.test(hero));
	}

	@Test
	@DisplayName("Shoud be ok when names contains only partial")
	void shoudBeOkWhenContainsPartial() {
		// Given
		subject = new HeroNameContainsString("ar");
		final HeroEntity hero = new HeroEntity("34l", "Marcel");

		// Then
		Assert.assertTrue(subject.test(hero));
	}

	@Test
	@DisplayName("Shoud be ok when names contains only partial with not same case")
	void shoudBeOkWhenContainsPartialWithNotSameCase() {
		// Given
		subject = new HeroNameContainsString("Ar");
		final HeroEntity hero = new HeroEntity("34l", "MaRcel");

		// Then
		Assert.assertTrue(subject.test(hero));
	}


	@Test
	@DisplayName("Shoud be ko if hero name is null")
	void shoudBeKoWhenHeroNameIsNull() {
		// Given
		subject = new HeroNameContainsString("Ar");
		final HeroEntity hero = new HeroEntity("34l", null);

		// Then
		Assert.assertFalse(subject.test(hero));
	}



}
