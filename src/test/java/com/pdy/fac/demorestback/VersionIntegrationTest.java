package com.pdy.fac.demorestback;

/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import com.pdy.fac.demorestback.api.version.Version;
import com.pdy.fac.demorestback.application.Application;
import com.pdy.fac.demorestback.application.WebInitializer;

@Category(IntegrationTest.class)
@SpringBootTest(classes = { WebInitializer.class,
		Application.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class VersionIntegrationTest {
	// @LocalServerPort
	// private int port;

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