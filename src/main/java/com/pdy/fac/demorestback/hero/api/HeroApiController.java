package com.pdy.fac.demorestback.hero.api;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pdy.fac.demorestback.commons.functionnal.FxChain;
import com.pdy.fac.demorestback.hero.Hero;
import com.pdy.fac.demorestback.hero.service.HeroService;

import io.swagger.annotations.ApiParam;

@Controller
public class HeroApiController implements HeroApi {



	@Autowired
	private HeroService heroService;

	@Override
	public ResponseEntity<Hero> addHero(
			@ApiParam(value = "Le héro qu'il faut ajouter", required = true) @Valid @RequestBody final Hero body) {
		return FxChain.supplyVal(body).then(heroService::addHero).then(ResponseEntity::ok).get();
	}

	@Override
	public ResponseEntity<Void> deleteHero(
			@ApiParam(value = "Id du héro à supprimer", required = true) @PathVariable("heroId") final String heroId) {
		heroService.delete(heroId);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Hero> getHeroById(
			@ApiParam(value = "ID du héro à retourner", required = true) @PathVariable("heroId") final String heroId) {
		return FxChain.supplyVal(heroId).then(heroService::findById).then(ResponseEntity::ok).get();
	}

	@Override
	public ResponseEntity<List<Hero>> getHeroes(
			@ApiParam(value = "Le nom à recherche") @RequestParam(value = "name", required = false) final String name) {
		final List<Hero> heroes = Optional.ofNullable(name).filter(StringUtils::isNotEmpty)
				.map(heroService::findByNameContaining).orElseGet(heroService::getAll);
		return ResponseEntity.ok(heroes);
	}

	@Override
	public ResponseEntity<Hero> updateHero(
			@ApiParam(value = "ID du héro à mettre à jour", required = true) @PathVariable("heroId") final String heroId,
			@ApiParam(value = "Le héro qu'il faut mettre à jour", required = true) @Valid @RequestBody final Hero body) {
		return ResponseEntity.ok(heroService.update(heroId, body));

	}

}
