package com.daramg.pickup_service.presentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.RestAssuredRestDocumentationWrapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.test.context.support.WithMockUser;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

@DisplayName("SampleController 테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class SampleControllerTest {

    protected RequestSpecification spec;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    @WithMockUser
    @DisplayName("/sample GET 요청 시 '람쥐~' 반환")
    void getSampleMessage() {
        given(spec)
                .filter(RestAssuredRestDocumentationWrapper.document("sample-get",
                        ResourceSnippetParameters.builder()
                                .tag("Sample")
                                .summary("람쥐 메시지 반환")
                                .description("GET /sample 요청 시 '람쥐~' 문자열을 반환합니다.")
                ))
                .accept("*/*")
                .when()
                .get("/sample")
                .then()
                .statusCode(200)
                .body(equalTo("람쥐~"));
    }

}
