package com.pdy.fac.demorestback.health.service.indicator;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.DataSourceHealthIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

@Service
public class DemoRestAppliDatabaseHealthIndicator extends ProxyHealthIndicator {

	@Autowired
	private DataSource dataSource;

	@Override
	protected HealthIndicator getHealthIndicatorConfigure() {
		return new DataSourceHealthIndicator(dataSource, "SELECT 1 FROM DUAL");
	}

	@Override
	public String getNom() {
		return "Test H2 Database";
	}

}
