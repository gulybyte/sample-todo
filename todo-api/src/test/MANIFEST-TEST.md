# Manifesto sobre os Testes da API nesta Aplicação.
### Testes Unitários:
 - Os testes de **controller não serão realizados**
> A **lógica de negócio já é testada** pelos testes unitários do **service**. Restando testar no controller as entradas, porém não é possível validar as entradas do controller adequadamente, já que a maioria das **validações de entrada são feitas usando jakarta.validation**, que não é acionado corretamente quando usamos mocks. Portanto, a validação das entradas e os retornos em todos os endpoints são tratados pelos **testes de integração**.

 - Os testes do **service** garantem apenas o sucesso das regras/lógica de negócio.
 > **Não é necessário prever erros na lógica**, pois, idealmente, o código seria refatorado nesses casos. Os erros relacionados à lógica estão mais relacionados às entradas, e isso é garantido pelos testes de integração. A função então é garantir que as regras de negócio **continuem a funcionar** conforme o esperado

 - Os testes do **repository** garantem o sucesso e possíveis falhas nas consultas (querys) específicas do repository.

### Testes de Integração:
 - Os testes de **integration** (IT) garantem a validação das entradas e dos retornos de cada endpoint.
> Eles verificam se o tipo de retorno (sucesso ou falha prevista) corresponde à entrada esperada.
