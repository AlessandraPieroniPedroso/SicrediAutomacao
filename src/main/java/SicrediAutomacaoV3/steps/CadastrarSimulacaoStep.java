package SicrediAutomacaoV3.steps;
        import io.cucumber.datatable.DataTable;
        import io.cucumber.java.pt.Dado;
        import io.cucumber.java.pt.E;
        import io.cucumber.java.pt.Então;
        import io.cucumber.java.pt.Quando;
        import io.restassured.RestAssured;
        import io.restassured.http.ContentType;
        import io.restassured.response.Response;
        import io.restassured.specification.RequestSpecification;

        import java.util.Map;

        import static org.hamcrest.Matchers.is;
        import static org.hamcrest.Matchers.notNullValue;

        import io.cucumber.datatable.DataTable;
        import io.cucumber.java.pt.Dado;
        import io.cucumber.java.pt.E;
        import io.cucumber.java.pt.Então;
        import io.cucumber.java.pt.Quando;
        import io.restassured.RestAssured;
        import io.restassured.http.ContentType;
        import io.restassured.response.Response;
        import io.restassured.specification.RequestSpecification;
        import SicrediAutomacaoV3.utils.GeradorDados;
        import java.util.Map;
        import static org.hamcrest.Matchers.is;
        import static org.hamcrest.Matchers.notNullValue;

public class CadastrarSimulacaoStep {
    Response response;
    RequestSpecification request;
    GeradorDados geradorDados;
    public static String nomeCompleto = null;
    public static String cpf = null;
    public static String email = null;
    public static int valor = 0;
    public static Integer parcelas = null;
    public static Boolean seguro = true;
    public static String cpfAux = null;
    public static int id = 0;

    @Dado("^que preciso cadastrar uma nova simulação$")
    public void quePrecisoCadastrarUmaNovaSimulacao() {
        request = RestAssured.given()
                .log()
                .all()
                .accept("application/json")
                .contentType(ContentType.JSON);
    }

    @Quando("inserir dados corretos para cadastro de simulação")
    public void inserirDadosCorretosParaCadastroDeSimulaçãoComSeguro() throws Exception {
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

    @Quando("^inserir conjunto de dados para campo CPF nulo$")
    public void InserirConjuntoDeDadosParaCampoCPFNulo() throws Exception {
        cadastrar();

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n" +
                        "  \"cpf\": null,\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @Quando("^inserir conjunto de dados para campo NOME nulo$")
    public void InserirConjuntoDeDadosParaCampoNOMENulo() throws Exception {
        cadastrar();

        request.body(
                "{\n" +
                        "  \"nome\": null,\n" +
                        "  \"cpf\": \"" + cpf + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @Quando("^inserir conjunto de dados para campo EMAIL nulo$")
    public void InserirConjuntoDeDadosParaCampoEMAILNulo() throws Exception {
        cadastrar();

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n" +
                        "  \"cpf\": \"" + cpf + "\",\n" +
                        "  \"email\": null,\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @Quando("^inserir conjunto de dados para campo VALOR nulo$")
    public void InserirConjuntoDeDadosParaCampoVALORNulo() throws Exception {
        cadastrar();

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n" +
                        "  \"cpf\": \"" + cpf + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": null,\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @Quando("^inserir conjunto de dados para campo PARCELA nulo$")
    public void InserirConjuntoDeDadosParaCampoPARCELANulo() throws Exception {
        cadastrar();

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n" +
                        "  \"cpf\": \"" + cpf + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": null,\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @Quando("^inserir dados para cadastro de simulação com PARCELA \"(.*)\"$")
    public void InserirConjuntoDeDadosParaCampoPARCELA(Integer parcelaParameter) throws Exception {
        cadastrar();
        parcelas = parcelaParameter;

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

    @Quando("^inserir conjunto de dados para campo SEGURO nulo$")
    public void InserirConjuntoDeDadosParaCampoSEGURONulo() throws Exception {
        cadastrar();

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n" +
                        "  \"cpf\": \"" + cpf + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": null\n" +
                        "}").log().all();
    }

    @Quando("^inserir dados para cadastro com CPF (.*) em formato inválido$")
    public void inserirDadosParaCadastroComCPFEmFormatoInvalido(String CPFParameter) throws Exception {
        cpf = CPFParameter;
        cpfAux = CPFParameter;
        cadastrar();

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n" +
                        "  \"cpf\": \"" + cpfAux + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @Quando("^inserir dados para cadastro de simulação com Email (.*) em formato inválido$")
    public void inserirDadosParacadastroDeSimulacaoComEmailEmFormatoInvalido(String emailParameter) throws Exception {
        //for (Map<Object, Object> data : table.asMaps(String.class, String.class)) {

        email = emailParameter;
        cadastrar();

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n" +
                        "  \"cpf\": \"" + cpf + "\",\n" +
                        //"  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }


    @Quando("^inserir dados para cadastro de simulação com SEGURO \"(.*)\"$")
    public void inserirDadosParaCadastroDeSimulacaoComSEGURO(String seguroParameter) throws Exception {
        cadastrar();

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n" +
                        "  \"cpf\": \"" + cpf + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguroParameter + "\n" +
                        "}").log().all();
    }

    @Quando("^inserir dados para cadastro de simulação com VALOR \"(.*)\"$")
    public void inserirDadosParaCadastroDeSimulacaoComVALOR(int valorParameter) throws Exception {
        cadastrar();
        valor = valorParameter;

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

    @Quando("^inserir dados para cadastro de simulação com CPF já cadastrado$")
    public void inserirDadosParaCadastroDeSimulacaoComCPFjaCadastrado() throws Exception {
        cadastrar();
        cpf = cpfAux;

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

    @E("finalizar envio de informações")
    public void finalizarEnvioDeInformações() {
        response = request.when().log().all()
                .post("/api/v1/simulacoes");
    }



    @Então("^o retorno do status code deve ser (.*)$")
    public void oRetornoDoStatusCodeDeveSer(int statusCode) {
        response.then().log().all()
                .statusCode(statusCode);
    }

    @E("^informações cadastradas$")
    public void infomacoesCadastradas() {
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

    @E("^status de erro para campo CPF \"(.*)\"$")
    public void mensagemDeErroParaCPF(String mensagem) {
        response.then().log().all()
                .body("erros.cpf", is(mensagem));
    }

    @E("^status de erro para campo VALOR \"(.*)\"$")
    public void statusDeErroValor(String mensagem) {
        response.then().log().all()
                .body("erros.valor", is(mensagem));
    }

    @E("^status de erro para campo PARCELA \"(.*)\"$")
    public void statusDeErroParcela(String mensagem) {
        response.then().log().all()
                .body("erros.parcelas", is(mensagem));
    }

    @E("^status de erro para campo NOME \"(.*)\"$")
    public void statusDeErroNome(String mensagem) {
        response.then().log().all()
                .body("erros.nome", is(mensagem));
    }

    @E("^status de erro para campo EMAIL \"(.*)\"$")
    public void statusDeErroEmail(String mensagem) {
        response.then().log().all()
                .body("erros.email", is(mensagem));
    }

    @E("^status de erro para campo SEGURO \"(.*)\"$")
    public void statusDeErroSeguro(String mensagem) {
        response.then().log().all()
                .body("erros.seguro", is(mensagem));
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

