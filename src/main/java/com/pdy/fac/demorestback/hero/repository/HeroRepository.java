package com.pdy.fac.demorestback.hero.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pdy.fac.demorestback.commons.repository.OptionalCrudRepository;
import com.pdy.fac.demorestback.hero.repository.entities.HeroEntity;

@Repository("heroRepository")
public interface HeroRepository extends OptionalCrudRepository<HeroEntity, String> {

	@Override
	List<HeroEntity> findAll();

}
