package org.testBack;

import io.restassured.response.Response;
import lombok.NoArgsConstructor;
import org.testing.BaseTest;
import org.testing.User;

import java.util.List;

import static io.restassured.RestAssured.given;

@NoArgsConstructor
public class BackBaseTest {

    public void registerUser() throws InterruptedException {
        // crear un usuario de prueba desde el primer registro del Json, username: DawnNunez, password: 123456
        BaseTest baseTest = new BaseTest();
        baseTest.registerUser(User.firstUser());
        baseTest.close();
    }

    public Integer getCustomerId() {
        // Realiza la solicitud GET al endpoint de login
        Response response = given()
                .when()
                .get("http://parabank.parasoft.com/parabank/services/bank/login/DawnNunez/123456")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Parsear el cuerpo de la respuesta para obtener el customerId
        return response.xmlPath().getInt("customer.id");

    }
    public List<Integer> getAccountIds(Integer customerId) {
        // Realiza la solicitud GET al endpoint de cuentas
        Response response = given()
                .pathParam("customerId", customerId)
                .when()
                .get("http://parabank.parasoft.com/parabank/services/bank/customers/{customerId}/accounts")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Parsear el cuerpo de la respuesta XML para obtener los account IDs
        return response.xmlPath().getList("accounts.account.id", Integer.class);
    }

    public void createAccount(Integer customerId, Integer newAccountType, Integer fromAccountId) {
        // Realiza la solicitud POST al endpoint de creación de cuentas
        given()
                .queryParam("customerId", customerId)
                .queryParam("newAccountType", newAccountType)
                .queryParam("fromAccountId", fromAccountId)
                .when().post("https://parabank.parasoft.com/parabank/services/bank/createAccount")
                .then()
                .statusCode(200);

    }
}
