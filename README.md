<h1>Notifique App (free and paid versions)</h1>
<h2>Build/Run configuration</h2>
Add following files to run app and gce backend:
<ul>
    <li>app/google-services.json</li>
    <li>gce/src/main/webapp/WEB-INF/serviceAccountKey.json</li>
</ul>
Both files can be downloaded from Firebase console.
Beware all versions of app (free and paid) should be added to firebase console in order to receive FCM messages.
<h2>API calls</h2>
Notifique backend module <b>GCE</b> is a REST API that uses Google Cloud Engine Endpoints API to communicate with Firebase and use FCM API.
Calls to API shoud be done using HTTP POST with "Content-Type = 'Application/JSON'" to <u><i>http://{server-domain}/_ah/api/messages/v1/sendToTopic</i></u>
<h3>Information body request example</h3>
{<br>
     "tipo": "0",<br>
     "titulo": "Pagamentos realizados",<br>
 	"conteudo_informacao" : "Os pagamentos anuais agendados para hoje (10/10/2018) <b>foram realizados com sucesso!</b><br><i>* 306 pagamentos totalizando <u>R$ 1.203.345,76</u><i>",<br>
     "conteudo_relatorio": {},<br>
     "conteudo_erro" : {},<br>
     "acao": "0",<br>
     "acao_conteudo": "https://www.google.com.br/search?q=pagamentos"<br>
 }
<h3>Report body request example</h3>
{<br>
     "tipo": "1",<br>
     "titulo": "Perfil de vendas",<br>
     "conteudo_relatorio": {<br>
     	"conteudo" : "O detalhamento do perfil de vendas no ano corrente, como um exemplo para este aplicativo. Mauris vel arcu vel ex mollis iaculis. Suspendisse potenti. Phasellus in ex enim. In pellentesque ante in nulla aliquet tempus. Nunc sed ipsum metus. Curabitur vitae eros non libero mollis iaculis. Proin tristique eleifend egestas. Donec justo mauris, tempor in sem a, imperdiet suscipit dui. Pellentesque rhoncus imperdiet arcu quis dapibus. Maecenas dolor leo, imperdiet nec aliquet sit amet, gravida at mi. Nullam tincidunt mauris ac nunc interdum, eu volutpat velit aliquam.",<br>
     	"titulo_grafico" : "Canal de vendas 2018",<br>
     	"detalhe_grafico" : [<br>
     		{"rotulo" : "Internet", "porcentagem" : 68},<br>
     		{"rotulo" : "Loja - Shopping", "porcentagem" : 18},<br>
     		{"rotulo" : "Loja - Rua", "porcentagem" : 12},<br>
     		{"rotulo" : "Colaborador", "porcentagem" : 2}<br>
     	]<br>
     },<br>
     "conteudo_informacao" : "",<br>
     "conteudo_erro" : {},<br>
     "acao": "1",<br>
     "acao_conteudo": "O detalhamento do perfil de vendas no ano corrente, como um exemplo para este aplicativo. Mauris vel arcu vel ex mollis iaculis. Suspendisse potenti. Phasellus in ex enim. In pellentesque ante in nulla aliquet tempus. Nunc sed ipsum metus. Curabitur vitae eros non libero mollis iaculis. Proin tristique eleifend egestas. Donec justo mauris, tempor in sem a, imperdiet suscipit dui. Pellentesque rhoncus imperdiet arcu quis dapibus. Maecenas dolor leo, imperdiet nec aliquet sit amet, gravida at mi. Nullam tincidunt mauris ac nunc interdum, eu volutpat velit aliquam."<br>
 }
<h3>Warning body request example</h3>
{<br>
     "tipo": "2",<br>
     "titulo": "Batch-job ENVIO-REMESSA",<br>
     "conteudo_relatorio": {},<br>
     "conteudo_informacao" : "",<br>
     "conteudo_erro" : { "causa_erro" : "Timeout FTP", "detalhe_erro" : "Erro ao conectar FTP host 192.168.0.1 : timeout. O arquivo de remessa não pode ser entregue ao servidor para processamento."},<br>
     "acao": "2",<br>
     "acao_conteudo": "+5541990120123"<br>
 }
 