package com.pdy.fac.demorestback.hero.service.impl;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.pdy.fac.demorestback.hero.Hero;
import com.pdy.fac.demorestback.hero.repository.entities.HeroEntity;

@Service
public class HeroToEntityConverter implements Function<Hero, HeroEntity> {

	@Override
	public HeroEntity apply(final Hero dto) {
		final HeroEntity entity = new HeroEntity();
		dto.map(Hero::getName).ifPresent(entity::setNom);
		dto.map(Hero::getId).map(String::valueOf).ifPresent(entity::setId);
		return entity;
	}

}
