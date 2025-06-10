package finalmission.user.controller;

import finalmission.user.controller.dto.request.SignUpRequest;
import finalmission.user.service.UserService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class UserControllerTest {

    @Test
    void 회원가입_요청_테스트() {

        SignUpRequest signUpRequest = new SignUpRequest("test@email.com", "test123");

        RestAssured.given().log().all()
                .contentType("application/json")
                .body(signUpRequest)
                .when().post("/signup")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
}
