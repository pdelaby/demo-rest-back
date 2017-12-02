package com.pdy.fac.demorestback.commons.functionnal;

import java.util.function.Function;
import java.util.function.Supplier;

public class FxElem<I, O> implements FxChainPart<O> {

	private final Function<I, O> fx;
	private Supplier<I> valueGetter;

	public FxElem(final Function<I, O> fx, final Supplier<I> valueGetter) {
		this.fx = fx;
		this.valueGetter = valueGetter;
	}

	@Override
	public O resolve() {
		return fx.apply(valueGetter.get());
	}

	/**
	 * Ajoute une fonction à la chaîne de fonctions
	 * 
	 * @param nextFx
	 * @return
	 */
	public <Z> FxElem<I, Z> then(final Function<O, Z> nextFx) {
		return new FxElem<>(fx.andThen(nextFx), valueGetter);
	}

}
