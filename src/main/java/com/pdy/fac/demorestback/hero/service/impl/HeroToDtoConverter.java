package com.pdy.fac.demorestback.hero.service.impl;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.pdy.fac.demorestback.hero.Hero;
import com.pdy.fac.demorestback.hero.repository.entities.HeroEntity;

@Service
public class HeroToDtoConverter implements Function<HeroEntity,Hero> {

	@Override
	public Hero apply(final HeroEntity entity) {
		final Hero dto = new Hero();
		entity.map(HeroEntity::getNom).ifPresent(dto::setName);
		entity.map(HeroEntity::getId).ifPresent(dto::setId);
		return dto;
	}

}
