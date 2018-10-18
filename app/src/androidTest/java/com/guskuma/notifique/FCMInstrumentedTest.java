package com.guskuma.notifique;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.guskuma.notifique.backend.messages.Messages;
import com.guskuma.notifique.backend.messages.model.JsonMap;
import com.guskuma.notifique.backend.messages.model.MessagePayload;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class FCMInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        Messages.Builder builder = new Messages.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                .setRootUrl("http://localhost:8080/_ah/api/")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        Messages messagesService = builder.build();

        MessagePayload msg = new MessagePayload();
        msg.setTipo("0");
        msg.setTitulo(String.format("FCM Instrumented Test %s", new Date()));
        msg.setConteudoInformacao("<p style='text-align: justify;'><span style='font-size:14px;'>A Ouvidoria-Geral do Tribunal de Justiça do Paraná (TJPR) realizou modificações em toda a sua estrutura. Desde a parte física (equipamentos e mobiliários) até o gerenciamento interno dos procedimentos foram reavaliados.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>O atendimento ao público, por exemplo, passa a funcionar em setor separado. Uma sala foi destinada para os contatos que acontecem via telefone (<u>0800 200 1003</u>).&nbsp; A outra vai contar com servidores que foram destinados para realizar o atendimento presencial; são quatro pessoas que ficarão à disposição dos cidadãos, dos advogados e dos servidores que necessitem de qualquer auxílio da Ouvidoria. Além disso, atenta ao caráter humanizado dos atendimentos, a Ouvidoria integrou à equipe uma assistente social.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>Segundo Roseliz Patitucci, supervisora da Ouvidoria-Geral do TJPR, a intenção desse novo trabalho é fazer uma verificação da real situação apresentada. <i>“Caso seja constatado que se trata de uma manifestação, será imediatamente encaminhada para o registro. É frequente o atendimento de pessoas que trazem suas dificuldades ou dúvidas que dispensam registro, pois grande parte dessas questões é solucionada de imediato. Há situações, ainda, em que as pessoas buscam apenas ser ouvidas”</i>, explica.</span></p><p style='text-align: justify;'><span style='font-size:14px;'><strong>Manifestações</strong></span></p><p style='text-align: justify;'><span style='font-size:14px;'>De acordo com informações da Ouvidoria do TJPR, grande parte das manifestações referem-se a prazos processuais e a serviços disponibilizados pelos cartórios do foro extrajudicial. Outros assuntos, como a dificuldade de acesso ao site do TJPR, na busca de informações e legislação, também estão entre os pontos destacados.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>Roseliz Patitucci afirma que, com as modificações efetuadas no setor, o ambiente de trabalho ficou mais leve e funcional. “Pretendemos alcançar um rendimento maior durante o expediente. As manifestações, em sua maioria, devem ser respondidas num prazo de dois dias, isso quando não há necessidade de se obter informações de outras unidades.”</span></p><p style='text-align: justify;'><span style='font-size:14px;'><p style='text-align: justify;'><span style='font-size:14px;'>Texto completo no link.</span></p>");
        msg.setAcao("0");
        msg.setAcaoConteudo("https://www.google.com/");


        try {
            JsonMap result = messagesService.sendToTopic(msg).execute();
            assertThat(result, notNullValue());
            assertThat(result.get("status"), equalTo("OK"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
