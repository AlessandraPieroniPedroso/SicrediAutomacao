#language:pt
  #author: alepieronif@gmail.com
Funcionalidade: Consultar Simulacões de acordo com parâmetros informados

    #Regra: Deve retornar HTTP Status 200 / Campos obrigatórios: nome, cpf, email, valor, parcelas e seguro
  @Teste
  Cenario: Deve retornar todas as simulações existentes
    Dado que cadastro uma nova simulação
    E insiro os dados para cadastro de simulação
    E concluo o envio das informações
    E tenho o retorno do statusCode 201
    E as informações são cadastradas
    Quando inicio de consulta por simulações existentes
    E finalizar envio de informações para consulta


    #Regra: Retorna HTTP Status 204 se não existir simulações cadastradas
  @Teste
  Cenario: Deve retornar statusCode 204 quando não existir dados em lista a serem exibidos
    Dado que removo todos os registros informados em lista
    Quando inicio nova consulta por simulações existentes
    E finalizar envio de informações para consulta
    Então tenho o retorno do statusCode 204

    #Regra: Deve retornar HTTP Status 200 / Campos obrigatórios: nome, cpf, email, valor, parcelas e seguro
  @Teste
  Cenario: Deve retornar uma simulação através do CPF
    Dado que cadastro uma nova simulação
    E insiro os dados para cadastro de simulação
    E concluo o envio das informações
    E tenho o retorno do statusCode 201
    E as informações são cadastradas
    Quando pesquiso pela simulação sem informar o CPF
    Então lista contendo dados de operações

    #Regra: Deve retornar HTTP Status 404
  @Teste
  Cenario: Deve retornar erro quando não encontrar simulação com parâmetros informados
    Dado inicio de consulta para CPF não cadastrado
    Quando finalizar envio de informações para consulta por CPF
    #Então tenho o retorno do statusCode 404
    E status da mensagem de erro
