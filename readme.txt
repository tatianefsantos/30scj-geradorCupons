FIAP 30SCJ - Frameworks

1. O sistema necessita do ActiveMQ para rodar.

2. Executar o maven install para compilar o projeto.

3. Iniciar um banco de dados MYSQL.

4. Editar o arquivo application.properties com o usuário de senha do seu DB.

5. Criar uma base de dados chamada fiaproupas.

6. Fazer o start da aplicação via spring, pode ser utilizado o seguinte comando: mvn spring-boot:run.

7. Com a aplicação no ar:

	7.1. chamar o endpoint '/populadb' para popular o banco de dados


8. Crie em c: um diretório chamado 'cupons'

9. para produzir uma nova nota fiscal chamar o endpoint '/cupom/{id}', onde {id} é o número do pedido na faixa
	de 1 até 100. {id} igual a -1 (menos um) gera todas os pedidos referentes ao mês de Fevereiro de 2018.