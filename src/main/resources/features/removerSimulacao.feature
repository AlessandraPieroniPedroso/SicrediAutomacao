#language:pt
  #author:alepieronif@gmail.com

Funcionalidade: Remove uma simulação previamente cadastrada

    #Regra: Deve retornar HTTP Status 200
  @Teste
  Cenario: Deve remover simulação existente através do ID com sucesso
    Dado que quero cadastrar uma nova simulação
    E inserir dados para novo cadastro de simulação
    E encerro o envio de informações
    E deve ter o statusCode 201
    E cadastro com sucesso
    Quando inserir parâmetros para remoção de simulação para ID informado
    E finalizar envio de informações para remoção
    E deve exibir statusCode 200


    #Regra: Deve retornar HTTP Status 404
  #API quando não encontra um ID para exclusão está trazendo status code 200
  @Teste
  Cenario: Deve retornar status de erro para simulação não encontrada
    Dado insiro parâmetros para remoção de simulação para ID não cadastrada
    Quando finalizar envio de informações para remoção
    Então deve exibir statusCode 404
    E mensagem "Simulação não encontrada"