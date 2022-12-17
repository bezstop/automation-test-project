package api;

import api.colors.ColorsData;
import api.registration.Registration;
import api.registration.SuccessRegistration;
import api.registration.UnSuccessRegistration;
import api.specifications.Specifications;
import api.users.UserData;
import api.users.UserTimeUpdateRequest;
import api.users.UserTimeUpdateResponse;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static api.specifications.Specifications.*;
import static io.restassured.RestAssured.given;

public class ReqresPOJOTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarAndIdTest() {
        Specifications.installSpecification(requestSpecification(URL), responseSpecificationOK200());
        List<UserData> users = given()
                .when()
                //                .contentType(ContentType.JSON)
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        //        users.stream().forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        //        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < avatars.size(); i++) {
            boolean contains = avatars.get(i).contains(ids.get(i));
            Assert.assertTrue(contains);
        }
    }

    @Test
    public void successRegistrationTest() {
        Specifications.installSpecification(requestSpecification(URL), responseSpecificationOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Registration newUser = new Registration("eve.holt@reqres.in", "pistol");
        SuccessRegistration successRegistration = given()
                .body(newUser)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessRegistration.class);
        Assert.assertNotNull(successRegistration.getId());
        Assert.assertNotNull(successRegistration.getToken());

        Assert.assertEquals(id, successRegistration.getId());
        Assert.assertEquals(token, successRegistration.getToken());
    }

    @Test
    public void unSuccessRegistrationTest() {
        Specifications.installSpecification(requestSpecification(URL), responseSpecificationError400());
        String ERROR_MESSAGE = "Missing password";
        Registration newUser = new Registration("sydney@fife");
        UnSuccessRegistration unSuccessRegistration = given()
                .body(newUser)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessRegistration.class);
        Assert.assertEquals(ERROR_MESSAGE, unSuccessRegistration.getError());
    }

    @Test
    public void checkSortedYearsTest() {
        Specifications.installSpecification(requestSpecification(URL), responseSpecificationOK200());
        List<ColorsData> colorsData = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        List<Integer> years = colorsData.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(years, sortedYears);
    }

    @Test
    public void checkDeleteUsersTest() {
        Specifications.installSpecification(requestSpecification(URL), responseSpecificationCode(204));
                given()
                .when()
                .delete("api/users/2")
                .then().log().all();
        //                .extract().body().jsonPath().getList("data", ColorsData.class);
        //        List<Integer> years = colorsData.stream().map(ColorsData::getYear).collect(Collectors.toList());
        //        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        //        Assert.assertEquals(years, sortedYears);
    }

    @Test
    public void timeUpdateTest() {
        Specifications.installSpecification(requestSpecification(URL), responseSpecificationCode(200));
        UserTimeUpdateRequest userTimeUpdateRequest = new UserTimeUpdateRequest("morpheus","zion resident");
        UserTimeUpdateResponse userTimeUpdateResponse = given()
                .body(userTimeUpdateRequest)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeUpdateResponse.class);
        String regexValueCurrentTime = "(.{11})$";
        String regexValueUpdateTime = "(.{5})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regexValueCurrentTime, "");
        System.out.println("Текущее время:" + currentTime);
        String updateTime = userTimeUpdateResponse.getUpdatedAt().replaceAll(regexValueUpdateTime, "");
        System.out.println("Обновленное время:" + updateTime);
        Assert.assertEquals(currentTime, updateTime);

    }
}
