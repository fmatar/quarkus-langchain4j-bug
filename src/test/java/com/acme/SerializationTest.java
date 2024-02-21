package com.acme;

import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import dev.ai4j.openai4j.chat.ChatCompletionRequest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
class SerializationTest {

  @Test
  void testSerializationWithLangchain4jSerialization() {
    var request = ChatCompletionRequest.builder().model("gpt-3.5-turbo").build();
    assertThat(request, notNullValue());
    var json = dev.langchain4j.internal.Json.toJson(request);
    assertThat(json, notNullValue());
    var deserialized = dev.langchain4j.internal.Json.fromJson(json, ChatCompletionRequest.class);
    assertThat(deserialized, equalToObject(request));
  }

  @Test
  void testSerializationWithQuarkusSerialization() {
    var request = ChatCompletionRequest.builder().model("gpt-3.5-turbo").build();
    assertThat(request, notNullValue());
    var json = io.vertx.core.json.Json.encode(request);
    assertThat(json, notNullValue());
    var deserialized = io.vertx.core.json.Json.decodeValue(json, ChatCompletionRequest.class);
    assertThat(deserialized, equalToObject(request));
  }
}