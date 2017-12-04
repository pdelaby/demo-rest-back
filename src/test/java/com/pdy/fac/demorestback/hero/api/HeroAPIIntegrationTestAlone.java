package com.pdy.fac.demorestback.hero.api;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Sers uniquement à pouvoir jouer le test seul
 * Ne sera pas joué durant la phase de test, car ne finit pas par test
 * Ne sera pas joué durant la phase de tests d'intégration, car ne finit pas par IntegrationTest
 * @author pdelaby
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
		features = "classpath:features/api/hero.feature"
		)
public class HeroAPIIntegrationTestAlone {

}
