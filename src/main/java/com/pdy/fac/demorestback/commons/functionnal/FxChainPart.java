package com.pdy.fac.demorestback.commons.functionnal;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.util.Assert;

public interface FxChainPart<O> extends Supplier<O>{

	/**
	 * Applique la chaîne de fonctions sur la valeur, et retourne le résultat
	 * @return
	 */
	O resolve();

	@Override
	default O get(){
		return resolve();
	}


	/**
	 * Applique la chaîne de fonctions sur la valeur et l'utilise avec le consumerF
	 * @param consumer
	 */
	default void consume(final Consumer<O> consumer){
		Assert.notNull(consumer, "Le consumer ne peut pas être null");
		consumer.accept(resolve());
	}

}
