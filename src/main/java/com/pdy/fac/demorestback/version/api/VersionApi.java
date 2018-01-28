package com.pdy.fac.demorestback.version.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pdy.fac.demorestback.version.Version;
import com.pdy.fac.demorestback.version.service.VersionSupplier;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api
@RequestMapping(value = "/version")
public class VersionApi  {

	@Autowired
	private VersionSupplier versionSupplier;



	@ApiOperation(value = "Retourne la vesrion", notes = "la version du système", response = Version.class, tags = {
			"system", })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successful operation", response = Version.class)})
	@RequestMapping(produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Version> getVersion(){
		return ResponseEntity.ok(versionSupplier.get()); 
	}

}
