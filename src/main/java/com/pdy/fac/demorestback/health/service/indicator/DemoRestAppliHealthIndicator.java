package com.pdy.fac.demorestback.health.service.indicator;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Service;

@Service
public class DemoRestAppliHealthIndicator extends AbstractHealthIndicator implements DemoRestHealthIndicator {

	@Override
	public String getNom() {
		return "Demo rest application";
	}

	@Override
	protected void doHealthCheck(final Builder builder) throws Exception {
		builder.up();
	}

}
