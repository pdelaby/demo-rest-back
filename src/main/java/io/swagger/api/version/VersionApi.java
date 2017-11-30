package io.swagger.api.version;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VersionApi  {

	@RequestMapping(value = "/version", produces = { "application/json" }, method = RequestMethod.GET)
	public ResponseEntity<Version> getVersion(){
		return ResponseEntity.ok(new Version("0.0.1")); 
	}

}
