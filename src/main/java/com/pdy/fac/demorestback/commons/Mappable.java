package com.pdy.fac.demorestback.commons;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Ajoute la fonctionnalité {@code .map} à l'objet, permettant de faire une programmation fonctionnelle, en enlevant l'appel à Optional.ofNullable pour chainer les opérations de programmation fonctionnelle
 * <ul>
 * <li>Permet : {@code objet.map(Object::getMembre).map(Membre::getNom)}</li>
 * <li>Au lieu de : {@code Optional.ofNullable(objet.getMembre()).map(Membre::getNom)}</li>
 * </ul>
 * @author PDY/Atos
 *
 */
public interface Mappable {

	/**
	 * Fonctionne comme Map sur un stream ou un Optional : si une valeur rest
	 * présente, applique la fonction de mapping fournie
	 * <ul>
	 * <li>Si la résultat est non-null, retourne un {@link Optional} décrivant
	 * le résultat</li>
	 * <li>Sinon, retourne un {@link Optional} vide</li>
	 * </ul>
	 * 
	 * @param <U>
	 *            Le super type du résultat de la fonction de mapping
	 * @param mapper
	 *            une fonctione de mapping a appliquer sur la valeur l'objet
	 *            courant
	 * @return un {@link Optional} describing the result of applying a mapping
	 *         function to the value of this {@code Optional}, if a value is
	 *         present, otherwise an empty {@code Optional}
	 * @throws NullPointerException
	 *             si la fonction de mapping est nulle
	 */
	@SuppressWarnings("unchecked")
	public default <U, T extends Mappable> Optional<U> map(final Function<T, ? extends U> mapper) {
		Objects.requireNonNull(mapper);

		return Optional.ofNullable(mapper.apply((T) this));
	}

}
