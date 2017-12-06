package com.pdy.fac.demorestback.version.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pdy.fac.demorestback.version.Version;
import com.pdy.fac.demorestback.version.service.VersionSupplier;

@Controller
public class VersionApi  {

	@Autowired
	private VersionSupplier versionSupplier;

	@RequestMapping(value = "/version", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Version> getVersion(){
		return ResponseEntity.ok(versionSupplier.get()); 
	}

}
