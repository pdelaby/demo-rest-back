package com.pdy.fac.demorestback.commons.functionnal;

import java.util.function.Supplier;

/**
 * Utilitaire permettant de chaîner les appels de méthodes, comme pour
 * Function1.of() ou Function.andThen(), à la différence que la valeur est
 * utilisée au début au lieu d'être apply(v) à la fin.
 * 
 * @author PDY/Atos
 *
 */
public abstract class FxChain {

	private FxChain() {
		// Classe util
	}

	/**
	 * Utilise le supplier fournit pour démarrer une FxChain
	 * <br>
	 * Exemple : <code>d = FxChain.supply(truc::aGetter).then(aToB).then(BtoC).then(CtoD).resolve()</code>
	 * @param supplier
	 *            le supplier à utiliser comme paramètre d'entrée pour la
	 *            première fonction. Ne sera appelé lors de l'appel final à la
	 *            FxChain
	 * @return une {@link FxChainHead} permettant de chaîner les appels
	 */
	public static <O> FxChainHead<O> supply(final Supplier<O> supplier) {
		return new FxChainHead<>(supplier);
	}

	/**
	 * Utilise la valeur fournie pour démarrer une FxChain
	 * <br>
	 * Exemple : <code>d = FxChain.supplyVal(a).then(aToB).then(BtoC).then(CtoD).resolve()</code>
	 * @param val
	 *            la valeur à utiliser comme paramètre d'entrée pour la première
	 *            fonction
	 * @return une {@link FxChainHead} permettant de chaîner les appels
	 */
	public static <O> FxChainHead<O> supplyVal(final O val) {
		return FxChain.supply(() -> val);
	}

}
