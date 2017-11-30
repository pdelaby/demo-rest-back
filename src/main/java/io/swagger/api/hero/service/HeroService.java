package io.swagger.api.hero.service;

import java.util.List;

import io.swagger.model.Hero;

public interface HeroService {

	Hero addHero(Hero body);

	void delete(Long heroId);

	Hero findById(Long heroId);

	List<Hero> findByNameContaining(String name);

	List<Hero> getAll();

	Hero update(Long heroId, Hero hero);

}
