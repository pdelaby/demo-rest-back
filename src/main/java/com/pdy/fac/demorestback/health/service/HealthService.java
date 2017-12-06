package com.pdy.fac.demorestback.health.service;

import org.springframework.boot.actuate.health.Health;

public interface HealthService {

	Health getHealth();

}
