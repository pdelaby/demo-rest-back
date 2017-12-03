package com.pdy.fac.demorestback.version.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pdy.fac.demorestback.version.Version;

@Controller
public class VersionApi  {

	@RequestMapping(value = "/version", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Version> getVersion(){
		return ResponseEntity.ok(new Version("0.0.1")); 
	}

}
