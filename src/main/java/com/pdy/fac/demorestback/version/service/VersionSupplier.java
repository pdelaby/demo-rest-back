package com.pdy.fac.demorestback.version.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pdy.fac.demorestback.version.Version;

/**
 * Lit la version depuis le pom en utilisant mvn filters : 
 * un placeholder est placé dans applications.properties
 * maven resource	
 * @author pdelaby
 *
 */
@Service
public class VersionSupplier implements Supplier<Version> {


	@Value("${info.build.version}")
	private String buildVersion;

	@Override
	public Version get() {
		return new Version(buildVersion);
	}

}
