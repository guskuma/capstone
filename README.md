<h1>Notifique App (free and paid versions)</h1>
<h2>Build/Run configuration</h2>
Add following files to run app and gce backend:
<ul>
    <li>app/google-services.json (with app ids com.guskuma.notifique.free and com.guskuma.notifique.paid)</li>
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
    "titulo": "Ouvidoria do TJPR passa por reestruturação e amplia espaço para atendimento",<br>
	"conteudo_informacao" : "<p style='text-align: justify;'><span style='font-size:14px;'>A Ouvidoria-Geral do Tribunal de Justiça do Paraná (TJPR) realizou modificações em toda a sua estrutura. Desde a parte física (equipamentos e mobiliários) até o gerenciamento interno dos procedimentos foram reavaliados.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>O atendimento ao público, por exemplo, passa a funcionar em setor separado. Uma sala foi destinada para os contatos que acontecem via telefone (<u>0800 200 1003</u>).&nbsp; A outra vai contar com servidores que foram destinados para realizar o atendimento presencial; são quatro pessoas que ficarão à disposição dos cidadãos, dos advogados e dos servidores que necessitem de qualquer auxílio da Ouvidoria. Além disso, atenta ao caráter humanizado dos atendimentos, a Ouvidoria integrou à equipe uma assistente social.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>Segundo Roseliz Patitucci, supervisora da Ouvidoria-Geral do TJPR, a intenção desse novo trabalho é fazer uma verificação da real situação apresentada. <i>“Caso seja constatado que se trata de uma manifestação, será imediatamente encaminhada para o registro. É frequente o atendimento de pessoas que trazem suas dificuldades ou dúvidas que dispensam registro, pois grande parte dessas questões é solucionada de imediato. Há situações, ainda, em que as pessoas buscam apenas ser ouvidas”</i>, explica.</span></p><p style='text-align: justify;'><span style='font-size:14px;'><strong>Manifestações</strong></span></p><p style='text-align: justify;'><span style='font-size:14px;'>De acordo com informações da Ouvidoria do TJPR, grande parte das manifestações referem-se a prazos processuais e a serviços disponibilizados pelos cartórios do foro extrajudicial. Outros assuntos, como a dificuldade de acesso ao site do TJPR, na busca de informações e legislação, também estão entre os pontos destacados.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>Roseliz Patitucci afirma que, com as modificações efetuadas no setor, o ambiente de trabalho ficou mais leve e funcional. “Pretendemos alcançar um rendimento maior durante o expediente. As manifestações, em sua maioria, devem ser respondidas num prazo de dois dias, isso quando não há necessidade de se obter informações de outras unidades.”</span></p><p style='text-align: justify;'><span style='font-size:14px;'><p style='text-align: justify;'><span style='font-size:14px;'>Texto completo no link.</span></p>",<br>
    "conteudo_relatorio": {},<br>
    "conteudo_erro" : {},<br>
    "acao": "0",<br>
    "acao_conteudo": "https://www.tjpr.jus.br/destaques/-/asset_publisher/1lKI/content/ouvidoria-do-tjpr-passa-por-reestruturacao-e-amplia-espaco-para-atendimento/18319?inheritRedirect=false&redirect=https%3A%2F%2Fwww.tjpr.jus.br%2Fdestaques%3Fp_p_id%3D101_INSTANCE_1lKI%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-2%26p_p_col_count%3D1"<br>
}
<h3>Report body request example</h3>
{<br>
    "tipo": "1",<br>
    "titulo": "Receita Bruta - 2T18",<br>
    "conteudo_relatorio": {<br>
    	"conteudo" : "Encerramos o 2T18 com uma receita bruta consolidada de R$ 3.791,6 milhões, um incremento de 11,6% em relação 2T17. Nossas operações de drogarias registraram um crescimento de 10,6%, enquanto a 4Bio cresceu 36,3% no período. OTC foi o destaque do trimestre com crescimento de 18,8% e ganho de 1,4 ponto percentual no mix de vendas. HPC cresceu 11,0%, ganhou 0,1 ponto percentual no mix. Em contrapartida, Marca cresceu 8,9% no trimestre e perdeu 0,7 ponto percentual. É importante mencionar que a forte performance de OTC foi alavancada pelo switch de alguns medicamentos de Marca para OTC, uma migração de 0,4 ponto percentual. Os Genéricos cresceram 3,4% em receita, mas registraram um incremento de 13,7% em unidades vendidas, refletindo um investimento bem-sucedido em preços ao passo em que preservamos a margem bruta total. Obtivemos um crescimento de 2,5% nas mesmas lojas e uma contração de 1,4% nas maduras. Registramos um efeito calendário de +0,6% no trimestre, que foi neutralizado pela queda de receita de mesmo montante em função dos jogos do Brasil na Copa do Mundo.",<br>
    	"titulo_grafico" : "Mix de Vendas do Varejo",<br>
    	"detalhe_grafico" : [<br>
    		{"rotulo" : "Perfumaria", "porcentagem" : 24.8},<br>
    		{"rotulo" : "OTC", "porcentagem" : 19.8},<br>
    		{"rotulo" : "Genéricos", "porcentagem" : 11.0},<br>
    		{"rotulo" : "Marca", "porcentagem" : 44.3}<br>
    	]<br>
    },<br>
    "conteudo_informacao" : "",<br>
    "conteudo_erro" : {},<br>
    "acao": "1",<br>
    "acao_conteudo": "Encerramos o 2T18 com uma receita bruta consolidada de R$ 3.791,6 milhões, um incremento de 11,6% em relação 2T17. Nossas operações de drogarias registraram um crescimento de 10,6%, enquanto a 4Bio cresceu 36,3% no período. OTC foi o destaque do trimestre com crescimento de 18,8% e ganho de 1,4 ponto percentual no mix de vendas. HPC cresceu 11,0%, ganhou 0,1 ponto percentual no mix. Em contrapartida, Marca cresceu 8,9% no trimestre e perdeu 0,7 ponto percentual. É importante mencionar que a forte performance de OTC foi alavancada pelo switch de alguns medicamentos de Marca para OTC, uma migração de 0,4 ponto percentual. Os Genéricos cresceram 3,4% em receita, mas registraram um incremento de 13,7% em unidades vendidas, refletindo um investimento bem-sucedido em preços ao passo em que preservamos a margem bruta total. Obtivemos um crescimento de 2,5% nas mesmas lojas e uma contração de 1,4% nas maduras. Registramos um efeito calendário de +0,6% no trimestre, que foi neutralizado pela queda de receita de mesmo montante em função dos jogos do Brasil na Copa do Mundo."<br>
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
 