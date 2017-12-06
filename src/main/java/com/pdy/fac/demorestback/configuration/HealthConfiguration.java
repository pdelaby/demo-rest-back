package com.pdy.fac.demorestback.configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.OrderedHealthAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pdy.fac.demorestback.health.service.indicator.CompositeParallelHealthIndicator;
import com.pdy.fac.demorestback.health.service.indicator.DemoRestHealthIndicator;

/***
 * Configuration du HealthIndicator, qui sera utilisé dans le service
 * HealtService
 * 
 * @author ATOS/MND
 *
 */
@Configuration
@ComponentScan(basePackages = {
		"com.pdy.fac.demorestback.health.service.indicator"
})
public class HealthConfiguration {

	/**
	 * Les indicateurs sont déclarés comme services. Pour en rajouter 1, il
	 * suffit de créer la bonne classe et de l'annoter service pour qu'elle
	 * puisse être détectée par spring
	 */
	@Autowired
	private List<DemoRestHealthIndicator> indicators;

	/**
	 * Crée le compositeHealthIndicator provenant d'une copie de celui Spring Acurator : un indicateur qui en aggrège d'autres. 
	 * Celui-ci est composé de tous les indicateurs déclarés pour Osmose
	 * @return
	 */
	@Bean
	public CompositeParallelHealthIndicator compositeParallelHealthIndicator() {

		// A Ajouter :
		// - Export perso : nb en cache, nb en cours ?
		final Map<String, HealthIndicator> map = indicators.stream().collect(Collectors.toMap(DemoRestHealthIndicator::getNom, i -> i));

		return new CompositeParallelHealthIndicator(new OrderedHealthAggregator(), map);
	}

}
