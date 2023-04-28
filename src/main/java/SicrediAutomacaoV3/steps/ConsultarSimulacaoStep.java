package SicrediAutomacaoV3.steps;

        import io.cucumber.java.pt.Dado;
        import io.cucumber.java.pt.E;
        import io.cucumber.java.pt.Então;
        import io.cucumber.java.pt.Quando;
        import io.restassured.RestAssured;
        import io.restassured.http.ContentType;
        import io.restassured.response.Response;
        import io.restassured.specification.RequestSpecification;
        import SicrediAutomacaoV3.utils.GeradorDados;

        import static SicrediAutomacaoV3.steps.CadastrarSimulacaoStep.*;
        import static org.hamcrest.Matchers.*;

public class ConsultarSimulacaoStep {
    Response response;
    RequestSpecification request;
    GeradorDados geradorDados;

    @Dado("^que cadastro uma nova simulação$")
    public void queCadastroUmaNovaSimulacao() {
        request = RestAssured.given()
                .log()
                .all()
                .accept("application/json")
                .contentType(ContentType.JSON);
    }
    @Dado("^inicio de consulta para CPF não cadastrado$")
    public void inicioDeConsultaParaCPFNaoCadastrado() throws Exception {
        geradorDados = new GeradorDados();
        cpf = geradorDados.getCpf();
        request = RestAssured.given().log().all();
    }

    @Quando("^pesquiso por uma simulação pelo CPF$")
    public void pesquisoPorUmaSimulacaoPeloCpf() throws Exception {
        String cpfRetornado = response.then().extract().path("cpf");
    }

    @Quando("^pesquiso pela simulação sem informar o CPF$")
    public void pesquisoPelaSimulacaoSemInformarOCpf(){
        response = request.when().log().all()
                .get("/api/v1/simulacoes");

    }


    @E("insiro os dados para cadastro de simulação")
    public void inseroDadosParaCadastroDeSimulação() throws Exception {
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

    @E("^tenho o retorno do statusCode (.*)$")
    public void tenhoORetornoDoStatusCode(int statusCode){
        response.then().log().all()
                .statusCode(statusCode);

    }





    @E("^as informações são cadastradas$")
    public void asInfomacoesSaoCadastradas() {
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
    @E("concluo o envio das informações")
    public void concluoOEnvioDasInformações() {
        response = request.when().log().all()
                .post("/api/v1/simulacoes");
    }
    @Então("^inicio de consulta por simulações existentes$")
    public void inicioDeConsultaPorSimulacoesExistentes() {
        String cpfRetornado = response.then().extract().path("cpf");
        //request = RestAssured.given().log().all();
    }

    @Então("^inicio nova consulta por simulações existentes$")
    public void inicioNovaDeConsultaPorSimulacoesExistentes() {
        request = RestAssured.given().log().all();
    }

    @Então("^inicio de consulta por simulações para CPF informado$")
    public void inicioDeConsultaPorSimulacoesParaCPFInformado() {
        request = RestAssured.given().log().all();
    }

    @E("finalizar envio de informações para consulta")
    public void finalizarEnvioDeInformaçõesParaConsulta() {
        response = request.when().log().all()
                .get("/api/v1/simulacoes/" + cpf);
    }

    @E("finalizar envio de informações para consulta por CPF")
    public void finalizarEnvioDeInformaçõesParaConsultaPorCPF() {
        response = request.when().log().all()
                .get("/api/v1/simulacoes/" + cpf);
    }


    @E("lista contendo dados de operações")
    public void listaContendoDadosDeOperacoes() {
        response.then().log().all()
                .body("id", hasItem(id));
    }

    @E("^status da mensagem de erro$")
    public void mensagemDeErro() {
        //String cpfRetornado = response.then().extract().path("cpf");
        response.then().log().all()
                .body("mensagem", is("CPF " + cpf + " não encontrado"));
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
