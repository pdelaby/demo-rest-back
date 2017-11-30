package io.swagger.api.hero.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.api.hero.service.HeroService;
import io.swagger.api.id.IdSupplier;
import io.swagger.configuration.ElementNotFound;
import io.swagger.model.Hero;

@Service
public class HeroServiceImpl implements HeroService {

	@Autowired
	private IdSupplier idSupplier;

	final List<Hero> heroes = new ArrayList<>();

	public HeroServiceImpl() {
		final Map<Long, String> attrs = new HashMap<>();
		attrs.put(11l, "Mr. Nice");
		attrs.put(12l, "Narco");
		attrs.put(13l, "Bombasto");
		attrs.put(14l, "Celeritas");
		attrs.put(15l, "Magneta");
		attrs.put(16l, "RubberMan");
		attrs.put(17l, "Dynama");
		attrs.put(18l, "Dr IQ");
		attrs.put(19l, "Magma");
		attrs.put(20l, "Tornado");
		attrs.entrySet().stream().map(e -> new Hero(e.getKey(), e.getValue())).forEach(heroes::add);
	}

	@Override
	public Hero addHero(final Hero hero) {
		hero.setId(idSupplier.get());
		heroes.add(hero);
		return hero;
	}

	@Override
	public void delete(final Long heroId) {
		final Hero hero = findById(heroId);
		heroes.remove(hero);
	}

	@Override
	public Hero findById(final Long heroId) {
		return heroes.stream().filter(e -> e.getId().compareTo(heroId) == 0).findAny()
				.orElseThrow(() -> new ElementNotFound("Hero avec l'id " + heroId));
	}

	@Override
	public List<Hero> findByNameContaining(final String name) {
		return heroes.stream().filter(h -> StringUtils.containsIgnoreCase(h.getName(), name))
				.collect(Collectors.toList());
	}

	@Override
	public List<Hero> getAll() {
		return heroes;
	}

	@Override
	public Hero update(final Long heroId, final Hero heroToUpdate) {
		heroToUpdate.setId(heroId);		
		delete(heroId);
		heroes.add(heroToUpdate);
		return heroToUpdate; 
	}

}
