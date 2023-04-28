#language:pt
  #author:alepieronif@gmail.com
Funcionalidade: Alterar Simulacões de acordo com parâmetros informados

    #Regra: Deve retornar HTTP Status 200
  @Teste
  Cenário: Deve atualizar uma simulação existente através do CPF para campo NOME
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para nome
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E nome alterado com sucesso



    #Regra: Deve retornar HTTP Status 200
  @Teste
  Cenario: Deve atualizar uma simulação existente através do CPF para campo CPF
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para CPF
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E cpf alterado com sucesso

    #Regra: Deve retornar HTTP Status 200
  @Teste
  Cenario: Deve atualizar uma simulação existente através do CPF para campo EMAIL
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para EMAIL
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E email alterado com sucesso

    #Regra: Deve retornar HTTP Status 200
  @Teste
  Cenario: Deve atualizar uma simulação existente através do CPF para campo VALOR
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para VALOR
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E valor alterado com sucesso


    #Regra: Deve retornar HTTP Status 200
  @Teste
  Cenario: Deve atualizar uma simulação existente através do CPF para campo PARCELAS
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para PARCELAS
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E parcelas alterado com sucesso


    #Regra: Deve retornar HTTP Status 200
  @Teste
  Cenario: Deve atualizar uma simulação existente através do CPF para campo SEGURO
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para SEGURO
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E seguro alterado com sucesso


    #Regra: Deve retornar HTTP Status 404
  @Teste
  Cenario: Deve retornar status de erro para simulação não encontrada
    Dado preciso atualizar o cadastro de uma simulação para CPF não cadastrado
    E inserir dados a serem atualizados para CPF não existente
    Quando finalizar alteracões de informações
    Então deve receber o status code 404
    E informação de erro para CPF

    #Regra: Texto informando o CPF // formato inválido 999.999.999-99
  @Teste
  Cenario: Validar formato de CPF informado para atualização de simulação
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para CPF formato
    E finalizar alteracões de informações
    E deve receber o status code 400

    #Regra: Texto informado um e-mail válido
  @Teste
  Cenario: Validar formato de EMAIL informado para atualização de simulação
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para EMAIL em formato invalido
      | EMAIL   |
      | <email.com> |
    E finalizar alteracões de informações
    Então deve receber o status code 400
    E status de erro para EMAIL "não é um endereço de e-mail"


    #Regra: Valor da simulação que deve ser igual ou maior que R$ 1.000 e menor ou igual que R$ 40.000
  #A API está permitido a alteração do valor para inferior a R$ 1000
  @Teste
  Cenario: Validar atualização de simulação para campo "valor" menor que R$ 1.000
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para VALOR "999"
    E finalizar alteracões de informações
    Então deve receber o status code 400
    E status de erro para campo VALOR "<any>"

#A API está permitido a alteração do valor para superior R$ 40000
  @Teste
  Cenario: Validar atualização de simulação para campo "valor" maior que R$ 40.000
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para VALOR "40001"
    E finalizar alteracões de informações
    Então deve receber o status code 400



  @Teste
  Cenario: Validar atualização de simulação para campo "valor" igual a R$ 1.000
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para VALOR "1000"
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E valor alterado com sucesso


  @Teste
  Cenario: Validar atualização de simulação para campo "valor" igual a R$ 40.000
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para VALOR "40000"
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E valor alterado com sucesso


    #Regra: Número de parcelas para pagamento que deve ser igual ou maior que 2 e menor ou igual a 48
  @Teste
  Cenario: Validar atualização de simulação para campo "parcela" menor que 2
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para PARCELA "1"
    E finalizar alteracões de informações
    Então deve receber o status code 400


# A API permitiu o alteração da parcela para superior a 48 parcelas
  @Teste
  Cenario: Validar atualização de simulação para campo "parcela" maior que 48
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para PARCELA "49"
    E finalizar alteracões de informações
    Então deve receber o status code 400


  @Teste
  Cenario: Validar atualização de simulação para campo "parcela" igual a 2
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para PARCELA "2"
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E parcelas alterado com sucesso



  @Teste
  Cenario: Validar atualização de simulação para campo "parcela" igual a R$ 48
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para PARCELA "48"
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E parcelas alterado com sucesso


    #Regra: Booleano true se com seguro e false se sem seguro
  @Teste
  Cenario: Validar atualização de simulação para campo "seguro" diferente de true ou false
    Dado que desejo realizar o cadastro de uma nova simulação
    E inserir dados para cadastro de simulação
    E finalizo envio de informações
    E deve receber o status code 201
    E realiza o cadastro com sucesso
    Quando preciso atualizar o cadastro de uma simulação para CPF informado
    E inserir dados a serem atualizados para SEGURO "false"
    E finalizar alteracões de informações
    Então deve receber o status code 200
    E seguro alterado com sucesso


    #Regra: Se o CPF não possuir uma simulação o HTTP Status 404 é retornado com a mensagem "CPF não encontrado"
  @Teste
  Cenario: Validar status de erro para alteração em simulação com CPF não encontrado
    Dado preciso atualizar o cadastro de uma simulação para CPF não cadastrado
    Quando informo um CPF não cadastrado
    Então deve receber o status code 404
    E status de erro "CPF não encontrado"


