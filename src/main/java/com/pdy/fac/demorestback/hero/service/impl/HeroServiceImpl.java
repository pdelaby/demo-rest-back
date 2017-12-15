package com.pdy.fac.demorestback.hero.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pdy.fac.demorestback.configuration.ElementNotFound;
import com.pdy.fac.demorestback.hero.Hero;
import com.pdy.fac.demorestback.hero.repository.HeroRepository;
import com.pdy.fac.demorestback.hero.repository.entities.HeroEntity;
import com.pdy.fac.demorestback.hero.service.HeroService;

@Service
@Transactional
public class HeroServiceImpl implements HeroService {

	@Autowired
	private HeroRepository heroRepository;

	@Autowired
	private HeroToEntityConverter heroToEntityConverter;

	@Autowired
	private HeroToDtoConverter heroToDtoConverter;


	@Override
	public Hero addHero(final Hero hero) {		
		final HeroEntity entityBeforeSave = heroToEntityConverter.apply(hero);
		final HeroEntity saved = heroRepository.save(entityBeforeSave);
		return heroToDtoConverter.apply(saved);
	}

	@Override
	public void delete(final String heroId) {
		heroRepository.delete(heroId);
	}

	@Override
	public Hero findById(final String heroId) {
		return heroRepository
				.findById(heroId)
				.map(heroToDtoConverter)
				.orElseThrow(() -> new ElementNotFound("Hero avec l'id " + heroId));
	}

	@Override
	public List<Hero> findByNameContaining(final String name) {
		return heroRepository
				.findAll()
				.stream()
				.filter(new HeroNameContainsString(name))
				.map(heroToDtoConverter)
				.collect(Collectors.toList());
	}

	@Override
	public List<Hero> getAll() {
		return heroRepository.findAll().stream().map(heroToDtoConverter).collect(Collectors.toList());
	}

	@Override
	public Hero update(final String heroId, final Hero heroToUpdate) {
		final HeroEntity entity = heroToEntityConverter.apply(heroToUpdate);
		entity.setId(heroId);		
		heroRepository.save(entity);
		return heroToDtoConverter.apply(entity);
	}

}
