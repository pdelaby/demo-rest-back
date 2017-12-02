package com.pdy.fac.demorestback.version.api;

import org.assertj.core.api.BDDAssertions;
import org.junit.Assert;
import org.junit.Test;
/**
 * Basic integration tests for service demo application.
 *
 * @author Dave Syer
 */
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pdy.fac.demorestback.IntegrationTest;
import com.pdy.fac.demorestback.application.Application;
import com.pdy.fac.demorestback.version.Version;

@Category(IntegrationTest.class)
@SpringBootTest(classes = { Application.class,
		Application.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class VersionIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturn200WhenSendingRequestToController() throws Exception {
		final ResponseEntity<Version> entity = testRestTemplate.getForEntity("/version", Version.class);
		Assert.assertNotNull(entity);
		final Version version = entity.getBody();
		Assert.assertNotNull(version);
		Assert.assertNotNull(version.getNumero());
		BDDAssertions.then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}