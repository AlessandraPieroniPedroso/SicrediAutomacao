package SicrediAutomacaoV3.utils;


        import com.github.javafaker.Faker;

        import java.util.Random;



        import com.github.javafaker.Faker;

//import java.math.BigDecimal;
        import java.util.Random;

public class GeradorDados {
    Random gerador = new Random();
    private final Faker faker = new Faker();

    /* Geração de dados para validação de cadastro inicial */
    private String CPF = geraCPF();
    private String nome = faker.name().firstName().replace("'", " ");
    private String sobrenome = faker.name().lastName().replace("'", " ");
    private String nomeCompleto = faker.name().fullName();
    private String email = nome + "@email.com";
    private Integer parcela = Integer.valueOf(faker.number().numberBetween(2, 48));
    private int valor = faker.number().numberBetween(1000, 40000);

    /*
     * -----------------------------------------------------------------------------
     * --------------
     */
    /* Geração de CPF */
    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = new Integer(0);
        } else {
            primDig = new Integer(11 - (soma % 11));
        }

        soma = 0;
        peso = 11;

        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }
        soma += primDig.intValue() * 2;

        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = new Integer(0);
        } else {
            segDig = new Integer(11 - (soma % 11));
        }

        return primDig.toString() + segDig.toString();
    }

    public static String geraCPF() {
        String iniciais = "";
        Integer numero;
        for (int i = 0; i < 9; i++) {
            numero = new Integer((int) (Math.random() * 10));
            iniciais += numero.toString();
        }
        return iniciais + calcDigVerif(iniciais);
    }

    public GeradorDados() throws Exception {
    }

    /*
     * -----------------------------------------------------------------------------
     * --------------
     */
    /* Construtores */
    public String getCpf() {
        return CPF;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public int getValor() {
        return valor;
    }

    public Integer getParcela() {
        return parcela;
    }
}
