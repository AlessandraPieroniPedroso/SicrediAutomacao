package SicrediAutomacaoV3.steps;

import SicrediAutomacaoV3.utils.GeradorDados;
import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import SicrediAutomacaoV3.utils.GeradorDados;


import com.github.javafaker.Faker;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import SicrediAutomacaoV3.steps.ConsultarSimulacaoStep;

//import static org.hamcrest.Matchers.notNullValue;
//import static org.hamcrest.Matchers.nullValue;
import static SicrediAutomacaoV3.steps.CadastrarSimulacaoStep.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class RemoverSimulacaoStep {
    Response response;
    RequestSpecification request;
    GeradorDados geradorDados;


    @Dado("^que quero cadastrar uma nova simulação$")
    public void queQueroCadastrarUmaNovaSimulacao() {
        request = RestAssured.given()
                .log()
                .all()
                .accept("application/json")
                .contentType(ContentType.JSON);
    }
    @Dado("^insiro parâmetros para remoção de simulação para ID não cadastrada$")
    public void insiroParametrosParaRemocaoDeSimulacaoParaIDNaoCadastrada() {
        request = RestAssured.given().log().all();
        Faker faker = new Faker();
        id = faker.number().numberBetween(9999,9999999);
    }

    @Quando("inserir dados para novo cadastro de simulação")
    public void inserirDadosParaNovoCadastroDeSimulação() throws Exception {
        cadastrar();
        cpfAux = cpf;

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n" +
                        "  \"cpf\": \"" + cpf + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @Quando("^inserir parâmetros para remoção de simulação para ID informado$")
    public void inserirParametrosParaRemocaoDesimulacaoParaIDInformado() {
        Integer idRetornado = response.then().extract().path("id");
        //request = RestAssured.given().log().all();
    }

    @E("encerro o envio de informações")
    public void encerroOEnvioDeInformações() {
        response = request.when().log().all()
                .post("/api/v1/simulacoes");
    }

    @Então("^deve ter o statusCode (.*)$")
    public void deveTerOStatusCode(int statusCode) {
        response.then().log().all()
                .statusCode(statusCode);
    }

    @E("^cadastro com sucesso$")
    public void cadastroComSucesso() {
        response.then().log().all()
                .body("id", notNullValue())
                .body("nome", is(nomeCompleto))
                .body("cpf", is(cpf))
                .body("email", is(email))
                .body("valor", is(valor))
                .body("parcelas", is(parcelas))
                .body("seguro", is(seguro));

        id = response.path("id");
    }
    @E("finalizar envio de informações para remoção")
    public void finalizarEnvioDeInformaçõesParaRemoção() {
        response = request.when().log().all()
                .delete("/api/v1/simulacoes/" + id);
    }

    @Então(("^deve exibir statusCode (.*)"))
    public void entaoDeveExibirStatus(int statusCode) {
        response.then().log().all()
                .statusCode(statusCode);
    }

    @E("^consulto a simulaçao para CPF informado$")
    public void ConsultoASimulacaoParaCPFInformado() {
        Integer idRetornado = response.then().extract().path("id");
    }

    @E("que removo todos os registros informados em lista")
    public void removoTodosOsRegistrosInformadosEmLista() {
        String body;

        do {
            //Consultar simulações
            request = RestAssured.given().log().all();
            response = request.when().log().all().get("/api/v1/simulacoes");
            response.then().log().all();
            body = response.body().asString();
            if (body.contains("id")){
                id = response.path("id[0]");
                //Apagar
                request = RestAssured.given().log().all();
                response = request.when().log().all().delete("/api/v1/simulacoes/" + id);
                response.then().log().all();
            }
        } while (body.contains("id"));
    }

    public void cadastrar() throws Exception {
        geradorDados = new GeradorDados();
        nomeCompleto = geradorDados.getNome();
        cpf = geradorDados.getCpf();
        email = geradorDados.getEmail();
        valor = geradorDados.getValor();
        parcelas = geradorDados.getParcela();
    }
}
