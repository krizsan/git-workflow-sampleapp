package se.ivankrizsan.spring.hellowebapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Tests for the {@code HelloHandler} class.
 *
 * @author Ivan Krizsan
 */
public class HelloHandlerTests {
    /* Constant(s): */

    /* Instance variable(s): */
    protected HelloHandler mHandlerUnderTest;


    /**
     * Sets up before tests.
     */
    @Before
    public void setup() {
        mHandlerUnderTest = new HelloHandler();
    }

    /**
     * Tests sending of a good request to the greeting handler.
     * Expected result:
     * The response should be successfully handled and the response
     * body should contain a greeting.
     */
    @Test
    public void successfulGetGreetingTest() {
        final ServerRequest theRequest = MockServerRequest
            .builder()
            .method(HttpMethod.GET)
            .build();

        final Mono<ServerResponse> theResponseMono =
            mHandlerUnderTest.hello(theRequest);
        final ServerResponse theResponse = theResponseMono.block();

        Assert.assertNotNull("There should be a response", theResponse);

        final HttpStatus theResponseStatus = theResponse.statusCode();
        final MediaType theResponseType = theResponse.headers().getContentType();

        Assert.assertNotNull(
            "There should be a HTTP response status",
            theResponseStatus);
        Assert.assertEquals(
            "The response should be successfully handled",
            HttpStatus.OK,
            theResponseStatus);
        Assert.assertEquals(
            "The content type should be text/plain",
            theResponseType,
            MediaType.TEXT_PLAIN);
    }
}
