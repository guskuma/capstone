<h1>Notifique App (versões pagas e free)</h1>
<h2>Configurações</h2>
Adicionar o seguinte arquivo para rodar o backend:
<ul>
    <li>gce/src/main/webapp/WEB-INF/serviceAccountKey.json</li>
</ul>
O arquivo será enviado separadamente já que contem API keys e pode gerar custos no Google Cloud Engine.

<h2>Chamandas API</h2>
O módulo de backend GCE é uma API REST que usa a API do Google Engine Endpoints para se comunicar com o Firebase e usar a API do FCM. O backend pode ser iniciado usando a tarefa gradle ":gce:appengineStart". 
As chamadas à API devem ser feitas utilizando POST HTTP com "Content-Type = 'Application/JSON'" para <u><i>{server-domain}/_ah/api/messages/v1/sendToTopic</i></u>
Para simplificar os testes do APP já adicionei o payload das mensagens nas seguintes URLS (para os 3 tipos de notificações disponíveis no APP):
<ul>
    <li>{server-domain}/_ah/api/messages/v1/information</li>
    <li>{server-domain}/_ah/api/messages/v1/report</li>
    <li>{server-domain}/_ah/api/messages/v1/warning</li>
</ul>
*Existe um backend rodando em <i>https://capstone-project-c681e.appspot.com</i> e pode ser utilizado para testes*

<h2>Activity de testes</h2>
Foi incluída uma Activity para testes de chamada à API, que pode ser acessada no menu da Activity principal. 
Lá podem ser selecionados os 3 tipos de notificações da aplicação e a edição do JSON a ser enviado também é possível 
(caso queira testar e experimentar).
As chamadas a partir do APP estão apontando para o backend hospedado no GCE. 
Se quiser apontar para o backend rodando em sua máquina, deve-se alterar a variável BASE_URL da classe GCEAPIService 
(lembrar de desativar o firewall da máquina onde o backend está rodando para aceitar as conexões vindo do app 
e usar o IP externo da máquina).