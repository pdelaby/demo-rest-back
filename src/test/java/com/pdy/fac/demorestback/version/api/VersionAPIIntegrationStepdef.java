package com.pdy.fac.demorestback.version.api;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.pdy.fac.demorestback.application.Application;
import com.pdy.fac.demorestback.version.Version;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class VersionAPIIntegrationStepdef {

	@Autowired
	private TestRestTemplate restTemplate;

	private ResponseEntity<Version> response;


	@When("^I GET (.+)$")
	public void i_GET(final String path) throws Throwable {
		response = restTemplate.getForEntity(path, Version.class);
	}

	@Then("^response code should be (\\d+)$")
	public void response_code_should_be(final int status) throws Throwable {
		Assert.assertEquals(status, response.getStatusCode().value());
	}

	@Then("^response code should not be (\\d+)$")
	public void response_code_should_not_be(final int status) throws Throwable {
		Assert.assertNotEquals(status, response.getStatusCode().value());
	}

	@Then("^the body shoud not be null$")
	public void the_body_shoud_not_be_null() throws Throwable {
		Assert.assertNotNull(response.getBody());
	}

}
