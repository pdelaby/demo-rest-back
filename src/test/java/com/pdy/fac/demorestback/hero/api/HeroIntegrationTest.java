package com.pdy.fac.demorestback.hero.api;


import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.BDDAssertions;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pdy.fac.demorestback.application.Application;
import com.pdy.fac.demorestback.hero.Hero;

@Ignore("entrainement pour les tests cucumber")
@SpringBootTest(classes = { Application.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class HeroIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void heroShoudBeCreated() throws Exception {
		final Hero request = new Hero("22", "BOB");
		final ResponseEntity<Hero> entity = testRestTemplate.postForEntity("/hero", request, Hero.class);
		//				final ResponseEntity<Version> entity = testRestTemplate.getForEntity("/version", Version.class);
		Assert.assertNotNull(entity);
		final Hero hero = entity.getBody();
		Assert.assertNotNull(hero);
		BDDAssertions.then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assert.assertEquals("BOB",hero.getName());
	}

	@Test
	public void heroShoudBeCreatedAndFindById() throws Exception {
		// GIVEN je post un héro
		final Hero request = new Hero("22", "Gerard");
		final ResponseEntity<Hero> entity = testRestTemplate.postForEntity("/hero", request, Hero.class);
		//				final ResponseEntity<Version> entity = testRestTemplate.getForEntity("/version", Version.class);
		final String id = entity.getBody().getId();

		// je le recherchepar son id2
		final ResponseEntity<Hero> entity2 = testRestTemplate.getForEntity("/hero/{id}", Hero.class, id);
		Assert.assertEquals("Gerard", entity2.getBody().getName());
	}

	@Test
	public void heroShoudBeCreatedAndFindByName() throws Exception {
		// GIVEN je post un héro
		final Hero request = new Hero("22", "SuperGirl");
		testRestTemplate.postForEntity("/hero", request, Hero.class);
		//				final ResponseEntity<Version> entity = testRestTemplate.getForEntity("/version", Version.class);

		// je le recherchepar son id2
		final ResponseEntity<Hero[]> entity2 = testRestTemplate.getForEntity("/hero", Hero[].class);
		final List<Hero> heroes = Arrays.asList(entity2.getBody());
		final boolean isPresent = heroes.stream().filter(h -> StringUtils.equals("SuperGirl", h.getName())).findAny().isPresent();
		Assert.assertTrue(isPresent);
	}


}