package com.pdy.fac.demorestback.version.api;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
		features = "classpath:features/api/version.feature"
		)
public class VersionAPIIntegrationTest {
}
