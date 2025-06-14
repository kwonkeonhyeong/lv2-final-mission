package finalmission.member.controller;

import finalmission.member.controller.dto.SignUpRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MemberControllerTest {

    @Test
    void 회원가입() {
        SignUpRequest request = new SignUpRequest("testUser", "1234");

        RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/signup")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
