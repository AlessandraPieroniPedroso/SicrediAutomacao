#language:pt
  #author:alepieronif@gmail.com
Funcionalidade: Cadastrar Simulacões de acordo com parâmetros informados

    #Regra: Deve retornar HTTP Status 200
  @Teste
  Esquema do Cenario: Deve cadastrar uma nova simulação
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados corretos para cadastro de simulação
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E informações cadastradas

    Exemplos:
      |HttpCode |
      | 201     |

  #Regra: Deve retornar HTTP Status 400
  @Teste
  Esquema do Cenario: Deve cadastrar uma nova simulação com CPF nulo
    Dado que preciso cadastrar uma nova simulação
    Quando inserir conjunto de dados para campo CPF nulo
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E status de erro para campo CPF "<Mensagem>"

    Exemplos:
      |HttpCode | CPF         | Mensagem               |
      | 400     |             | CPF não pode ser vazio |

    #Erro
    #Regra: Deve retornar HTTP Status 400
  @Teste
  Esquema do Cenario: Deve cadastrar uma nova simulação com CPF já cadastrado
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com CPF já cadastrado
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E status de erro para campo CPF "<Mensagem>"

    Exemplos:
      |HttpCode | CPF         | Mensagem |
      | 400     |             |          |

   #Regra: Deve retornar HTTP Status 400
  @Teste
  Esquema do Cenario: Deve cadastrar uma nova simulação com Nome nulo
    Dado que preciso cadastrar uma nova simulação
    Quando inserir conjunto de dados para campo NOME nulo
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E status de erro para campo NOME "<Mensagem>"

    Exemplos:
      |HttpCode | Nome      | Mensagem                |
      | 400     |           | Nome não pode ser vazio |

  #Regra: Deve retornar HTTP Status 400
  @Teste
  Esquema do Cenario: Deve cadastrar uma nova simulação com Email nulo
    Dado que preciso cadastrar uma nova simulação
    Quando inserir conjunto de dados para campo EMAIL nulo
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E status de erro para campo EMAIL "<Mensagem>"

    Exemplos:
      |HttpCode | Email     | Mensagem                 |
      | 400     |           | E-mail não deve ser vazio |

  #Regra: Deve retornar HTTP Status 400
  @Teste
  Esquema do Cenario: Deve cadastrar uma nova simulação com Valor nulo
    Dado que preciso cadastrar uma nova simulação
    Quando inserir conjunto de dados para campo VALOR nulo
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E status de erro para campo VALOR "<Mensagem>"

    Exemplos:
      |HttpCode | Valor     | Mensagem                 |
      | 400     |           | Valor não pode ser vazio |

#Regra: Deve retornar HTTP Status 400
  @Teste
  Esquema do Cenario: Deve cadastrar uma nova simulação com Parcela nulo
    Dado que preciso cadastrar uma nova simulação
    Quando inserir conjunto de dados para campo PARCELA nulo
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E status de erro para campo PARCELA "<Mensagem>"

    Exemplos:
      |HttpCode | Parcela   | Mensagem                    |
      | 400     |           | Parcelas não pode ser vazio |


    #Regra: Deve retornar HTTP Status 400
  #A API está permitindo o cadastro do CPF com formato inválido
  @Teste
  Esquema do Cenario: Deve cadastrar uma nova simulação com CPF inválido "99999999999"
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro com CPF <cpf> em formato inválido
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E status de erro para campo CPF "<Mensagem>"

    Exemplos:
      |HttpCode | cpf         | Mesagem |
      | 400     | 99999999999 |         |


    #Verificar
     #Regra: Deve retornar HTTP Status 404
  @Teste
  Esquema do Cenario: Realizar cadastro de uma simulação com o Email inválido
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com Email <email> em formato inválido
    Então o retorno do status code deve ser <HttpCode>
    E status de erro para campo EMAIL "<Mensagem>"
    Exemplos:
      |HttpCode | email                 | Mensagem |
      | 404     | email.email.com.br    |          |


    #Regra: Valor da simulação que deve ser igual ou maior que R$ 1.000 e menor ou igual que R$ 40.000
