#language:pt
  #author:alepieronif@gmail.com
Funcionalidade: Consultar o CPF informando, retornando se ele possui ou não uma restrição

    #Regra: Se não possui restrição do HTTP Status 204 é retornado
    #Erro: Não encontrei massa para testes em aplicação
  @Teste
  Esquema do Cenario: : Deve retorna status de pessoa sem restrição corretamente
    Dado inicio de pesquisa
    Quando informo CPF de pessoa sem restrição
    Então deve retornar statusCode <HttpCode>
    Exemplos:
      | HttpCode |
      | 204     |

    #Regra: Se possui restrição o HTTP Status 200 é retornado com a mensagem "O CPF 99999999999 possui restrição"
  @Teste
  Esquema do Cenario: Deve retorna status de pessoa com restrição corretamente
    Dado inicio de pesquisa
    Quando informo CPF de pessoa com restrição
      | CPF   |
      | <CPF> |
    Então deve retornar statusCode <HttpCode>
    E mensagem O CPF <CPF> tem problema

    Exemplos:
      | CPF         |HttpCode|
      | 97093236014 | 200    |
      | 60094146012 | 200    |
      | 84809766080 | 200    |
      | 62648716050 | 200    |
      | 26276298085 | 200    |
      | 01317496094 | 200    |
      | 55856777050 | 200    |
      | 19626829001 | 200    |
      | 24094592008 | 200    |
      | 58063164083 | 200    |



