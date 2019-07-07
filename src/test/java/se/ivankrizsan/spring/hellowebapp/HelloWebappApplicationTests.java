package se.ivankrizsan.spring.hellowebapp;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integrationtests of the Hello web application.
 *
 * @author Ivan Krizsan
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = { HelloRouter.class, HelloHandler.class },
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
public class HelloWebappApplicationTests {
    /* Constant(s): */
    protected static final String EXPECTED_GREETING = "Hello World";

    /* Instance variable(s): */
    @Autowired
    protected WebTestClient mWebTestClient;


    /**
     * Tests sending a good request to the greeting endpoint.
     * Expected result:
     * The response message should contain a greeting string.
     */
    @Test
    public void goodRequestTest() {
        final EntityExchangeResult<String> theExchangeResult = mWebTestClient
            .get()
            .uri("/hello")
            .accept(MediaType.TEXT_PLAIN)
            .exchange()
            .expectBody(String.class)
            .returnResult();

        final String theResponseBody = theExchangeResult.getResponseBody();
        Assert.assertNotNull(
            "There should be a response body",
            theResponseBody);
        Assert.assertTrue("Response message should contain a greeting",
            theResponseBody.contains(EXPECTED_GREETING));
    }

    /**
     * Tests starting the application using the main entry class.
     * Expected result:
     * The application should start without errors.
     */
    @Test
    public void startApplicationTest() {
        HelloWebappApplication.main(new String[0]);
    }
}
