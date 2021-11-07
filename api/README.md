<h1>Configurando e Startando</h1>
<h2>Para seguir deve-se primeiramente criar o bando de dados, para isso ja se tem a query no README do diretorio inicial deste repositorio</h4>
<h2>dentro do projeto em src/main/resources abra o properties e configure conforme seu banco de dados</h2>
<h2>Para dar start, vc muito provavelmente ja tem uma IDE instalada, senao tiver irei ensinar startar sem IDE</h2>
<h3>COM IDE(acho que nem precisava esplicar, mas to deixando aqui caso eu mesmo esqueca): dentro da ide va em File> import> Existing Maven Project> 
  caminho da api do projeto clonado ou baixado> Finish> ai e so abri o projeto e na classe Application da start</h3>
<h3>SEM IDE: abra o terminal e va ate o caminho da api do projeto clonado ou baixado, verifique se o maven esta instalado corretamente com este comando: mvn -v , se estiver 
  correto de o comando: mvn package, vai demorar um tempinho mais e so esperar, quando acabar ainda no terminal de o comando: cd target , e agora de comando: java -jar todo-0.0.1-SNAPSHOT.jar , 
  e pronto</h3>
