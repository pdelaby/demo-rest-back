package com.pdy.fac.demorestback.health.service.indicator;

import org.springframework.boot.actuate.health.HealthIndicator;

public interface DemoRestHealthIndicator extends HealthIndicator {

	public String getNom();
}
