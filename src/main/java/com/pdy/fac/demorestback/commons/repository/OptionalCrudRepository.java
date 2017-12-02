package com.pdy.fac.demorestback.commons.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Ajoute des méthodes de recherche qui retournent des optional
 * @author PDY/Atos
 *
 * @param <T> le type de l'entité
 * @param <I> le type de l'id de l'entité
 */
@NoRepositoryBean
public interface OptionalCrudRepository<T, I extends Serializable> extends CrudRepository<T, I>{
	
	/**
	 * Trouve l'entité par son id
	 * @param id
	 * @return un Optional contenant l'entité, empty sinon
	 */
	default Optional<T> findById(final I id){
		// utilisation de cette méthode pour être en read-only (sinon le findById peut poser des problèmes)
		return Optional.ofNullable(findOne(id));
	}

}
