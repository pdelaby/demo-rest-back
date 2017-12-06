package com.pdy.fac.demorestback.health.service.indicator;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.CompositeHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.util.Assert;

/**
 * Copie du CompositeHealthIndicator de spring, mais avec une éxecution en parralèlle.
 * L'avantage provient du fait que les {@link HealthIndicators} peuvent êtres long à s'éxecuter (timeout sur des URL/bases en cas de problème) 
 * et cela ralentirais considérablement l'appel    
 *
 * @author Pierre Delaby
 * @author Tyler J. Frederick
 * @author Phillip Webb
 * @author Christian Dupuis
 * @since 1.1.0
 */
public class CompositeParallelHealthIndicator implements HealthIndicator {

	private static final Logger LOGGER = LoggerFactory.getLogger(CompositeParallelHealthIndicator.class);

	private final Map<String, HealthIndicator> indicators;

	private final HealthAggregator healthAggregator;

	/**
	 * Create a new {@link CompositeHealthIndicator}.
	 * @param healthAggregator the health aggregator
	 */
	public CompositeParallelHealthIndicator(final HealthAggregator healthAggregator) {
		this(healthAggregator, new LinkedHashMap<String, HealthIndicator>());
	}

	/**
	 * Create a new {@link CompositeHealthIndicator} from the specified indicators.
	 * @param healthAggregator the health aggregator
	 * @param indicators a map of {@link HealthIndicator}s with the key being used as an
	 * indicator name.
	 */
	public CompositeParallelHealthIndicator(final HealthAggregator healthAggregator,
			final Map<String, HealthIndicator> indicators) {
		Assert.notNull(healthAggregator, "HealthAggregator must not be null");
		Assert.notNull(indicators, "Indicators must not be null");
		this.indicators = new LinkedHashMap<>(indicators);
		this.healthAggregator = healthAggregator;
	}

	public void addHealthIndicator(final String name, final HealthIndicator indicator) {
		indicators.put(name, indicator);
	}

	@Override
	public Health health() {			
		final ExecutorService executor = Executors.newFixedThreadPool(indicators.size());

		final Map<String, FutureTask<Health>> futures = new HashMap<>();

		// Crée les futures et lance l'éxecution
		for(final Entry<String, HealthIndicator> entry : indicators.entrySet()){
			final FutureTask<Health> future = new FutureTask<>(entry.getValue()::health);
			executor.execute(future);
			futures.put(entry.getKey(), future);
		}

		// Récupère les futures (l'ordre n'importe pas)
		final Map<String, Health> healths = new LinkedHashMap<>();
		for (final Entry<String, FutureTask<Health>> entry : futures.entrySet()) {
			try {
				healths.put(entry.getKey(), entry.getValue().get());
			} catch (InterruptedException | ExecutionException e) {
				LOGGER.warn("Problème durant l'execution de la futureTask du healthIndicator", e);
			}
		}

		executor.shutdown();

		return healthAggregator.aggregate(healths);
	}




}
