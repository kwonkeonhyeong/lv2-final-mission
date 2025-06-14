package finalmission.member.controller;

import finalmission.member.controller.dto.LoginRequest;
import finalmission.member.controller.dto.SignUpRequest;
import finalmission.member.service.MemberService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Test
    void 회원가입_요청_API_테스트() {
        SignUpRequest request = new SignUpRequest("testUser", "1234");

        RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/signup")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void 로그인_요청_API_테스트() {
        String nickname = "testUser";
        String password = "1234";

        SignUpRequest signUpRequest = new SignUpRequest(nickname, password);
        memberService.signup(signUpRequest);

        LoginRequest loginRequest = new LoginRequest(nickname, password);

        RestAssured.given().log().all()
                .contentType("application/json")
                .body(loginRequest)
                .when().post("/login")
                .then().log().all()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
