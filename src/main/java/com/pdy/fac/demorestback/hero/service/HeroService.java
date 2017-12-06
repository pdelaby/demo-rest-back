package com.pdy.fac.demorestback.hero.service;

import java.util.List;

import com.pdy.fac.demorestback.hero.Hero;

public interface HeroService {

	Hero addHero(Hero body);

	void delete(String heroId);

	Hero findById(String heroId);

	List<Hero> findByNameContaining(String name);

	List<Hero> getAll();

	Hero update(String heroId, Hero hero);

}
