# Manifesto sobre os Testes desta Aplicação.
### Testes Unitários:
 - Os testes de **controller não serão realizados**
> A **lógica de negócio é testada** pelos testes unitários do **service**. Restando testar no controller as entradas, porém não é possível validar as entradas do controller adequadamente, já que a maioria das **validações de entrada são feitas usando jakarta.validation**, que não é acionado corretamente quando usamos mocks (já que testes controller envolvem mock). Portanto, a validação das entradas e os retornos em todos os endpoints são tratados pelos **testes de integração**.

 - Os testes do **service** garantem o sucesso e exception das regras/lógica de negócio.
 > A função é garantir que as regras de negócio **continuem a funcionar** conforme o esperado, e as exception's previstas funcionem corretamente.

 - Os testes do **repository** garantem o sucesso e possíveis falhas nas querys criadas (não JpaRepository) no repository.

### Testes de Integração:
 - Os testes de **integration** (IT) garantem a validação das entradas (jakarta.validation) e dos retornos de cada endpoint.
> Eles verificam se o tipo de retorno (sucesso ou falha prevista) corresponde à entrada esperada.
