package com.pdy.fac.demorestback.hero.api;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.BDDAssertions;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.pdy.fac.demorestback.application.Application;
import com.pdy.fac.demorestback.hero.Hero;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class HeroAPIIntegrationStepdef {

	@Autowired
	private TestRestTemplate restTemplate;

	private ResponseEntity<Hero> responseOneEntity;

	private List<Hero> heroes;
	private Hero createdHero;
	private String stockedId;

	@When("^je crée un héro nommé '(.+)'$")
	public void je_cree_un_hero_nommé(final String nom) throws Throwable {
		final Hero request = new Hero(null, nom);
		responseOneEntity = restTemplate.postForEntity("/hero", request, Hero.class);
		createdHero = responseOneEntity.getBody();
	}

	@Given("^je modifie le nom du héro avec cet id pour '(.+)'$")
	public void modifieHeroWithStockedId(final String nom) throws Throwable {
		final Hero request = new Hero(stockedId, nom);
		restTemplate.put("/hero/"+stockedId, request);
	}

	@When("je supprime le hero avec cet id$")
	public void jeSupprimeLeHero() throws Throwable {
		restTemplate.delete("/hero/"+stockedId);
	}

	@When("je stocke son id")
	public void jeStockeSonId() {
		stockedId = createdHero.getId();
	}


	@Then("le système confirme que le héro est créé$")
	public void leSystemConfirme() throws Throwable {
		BDDAssertions.then(responseOneEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Then("je récupère le héro nommé '(.+)'$")
	public void je_récupère_le_héro_nommé(final String nom) throws Throwable {
		BDDAssertions.then(responseOneEntity.getBody().getName()).isEqualTo(nom);
	}

	@Then("^le système lui à assigné un id$")
	public void le_système_lui_à_assigné_un_id() throws Throwable {
		BDDAssertions.then(createdHero.getId()).isNotNull();
	}

	@When("^je recherche les héros dont le nom contient '(.+)'$")
	public void je_recherche_les_héros_dont_le_nom_contient(final String chaine) throws Throwable {
		final ResponseEntity<Hero[]> entity2 = restTemplate.getForEntity("/hero?name="+chaine, Hero[].class);
		heroes = Arrays.asList(entity2.getBody());
	}

	@When("^je recherche le héro avec cet id$")
	public void jeRechercheLeHeroAvecCetId() throws Throwable {
		responseOneEntity = restTemplate.getForEntity("/hero/"+stockedId, Hero.class);
	}

	@When("^je recherche tous les héros$")
	public void je_recherche_tous_les_héros() throws Throwable {
		final ResponseEntity<Hero[]> entity2 = restTemplate.getForEntity("/hero", Hero[].class);
		heroes = Arrays.asList(entity2.getBody());		
	}

	@Then("^le système réponds une liste de héros$")
	public void le_système_réponds_une_liste_de_héros() throws Throwable {
		//Assert.assertNotNull(heroes);
		Assert.fail();
	}

	@Then("^l'un d'entre eux s'appelle '(.+)'$")
	public void l_un_d_entre_eux_s_appelle(final String nom) throws Throwable {
		final boolean isPresent = heroes.stream().filter(h -> StringUtils.equals(nom, h.getName())).findAny().isPresent();
		Assert.assertTrue(isPresent);
	}

	@Then("^l'un d'entre eux à l'id stocké$")
	public void l_un_d_entre_eux_a_le_bon_id() throws Throwable {
		final boolean isPresent = heroes.stream().filter(h -> StringUtils.equals(stockedId, h.getId())).findAny().isPresent();
		Assert.assertTrue(isPresent);
	}

	@Then("^le héro n'est pas trouvé$")
	public void heroNotFound() throws Throwable {
		BDDAssertions.then(responseOneEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
