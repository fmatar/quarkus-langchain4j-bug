package com.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import dev.ai4j.openai4j.chat.ChatCompletionRequest;
import dev.langchain4j.internal.Json;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SerializationTest {
    @Test
    void testHelloEndpoint() {
       var request = ChatCompletionRequest.builder().model("gpt-3.5-turbo").build();

       assertThat(request, notNullValue());

       var json = Json.toJson(request);
       assertThat(json, notNullValue());

       var deserialized = Json.fromJson(json, ChatCompletionRequest.class);

       assertThat(deserialized, equalToObject(request));
    }

}