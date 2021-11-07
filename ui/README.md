<h3>o src e apenas o nosso codico fonte, mais sera presico aqui instalar o projeto, siga os passos de auxilio</h3>

<h4>Passo 1: abra o terminal no diretorio ui</h4>
cd caminho-ate-onde-esta-o-projeto/api-gerenciamento-de-tarefas/ui
<h4>Passo 2: estando no diretorio ui crie o projeto</h4>
<p>ng new angular-frontend</p>
na primeira pergunta: Would you like to add Angular routing? de um s ou y em ingles
apos isso aparecera um tabela de estilos para serem configurados no projeto, escolha a opção CSS
<p>O processo agora provavelmente vai demorar um pouco, entao espere ate o fim<p>
<h4>Passo 3: ainda no terminal entre no projeto angular que acaba de criar</h4>
cd angular-frontend
<h4>Passo 4: estando dentro do projeto angular instale o Bootstrap</h4>
npm install --save bootstrap
<h4>Passo 5: configure o bootstrap</h4>
<p>no projeto abra o arquivo angular.json e no primeiro styles adicione: "./node_modules/bootstrap/dist/css/bootstrap.min.css"</p>
<p>vai ficar tipo assim: "styles": [ <br>
              "src/styles.css", <br>
              "./node_modules/bootstrap/dist/css/bootstrap.min.css" <br>
            ],</p>
            
<h4>Passo 6: Delete o src do seu projeto angular e substitua por este que esta no aqui no github</h4>

<h4>7º e ultimo passo e so dar start</h4>
para isso no terminal estando dentro do projeto apenas escreva o comando 'ng serve', se seguiu os passos corretamente ele ira demorar um pouquinho mas ja ira startar, lembrando que pra funcionar corretamente a api também tem que estar "startada" e funcionando corretamente conectada no banco de dados, se nao tem certeza ou não sabe como dar start na api, la dentro do diretorio da api tem um README ensinando
