/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.pdy.fac.demorestback.hero.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pdy.fac.demorestback.hero.Hero;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value = "hero", description = "the hero API")
public interface HeroApi {

	@ApiOperation(value = "Ajoute un héro à la liste !", notes = "", response = Hero.class, tags = { "hero", })
	@ApiResponses(value = {
			@ApiResponse(code = 405, message = "Invalid input", response = Void.class),
			@ApiResponse(code = 200, message = "successful operation", response = Hero.class) 
	})
	@RequestMapping(value = "/hero", produces = { "application/json" }, consumes = {
	"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Hero> addHero(
			@ApiParam(value = "Le héro qu'il faut ajouter !", required = true) @Valid @RequestBody Hero body);

	@ApiOperation(value = "Supprime un héro", notes = "", response = Void.class, tags = { "hero", })
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
			@ApiResponse(code = 404, message = "Hero not found", response = Void.class) })

	@RequestMapping(value = "/hero/{heroId}", produces = { "application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Void> deleteHero(
			@ApiParam(value = "Id du héro à supprimer", required = true) @PathVariable("heroId") String heroId);

	@ApiOperation(value = "Trouve un héro par son ID et le retourne", notes = "Retourne un simple héro", response = Hero.class, tags = {
			"hero", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Hero.class),
			@ApiResponse(code = 400, message = "Invalid ID supplied", response = Void.class),
			@ApiResponse(code = 404, message = "Héro non trouvé", response = Void.class) })
	@RequestMapping(value = "/hero/{heroId}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Hero> getHeroById(
			@ApiParam(value = "ID du héro à retourner", required = true) @PathVariable("heroId") String heroId);

	@ApiOperation(value = "Récupère la liste des héros, éventuellement avec un no", notes = "", response = Hero.class, responseContainer = "List", tags = {
			"hero", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "successful operation", response = Hero.class, responseContainer = "List") })

	@RequestMapping(value = "/hero", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<List<Hero>> getHeroes(
			@ApiParam(value = "Le nom à recherche") @RequestParam(value = "name", required = false) String name);

	@ApiOperation(value = "Mets à jour un héro à la liste", notes = "", response = Void.class, tags = { "hero", })
	@ApiResponses(value = { @ApiResponse(code = 405, message = "Invalid input", response = Void.class) })

	@RequestMapping(value = "/hero/{heroId}", produces = { "application/xml" }, consumes = {
	"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<Hero> updateHero(
			@ApiParam(value = "ID du héro à mettre à jour", required = true) @PathVariable("heroId") String heroId,
			@ApiParam(value = "Le héro qu'il faut mettre à jour", required = true) @Valid @RequestBody Hero body);

}
