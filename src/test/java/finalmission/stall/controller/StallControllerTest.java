package finalmission.stall.controller;

import finalmission.stall.controller.dto.request.StallCreateRequest;
import finalmission.stall.controller.dto.response.StallCreateResponse;
import finalmission.stall.controller.dto.response.StallInfosResponse;
import finalmission.stall.service.StallService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StallControllerTest {

    @Autowired
    private StallService stallService;

    @Test
    void 화장실_사로_생성_요청_API_테스트() {
        StallCreateRequest request = new StallCreateRequest("1사로");

        RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().post("/stalls")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void 화장실_사로_전체_조회_API_테스트() {
        StallCreateRequest request = new StallCreateRequest("1사로");

        stallService.create(request);

        StallInfosResponse stallInfosResponse = RestAssured.given().log().all()
                .when().get("/stalls")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body().as(StallInfosResponse.class);

        assertThat(stallInfosResponse.stallInfos().size()).isEqualTo(1);
    }

    @Test
    void 화장싱_사로_삭제_요청_API_테스트() {
        StallCreateRequest request = new StallCreateRequest("1사로");

        StallCreateResponse stallCreateResponse = stallService.create(request);

        RestAssured.given().log().all()
                .contentType("application/json")
                .body(request)
                .when().delete(String.format("/stalls/%s", stallCreateResponse.id()))
                .then().log().all()
                .statusCode(HttpStatus.NO_CONTENT.value());

        StallInfosResponse stalls = stallService.findStalls();
        assertThat(stalls.stallInfos().size()).isEqualTo(0);
    }
}
