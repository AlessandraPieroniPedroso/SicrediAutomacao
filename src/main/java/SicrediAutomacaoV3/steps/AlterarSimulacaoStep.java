package SicrediAutomacaoV3.steps;
        import com.squareup.okhttp.RequestBody;
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

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import static org.hamcrest.Matchers.is;
        import static org.hamcrest.Matchers.notNullValue;
        import static SicrediAutomacaoV3.steps.CadastrarSimulacaoStep.*;

public class AlterarSimulacaoStep {
    Response response;
    RequestSpecification request;
    GeradorDados geradorDados;

    String nome;

    @Dado("^que desejo realizar o cadastro de uma nova simulação$")
    public void queDesejoRealizarOCadastroDeUmaNovaSimulacao() throws Exception {
        request = RestAssured.given()
                .log()
                .all()
                .accept("application/json")
                .contentType(ContentType.JSON);

        geradorDados = new GeradorDados();
        cpfAux = geradorDados.getCpf();
    }

    @E("inserir dados para cadastro de simulação")
    public void inserirDadosParaCadastroDeSimulaçãoComSeguro() throws Exception {
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

    @E("finalizo envio de informações")
    public void finalizoEnvioDeInformações() {
        response = request.when().log().all()
                .post("/api/v1/simulacoes");
    }

    @E("^realiza o cadastro com sucesso$")
    public void realizaOCadastroComSucesso() {
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

    @Quando("^preciso atualizar o cadastro de uma simulação para CPF informado$")
    public void precisoAtualizarOCadastroDeUmaSimulacaoParaCPFInformado() throws Exception {
        String cpfRetornado = response.then().extract().path("cpf");
    }

    @Dado("^preciso atualizar o cadastro de uma simulação para CPF não cadastrado$")
    public void precisoAtualizarOCadastroDeUmaSimulacaoParaCPFNãoCadastrado() throws Exception {
        request = RestAssured.given()
                .log()
                .all()
                .accept("application/json")
                .contentType(ContentType.JSON);
    }

    @Quando("^informo um CPF não cadastrado$")
    public void informoUmCpfNãoCadastrado() throws Exception{
        geradorDados = new GeradorDados();
        cpfAux = geradorDados.getCpf();
        response = request.when().log().all()
                .put("/api/v1/simulacoes/" + cpfAux);



    }

    @Então("^deve receber o status code (.*)$")
    public void deveReceberOStatusCode(int statusCode) {
        response.then().log().all()
                .statusCode(statusCode);
    }

    @E("^inserir dados a serem atualizados para nome$")
    public void inserirDadosASeremAtualizadosParaNOME() throws Exception {
        geradorDados = new GeradorDados();
        cpfAux = response.then().extract().path("cpf");
        email = response.then().extract().path("email");
        valor = response.then().extract().path("valor");
        parcelas = response.then().extract().path("parcelas");
        seguro = response.then().extract().path("seguro");

        request.body(
                "{\n" +
                        "  \"nome\": \"" + geradorDados.getNomeCompleto() + "\",\n"+
                        "  \"cpf\": \"" + cpfAux + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para CPF formato$")
    public void inserirDadosASeremAtualizadosParaCPFFormato() throws Exception {
        geradorDados = new GeradorDados();
        cpf = "999.999.999-99";
        nome = response.then().extract().path("nomeCompleto");
        email = response.then().extract().path("email");
        valor = response.then().extract().path("valor");
        parcelas = response.then().extract().path("parcelas");
        seguro = response.then().extract().path("seguro");

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n"+
                        "  \"cpf\": \"" + cpf+"\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para CPF$")
    public void inserirDadosASeremAtualizadosParaCPF() throws Exception {
        geradorDados = new GeradorDados();
        cpfAux = response.then().extract().path("cpf");
        nome = response.then().extract().path("nomeCompleto");
        email = response.then().extract().path("email");
        valor = response.then().extract().path("valor");
        parcelas = response.then().extract().path("parcelas");
        seguro = response.then().extract().path("seguro");

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n"+
                        "  \"cpf\": \"" + geradorDados.getCpf()+"\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para CPF não existente$")
    public void inserirDadosASeremAtualizadosParaCPFnaoExistente() throws Exception {
        geradorDados = new GeradorDados();
        cpfAux = geradorDados.getCpf();
        seguro = true;

        request.body(
                "{\n" +
                        "  \"nome\": \"" + geradorDados.getNomeCompleto() + "\",\n" +
                        "  \"cpf\": \"" + cpfAux + "\",\n" +
                        "  \"email\": \"" + geradorDados.getEmail() + "\",\n" +
                        "  \"valor\": " + geradorDados.getValor() + ",\n" +
                        "  \"parcelas\": " + geradorDados.getParcela() + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para EMAIL$")
    public void inserirDadosASeremAtualizadosParaEMAIL() throws Exception {
        geradorDados = new GeradorDados();
        cpfAux = response.then().extract().path("cpf");
        nome = response.then().extract().path("nomeCompleto");
        email = response.then().extract().path("email");
        valor = response.then().extract().path("valor");
        parcelas = response.then().extract().path("parcelas");
        seguro = response.then().extract().path("seguro");

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n"+
                        "  \"cpf\": \"" + cpf+"\",\n" +
                        "  \"email\": \"" + geradorDados.getEmail() + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para VALOR$")
    public void inserirDadosASeremAtualizadosParaVALOR() throws Exception {
        geradorDados = new GeradorDados();
        cpfAux = response.then().extract().path("cpf");
        email = response.then().extract().path("email");
        valor = response.then().extract().path("valor");
        parcelas = response.then().extract().path("parcelas");
        seguro = response.then().extract().path("seguro");

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n"+
                        "  \"cpf\": \"" + cpfAux + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + geradorDados.getValor() + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para VALOR \"(.*)\"$")
    public void inserirDadosASeremAtualizadosParaVALOREspecificado(int valorParameter) throws Exception {
        geradorDados = new GeradorDados();
        cpfAux = response.then().extract().path("cpf");
        email = response.then().extract().path("email");
        valor = valorParameter;
        parcelas = response.then().extract().path("parcelas");
        seguro = response.then().extract().path("seguro");

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n"+
                        "  \"cpf\": \"" + cpfAux + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para PARCELAS$")
    public void inserirDadosASeremAtualizadosParaPARCELAS() throws Exception {
        geradorDados = new GeradorDados();
        cpfAux = response.then().extract().path("cpf");
        email = response.then().extract().path("email");
        valor = response.then().extract().path("valor");
        parcelas = response.then().extract().path("parcelas");
        seguro = response.then().extract().path("seguro");

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n"+
                        "  \"cpf\": \"" + cpfAux + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + geradorDados.getParcela() + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para PARCELA \"(.*)\"$")
    public void inserirDadosASeremAtualizadosParaPARCELA(Integer parcelaParameter) throws Exception {
        geradorDados = new GeradorDados();
        cpfAux = response.then().extract().path("cpf");
        email = response.then().extract().path("email");
        valor = response.then().extract().path("valor");
        parcelas = parcelaParameter;
        seguro = response.then().extract().path("seguro");

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n"+
                        "  \"cpf\": \"" + cpfAux + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para SEGURO(.*)$")
    public void inserirDadosASeremAtualizadosParaSEGURO(boolean seguroParameter) throws Exception {
        geradorDados = new GeradorDados();
        cpfAux = response.then().extract().path("cpf");
        email = response.then().extract().path("email");
        valor = response.then().extract().path("valor");
        parcelas = response.then().extract().path("parcelas");
        seguro = seguroParameter;

        request.body(
                "{\n" +
                        "  \"nome\": \"" + nomeCompleto + "\",\n"+
                        "  \"cpf\": \"" + cpfAux + "\",\n" +
                        "  \"email\": \"" + email + "\",\n" +
                        "  \"valor\": " + valor + ",\n" +
                        "  \"parcelas\": " + parcelas + ",\n" +
                        "  \"seguro\": " + seguro + "\n" +
                        "}").log().all();
    }

    @E("^inserir dados a serem atualizados para EMAIL em formato invalido$")
    public void inserirDadosASeremAtualizadosParaEmailEmFormatoInvalido(DataTable table) throws Exception {
        for (Map<Object, Object> data : table.asMaps(String.class, String.class)) {
            email = (String) data.get("EMAIL");

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
    }

    @E("finalizar alteracões de informações")
    public void finalizaralteracõesDeInformações() {
        response = request.when().log().all()
                .put("/api/v1/simulacoes/" + cpfAux);
    }
    @E("^dados alterado com sucesso$")
    public void dadosAlteradosComSucesso(){
        response.then().log().all()
                .body("id", notNullValue())
                .body("nome", is(nomeCompleto))
                .body("cpf", is(cpf))
                .body("email", is(email))
                .body("valor", is(Float.valueOf(valor)))
                .body("parcelas", is(parcelas))
                .body("seguro", is(seguro));


    }
    @E("nome alterado com sucesso")
    public void nomeAlteradoComSucesso() {
        response.then().extract().path("nome");

    }

    @E("cpf alterado com sucesso")
    public void cpfAlteradoComSucesso() {
        response.then().extract().path("cpf");

    }

    @E("email alterado com sucesso")
    public void emailAlteradoComSucesso() {
        response.then().extract().path("email");

    }

    @E("valor alterado com sucesso")
    public void valorAlteradoComSucesso() {
        response.then().extract().path("valor");

    }

    @E("parcelas alterado com sucesso")
    public void parcelasAlteradoComSucesso() {
        response.then().extract().path("parcelas");

    }

    @E("seguro alterado com sucesso")
    public void seguroAlteradoComSucesso() {
        response.then().extract().path("seguro");

    }
    @E("informação de erro para CPF")
    public void informaçãoDeErro() {
        response.then().log().all()
                .body("mensagem", is("CPF " + cpfAux + " não encontrado"));
    }

    @E("^status de erro para EMAIL \"(.*)\"$")
    public void statusDeErroEmail(String mensagem) {
        response.then().log().all()
                .body("erros", is(mensagem));
    }

    @E("^status de erro \"(.*)\"$")
    public void statusDeErro(String mensagem) {
        response.then().log().all()
                .body("mensagem", is(mensagem));
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
