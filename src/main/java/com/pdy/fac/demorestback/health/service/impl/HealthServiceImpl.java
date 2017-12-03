package com.pdy.fac.demorestback.health.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Service;

import com.pdy.fac.demorestback.health.service.HealthService;
import com.pdy.fac.demorestback.health.service.indicator.CompositeParallelHealthIndicator;

@Service
public class HealthServiceImpl implements HealthService {


	private static final Logger LOGGER = LoggerFactory.getLogger(HealthServiceImpl.class);

	@Autowired
	private CompositeParallelHealthIndicator healthIndicator;

	private Health cachedHealth;

	private Long lastAccessNotCached;

	private static final int TTL_IN_MS = 10 * 1000;

	@Override
	public Health getHealth() {
		final long now = System.currentTimeMillis();
		if (isTimeToRenewCacheSince(now)) {
			LOGGER.debug("renouvellement du cache de santé");
			cachedHealth = healthIndicator.health();
			lastAccessNotCached = now;
		} else {
			LOGGER.debug("retour de la santé cachée");
		}
		return cachedHealth;
	}

	private boolean isTimeToRenewCacheSince(final long accessTime) {
		if (cachedHealth == null || lastAccessNotCached == null) {
			return true;
		} else {
			return accessTime - lastAccessNotCached >= TTL_IN_MS;
		}

	}


}
