package SicrediAutomacaoV3.steps;

        import io.cucumber.datatable.DataTable;
        import io.cucumber.java.pt.Dado;
        import io.cucumber.java.pt.E;
        import io.cucumber.java.pt.Então;
        import io.cucumber.java.pt.Quando;
        import io.restassured.RestAssured;
        import io.restassured.response.Response;
        import io.restassured.specification.RequestSpecification;

        import java.util.Map;

        import static org.hamcrest.Matchers.is;


public class ConsultarRestricaoStep {
    Response response;
    RequestSpecification request;
    public static String cpfSemRestricoes = "63271443653";
    public String cpfComRestricoes = null;

    public ConsultarRestricaoStep() throws Exception {
    }

    @Dado("^inicio de pesquisa$")
    public void inicioDePesquisa() {
        request = RestAssured.given().log().all();
    }

    @Quando("^informo CPF de pessoa com restrição$")
    public void informoCPFDePessoaComRestrição(DataTable table) {

        for (Map<Object, Object> data : table.asMaps(String.class, String.class)) {
            cpfComRestricoes = (String) data.get("CPF");

            response = request.when().log().all()
                    .get("/api/v1/restricoes/" + cpfComRestricoes);
        }
    }


    @Quando("^informo CPF de pessoa sem restrição$")
    public void informoCPFDePessoaSemRestrição() {
        response = request.when().log().all()
                .get("/api/v1/restricoes/" + cpfSemRestricoes);
    }

    @Então("^deve retornar statusCode (.*)$")
    public void deveRetornarStatusCode(int statusCode) {
        response.then().log().all()
                .statusCode(statusCode);
    }

    @E("^mensagem (.*)$")
    public void mensagem(String message) {
        response.then().log().all()
                .body("mensagem", is(message));
    }


}
