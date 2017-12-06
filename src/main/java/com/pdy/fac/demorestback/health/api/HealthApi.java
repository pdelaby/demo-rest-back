package com.pdy.fac.demorestback.health.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pdy.fac.demorestback.health.service.HealthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@RestController
@Api()
// Le endpoint Health de spring par défaut est désactivé, depuis application.yaml, le mien est là 
@RequestMapping(value = "/health", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HealthApi {

	@Autowired
	private HealthService healthService;

	@ApiOperation(value = "Retourne un descriptif de la santé de l'application", response=Health.class)
	@ApiResponse(code = 200, message = "successful operation", response = Health.class) 
	@RequestMapping(value = "", method = RequestMethod.GET, produces = { "application/json" } )
	public Health getHealth() {
		return healthService.getHealth();
	}
}
