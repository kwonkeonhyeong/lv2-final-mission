package finalmission.reservation.controller;

import finalmission.reservation.dto.request.StallUseRequest;
import finalmission.reservation.repository.StallReservationRepository;
import finalmission.toilet.entity.Stall;
import finalmission.toilet.repository.StallRepository;
import finalmission.user.auth.provider.JwtAuthorizationProvider;
import finalmission.user.controller.dto.request.LoginRequest;
import finalmission.user.controller.dto.request.SignUpRequest;
import finalmission.user.entity.Member;
import finalmission.user.repository.MemberRepository;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class StallReservationControllerTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private StallRepository stallRepository;

    @Test
    void 외부_API_랜덤_이름_요청_및_사로_예약_생성() {

        Member member = new Member("test@test.com", "1234");
        memberRepository.save(member);

        Stall stall = new Stall("1사로", "사용가능");
        stallRepository.save(stall);

        StallUseRequest stallUseRequest = new StallUseRequest(1L,"test@test.com");

        RestAssured.given().log().all()
                .contentType("application/json")
                .body(stallUseRequest)
                .when().post("/stall/reservation")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }
/*
    @Test
    void 로그인_요청_시_토큰_응답() {
        // given
        Member member = new Member("test@test.com", "1234");

        Member savedMember = memberRepository.save(member);

        LoginRequest request = new LoginRequest(savedMember.getEmail(), savedMember.getPassword());

        // when
        RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/login")
                .then().log().all()
                .statusCode(200)
                .cookie("token");
    }*/
}
