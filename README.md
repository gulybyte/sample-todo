<h1>API REST MINI GERENCIADOR DE TAREFAS</h1>
<br><br>
<h2>Banco de dados: PostgreSQL</h2>
<br>
<h2>Back-End: Spring</h2>
<br>
<h2>Front-End: Angular8 e Bootstrap5</h2>
<br>
<h3>Criando Banco de Dados:</h3>
CREATE DATABASE "rest-api-angular"
  WITH OWNER = postgres<br>
       ENCODING = 'UTF8'
       TABLESPACE = pg_default<br>
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'<br>
       CONNECTION LIMIT = -1;<br><br><br>
       
<h3>Instalando o angular8</h3>
<h4>Primeiramente abra o terminal</h4>
<h4>Caso ja tenha um instalado e nao seja a versao 8 remova-os com estes 2 comandos:</h4><br>
$ npm uninstall -g @angular/cli<br>
$ npm cache clean<br>
<h4>Caso nao tenha, ou acabou de excluir com os comando acima, instale-o na versao 8</h4><br>
$ npm install -g @angular/cli@8.3.25
