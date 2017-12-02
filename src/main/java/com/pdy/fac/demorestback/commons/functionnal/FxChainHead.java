package com.pdy.fac.demorestback.commons.functionnal;

import java.util.function.Function;
import java.util.function.Supplier;

public class FxChainHead<O> implements FxChainPart<O>{

	private final Supplier<O> supplier;

	public FxChainHead(final Supplier<O> supplier) {
		this.supplier = supplier;
	}

	@Override
	public O resolve() {
		return supplier.get();
	}

	/**
	 * Ajoute une fonction à la chaîne de fonctions
	 * @param nextFx
	 * @return
	 */
	public <Z> FxElem<O, Z> then(final Function<O, Z> nextFx) {		
		return new FxElem<>(nextFx, supplier);
	}

}