#Está permintindo o cadastro do valor inferior a 1000
  @Teste
  Esquema do Cenario: Realizar o cadastro de uma simulação no campo "valor" menor que R$ 1.000
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com VALOR "<Valor>"
    E finalizar envio de informações
    Então o retorno do status code deve ser <HttpCode>
    E status de erro para campo VALOR "<Mensagem>"

    Exemplos:
      |HttpCode | Valor | Mensagem                                    |
      | 400     | 999   | Valor deve ser  igual ou maior que R$ 1.000 |

  @Teste
  Esquema do Cenario: Realizar cadastro de simulação com "valor" maior a R$ 40.000
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com VALOR "<Valor>"
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E status de erro para campo VALOR "<Mensagem>"

    Exemplos:
      |HttpCode | Valor | Mensagem                                   |
      | 400     | 40001 | Valor deve ser menor ou igual a R$ 40.000  |



  @Teste
  Esquema do Cenario: Realizar o cadastro de uma simulação com "valor" igual a R$ 1.000
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com VALOR "<Valor>"
    E finalizar envio de informações
    Então o retorno do status code deve ser <HttpCode>
    E informações cadastradas
    Exemplos:
      |HttpCode | Valor |
      | 201     | 1000  |


  @Teste
  Esquema do Cenario: Realizar cadastro de simulação com "valor" igual a R$ 40.000
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com VALOR "<Valor>"
    E finalizar envio de informações
    E o retorno do status code deve ser <HttpCode>
    E informações cadastradas

    Exemplos:
      |HttpCode | Valor |
      | 201     | 40000 |




    #Regra: Número de parcelas para pagamento que deve ser igual ou maior que 2 e menor ou igual a 48
  @Teste
  Esquema do Cenario: Realizar cadastro de uma simulação com "parcela" menor que 2
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com PARCELA "<Parcela>"
    E finalizar envio de informações
    Então o retorno do status code deve ser <HttpCode>
    E status de erro para campo PARCELA "<Mensagem>"

    Exemplos:
      |HttpCode | Parcela | Mensagem                               |
      | 400     | 1       | Parcelas deve ser igual ou maior que 2 |

#Bug, está permitindo o cadastro de parcelas maiores de 48
  @Teste
  Esquema do Cenario: Realizar cadastro de uma simulação com "parcela" maior que 48
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com PARCELA "<Parcela>"
    E finalizar envio de informações
    Então o retorno do status code deve ser <HttpCode>
    E status de erro para campo PARCELA "<Mensagem>"
    Exemplos:
      |HttpCode | Parcela | Mensagem                                 |
      | 400     | 49      | Parcelas deve ser menor ou igual a 48    |

  @Teste
  Esquema do Cenario: Realizar cadastro de uma simulação com "parcela" igual a 2
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com PARCELA "<Parcela>"
    E finalizar envio de informações
    Então o retorno do status code deve ser <HttpCode>
    E informações cadastradas

    Exemplos:
      |HttpCode | Parcela |
      | 201     | 2       |

  @Teste
  Esquema do Cenário: Realizar cadastro de uma simulação com "parcela" igual a R$ 48
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com PARCELA "<Parcela>"
    E finalizar envio de informações
    Então o retorno do status code deve ser <HttpCode>
    E informações cadastradas

    Exemplos:
      |HttpCode | Parcela |
      | 201     | 48      |



 #Regra: Booleano true se com seguro e false se sem seguro
  #Verificar
  @Teste
  Esquema do Cenário: Realizar cadastro de uma simulação com "seguro" diferente de true
    Dado que preciso cadastrar uma nova simulação
    Quando inserir conjunto de dados para campo SEGURO nulo
    E finalizar envio de informações
    Então o retorno do status code deve ser <HttpCode>
    E status de erro para campo SEGURO "<Mensagem>"

    Exemplos:
      |HttpCode | Seguro |
      | 400     |        |

    #Verificar
  @Teste
  Esquema do Cenário: Realizar cadastro de uma simulação com "seguro" true
    Dado que preciso cadastrar uma nova simulação
    Quando inserir dados para cadastro de simulação com SEGURO "<Seguro>"
    E finalizar envio de informações
    Então o retorno do status code deve ser <HttpCode>
    E informações cadastradas

    Exemplos:
      |HttpCode | Seguro |
      | 201     | True   |


