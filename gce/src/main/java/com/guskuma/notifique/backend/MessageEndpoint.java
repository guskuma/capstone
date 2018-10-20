package com.guskuma.notifique.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.guskuma.notifique.commons.ConteudoErro;
import com.guskuma.notifique.commons.ConteudoRelatorio;
import com.guskuma.notifique.commons.ConteudoRelatorioDetalheGrafico;
import com.guskuma.notifique.commons.MessagePayload;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Api(
        name = "messages",
        version = "v1",
        namespace =
        @ApiNamespace(
                ownerDomain = "backend.notifique.guskuma.com",
                ownerName = "backend.notifique.guskuma.com",
                packagePath = ""
        )
)
public class MessageEndpoint {

    public MessageEndpoint() {
        super();

        try {
            FileInputStream serviceAccount = new FileInputStream("WEB-INF/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    @ApiMethod(name = "sendToTopic")
    public Map<String, Object> sendToTopic(MessagePayload messagePayload) {

        String topic = "all";

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("data", messagePayload);

        // See documentation on defining a messagePayload payload.
        Message msg = Message.builder()
                .putAllData(messagePayload.getData())
                .setTopic(topic)
                .build();

        // Send a messagePayload to the devices subscribed to the provided topic.
        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(msg);
            returnValue.put("status", "OK");
            returnValue.put("FCM-message", response);

        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            System.out.println("Error while sending message: " + e.getMessage());
            returnValue.put("status", "ERROR");
            returnValue.put("FCM-message", e.getMessage() + " [" + e.getErrorCode() + "]");
        }
        return returnValue;
    }

    @ApiMethod(name = "information", path = "information")
    public Map<String, Object> getInfo() {
        MessagePayload msg = new MessagePayload();
        msg.tipo = ("0");
        msg.titulo = ("Ouvidoria do TJPR passa por reestruturação e amplia espaço para atendimento");
        msg.conteudo_informacao = ("<p style='text-align: justify;'><span style='font-size:14px;'>A Ouvidoria-Geral do Tribunal de Justiça do Paraná (TJPR) realizou modificações em toda a sua estrutura. Desde a parte física (equipamentos e mobiliários) até o gerenciamento interno dos procedimentos foram reavaliados.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>O atendimento ao público, por exemplo, passa a funcionar em setor separado. Uma sala foi destinada para os contatos que acontecem via telefone (<u>0800 200 1003</u>).&nbsp; A outra vai contar com servidores que foram destinados para realizar o atendimento presencial; são quatro pessoas que ficarão à disposição dos cidadãos, dos advogados e dos servidores que necessitem de qualquer auxílio da Ouvidoria. Além disso, atenta ao caráter humanizado dos atendimentos, a Ouvidoria integrou à equipe uma assistente social.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>Segundo Roseliz Patitucci, supervisora da Ouvidoria-Geral do TJPR, a intenção desse novo trabalho é fazer uma verificação da real situação apresentada. <i>“Caso seja constatado que se trata de uma manifestação, será imediatamente encaminhada para o registro. É frequente o atendimento de pessoas que trazem suas dificuldades ou dúvidas que dispensam registro, pois grande parte dessas questões é solucionada de imediato. Há situações, ainda, em que as pessoas buscam apenas ser ouvidas”</i>, explica.</span></p><p style='text-align: justify;'><span style='font-size:14px;'><strong>Manifestações</strong></span></p><p style='text-align: justify;'><span style='font-size:14px;'>De acordo com informações da Ouvidoria do TJPR, grande parte das manifestações referem-se a prazos processuais e a serviços disponibilizados pelos cartórios do foro extrajudicial. Outros assuntos, como a dificuldade de acesso ao site do TJPR, na busca de informações e legislação, também estão entre os pontos destacados.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>Roseliz Patitucci afirma que, com as modificações efetuadas no setor, o ambiente de trabalho ficou mais leve e funcional. “Pretendemos alcançar um rendimento maior durante o expediente. As manifestações, em sua maioria, devem ser respondidas num prazo de dois dias, isso quando não há necessidade de se obter informações de outras unidades.”</span></p><p style='text-align: justify;'><span style='font-size:14px;'><p style='text-align: justify;'><span style='font-size:14px;'>Texto completo no link.</span></p>");
        msg.conteudo_relatorio = null;
        msg.conteudo_erro = null;
        msg.acao = ("0");
        msg.acao_conteudo = ("https://www.tjpr.jus.br/destaques/-/asset_publisher/1lKI/content/ouvidoria-do-tjpr-passa-por-reestruturacao-e-amplia-espaco-para-atendimento/18319?inheritRedirect=false&redirect=https%3A%2F%2Fwww.tjpr.jus.br%2Fdestaques%3Fp_p_id%3D101_INSTANCE_1lKI%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-2%26p_p_col_count%3D1");
        return sendToTopic(msg);
    }

    @ApiMethod(name = "report", path = "report")
    public Map<String, Object> getReport() {
        MessagePayload msg = new MessagePayload();
        msg.tipo = ("1");
        msg.titulo = ("Receita Bruta - 2T18");
        msg.conteudo_informacao = "";

        msg.conteudo_relatorio = new ConteudoRelatorio();
        msg.conteudo_relatorio.conteudo = "Encerramos o 2T18 com uma receita bruta consolidada de R$ 3.791,6 milhões, um incremento de 11,6% em relação 2T17. Nossas operações de drogarias registraram um crescimento de 10,6%, enquanto a 4Bio cresceu 36,3% no período. OTC foi o destaque do trimestre com crescimento de 18,8% e ganho de 1,4 ponto percentual no mix de vendas. HPC cresceu 11,0%, ganhou 0,1 ponto percentual no mix. Em contrapartida, Marca cresceu 8,9% no trimestre e perdeu 0,7 ponto percentual. É importante mencionar que a forte performance de OTC foi alavancada pelo switch de alguns medicamentos de Marca para OTC, uma migração de 0,4 ponto percentual. Os Genéricos cresceram 3,4% em receita, mas registraram um incremento de 13,7% em unidades vendidas, refletindo um investimento bem-sucedido em preços ao passo em que preservamos a margem bruta total. Obtivemos um crescimento de 2,5% nas mesmas lojas e uma contração de 1,4% nas maduras. Registramos um efeito calendário de +0,6% no trimestre, que foi neutralizado pela queda de receita de mesmo montante em função dos jogos do Brasil na Copa do Mundo.";
        msg.conteudo_relatorio.detalhe_grafico = new ArrayList<>();
        msg.conteudo_relatorio.detalhe_grafico.add(new ConteudoRelatorioDetalheGrafico("Perfumaria", 24.8f));
        msg.conteudo_relatorio.detalhe_grafico.add(new ConteudoRelatorioDetalheGrafico("OTC", 19.8f));
        msg.conteudo_relatorio.detalhe_grafico.add(new ConteudoRelatorioDetalheGrafico("Genéricos", 11.0f));
        msg.conteudo_relatorio.detalhe_grafico.add(new ConteudoRelatorioDetalheGrafico("Marca", 44.3f));

        msg.conteudo_erro = null;
        msg.acao = ("1");
        msg.acao_conteudo = msg.conteudo_relatorio.conteudo;
        return sendToTopic(msg);
    }

    @ApiMethod(name = "warning", path = "warning")
    public Map<String, Object> getWarning() {
        MessagePayload msg = new MessagePayload();
        msg.tipo = ("2");
        msg.titulo = ("Batch Job \"Receber Confirmação CRA\"");
        msg.conteudo_informacao = "";
        msg.conteudo_relatorio = null;

        msg.conteudo_erro = new ConteudoErro();
        msg.conteudo_erro.causa_erro = "Could not send Message";
        msg.conteudo_erro.detalhe_erro = "Último stacktrace coletado: br.jus.tjpr.uniformizado.log.AppException: Could not send Message. at br.jus.tjpr.uniformizado.protesto.service.ComunicacaoCRAService.receberConfirmacao(ComunicacaoCRAService.java:372) at br.jus.tjpr.uniformizado.protesto.service.ComunicacaoCRAService$Proxy$_$$_WeldSubclass.receberConfirmacao(Unknown Source) at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:498) at org.jboss.weld.interceptor.proxy.SimpleInterceptionChain.interceptorChainCompleted(SimpleInterceptionChain.java:51) at org.jboss.weld.interceptor.chain.AbstractInterceptionChain.finish(AbstractInterceptionChain.java:148) at org.jboss.weld.interceptor.chain.AbstractInterceptionChain.invokeNextInterceptor(AbstractInterceptionChain.java:104) at org.jboss.weld.interceptor.proxy.InterceptorInvocationContext.proceed(InterceptorInvocationContext.java:149) at com.arjuna.ats.jta.cdi.transactional.TransactionalInterceptorBase.invokeInOurTx(TransactionalInterceptorBase.java:89) at com.arjuna.ats.jta.cdi.transactional.TransactionalInterceptorRequired.intercept(TransactionalInterceptorRequired.java:52) at sun.reflect.GeneratedMethodAccessor204.invoke(Unknown Source) at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.lang.reflect.Method.invoke(Method.java:498) at org.jboss.weld.interceptor.reader.SimpleInterceptorInvocation$SimpleMethodInvocation.invoke(SimpleInterceptorInvocation.java:74) at org.jboss.weld.interceptor.chain.AbstractInterceptionChain.invokeNext(AbstractInterceptionChain.";

        msg.acao = ("2");
        msg.acao_conteudo = "+5541901230123";
        return sendToTopic(msg);
    }

}
