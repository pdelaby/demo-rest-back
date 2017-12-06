package com.pdy.fac.demorestback.health.service.indicator;

import javax.annotation.PostConstruct;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

/**
 * Classe permettant d'utiliser un healthIndicator de spring en le configurant au démarrage
 * @author Delaby Pierre
 *
 */
public abstract class ProxyHealthIndicator implements DemoRestHealthIndicator {

	private HealthIndicator healtIndicator;

	@PostConstruct
	public void init(){
		healtIndicator = getHealthIndicatorConfigure();
	}

	protected abstract HealthIndicator getHealthIndicatorConfigure();

	@Override
	public Health health() {
		return healtIndicator.health();
	}

}
