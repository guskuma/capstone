package com.guskuma.notifique.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guskuma.notifique.R;
import com.guskuma.notifique.data.restapi.GCEAPIService;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

import java.util.concurrent.TimeUnit;

public class APITestActivity extends AppCompatActivity {

    @BindView(R.id.spinner) Spinner spinner;

    @BindView(R.id.json) TextView txtJSON;

    @BindView(R.id.progressBar) ProgressBar progressBar;

    GCEAPIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apitest);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Gson gson = new GsonBuilder().setLenient().create();
        apiService = new Retrofit.Builder()
                .baseUrl(GCEAPIService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(GCEAPIService.class);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_notificacoes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String json = null;
                switch (position) {
                    case 0:
                        json = "{\n" +
                                "    \"tipo\": \"0\",\n" +
                                "    \"titulo\": \"Ouvidoria do TJPR passa por reestruturação e amplia espaço para atendimento\",\n" +
                                "\t\"conteudo_informacao\" : \"<p style='text-align: justify;'><span style='font-size:14px;'>A Ouvidoria-Geral do Tribunal de Justiça do Paraná (TJPR) realizou modificações em toda a sua estrutura. Desde a parte física (equipamentos e mobiliários) até o gerenciamento interno dos procedimentos foram reavaliados.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>O atendimento ao público, por exemplo, passa a funcionar em setor separado. Uma sala foi destinada para os contatos que acontecem via telefone (<u>0800 200 1003</u>).&nbsp; A outra vai contar com servidores que foram destinados para realizar o atendimento presencial; são quatro pessoas que ficarão à disposição dos cidadãos, dos advogados e dos servidores que necessitem de qualquer auxílio da Ouvidoria. Além disso, atenta ao caráter humanizado dos atendimentos, a Ouvidoria integrou à equipe uma assistente social.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>Segundo Roseliz Patitucci, supervisora da Ouvidoria-Geral do TJPR, a intenção desse novo trabalho é fazer uma verificação da real situação apresentada. <i>“Caso seja constatado que se trata de uma manifestação, será imediatamente encaminhada para o registro. É frequente o atendimento de pessoas que trazem suas dificuldades ou dúvidas que dispensam registro, pois grande parte dessas questões é solucionada de imediato. Há situações, ainda, em que as pessoas buscam apenas ser ouvidas”</i>, explica.</span></p><p style='text-align: justify;'><span style='font-size:14px;'><strong>Manifestações</strong></span></p><p style='text-align: justify;'><span style='font-size:14px;'>De acordo com informações da Ouvidoria do TJPR, grande parte das manifestações referem-se a prazos processuais e a serviços disponibilizados pelos cartórios do foro extrajudicial. Outros assuntos, como a dificuldade de acesso ao site do TJPR, na busca de informações e legislação, também estão entre os pontos destacados.</span></p><p style='text-align: justify;'><span style='font-size:14px;'>Roseliz Patitucci afirma que, com as modificações efetuadas no setor, o ambiente de trabalho ficou mais leve e funcional. “Pretendemos alcançar um rendimento maior durante o expediente. As manifestações, em sua maioria, devem ser respondidas num prazo de dois dias, isso quando não há necessidade de se obter informações de outras unidades.”</span></p><p style='text-align: justify;'><span style='font-size:14px;'><p style='text-align: justify;'><span style='font-size:14px;'>Texto completo no link.</span></p>\",\n" +
                                "    \"conteudo_relatorio\": {},\n" +
                                "    \"conteudo_erro\" : {},\n" +
                                "    \"acao\": \"0\",\n" +
                                "    \"acao_conteudo\": \"https://www.tjpr.jus.br/destaques/-/asset_publisher/1lKI/content/ouvidoria-do-tjpr-passa-por-reestruturacao-e-amplia-espaco-para-atendimento/18319?inheritRedirect=false&redirect=https%3A%2F%2Fwww.tjpr.jus.br%2Fdestaques%3Fp_p_id%3D101_INSTANCE_1lKI%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-2%26p_p_col_count%3D1\"\n" +
                                "}";
                        break;
                    case 1:
                        json = "{\n" +
                                "    \"tipo\": \"1\",\n" +
                                "    \"titulo\": \"Receita Bruta - 2T18\",\n" +
                                "    \"conteudo_relatorio\": {\n" +
                                "    \t\"conteudo\" : \"Encerramos o 2T18 com uma receita bruta consolidada de R$ 3.791,6 milhões, um incremento de 11,6% em relação 2T17. Nossas operações de drogarias registraram um crescimento de 10,6%, enquanto a 4Bio cresceu 36,3% no período. OTC foi o destaque do trimestre com crescimento de 18,8% e ganho de 1,4 ponto percentual no mix de vendas. HPC cresceu 11,0%, ganhou 0,1 ponto percentual no mix. Em contrapartida, Marca cresceu 8,9% no trimestre e perdeu 0,7 ponto percentual. É importante mencionar que a forte performance de OTC foi alavancada pelo switch de alguns medicamentos de Marca para OTC, uma migração de 0,4 ponto percentual. Os Genéricos cresceram 3,4% em receita, mas registraram um incremento de 13,7% em unidades vendidas, refletindo um investimento bem-sucedido em preços ao passo em que preservamos a margem bruta total. Obtivemos um crescimento de 2,5% nas mesmas lojas e uma contração de 1,4% nas maduras. Registramos um efeito calendário de +0,6% no trimestre, que foi neutralizado pela queda de receita de mesmo montante em função dos jogos do Brasil na Copa do Mundo.\",\n" +
                                "    \t\"titulo_grafico\" : \"Mix de Vendas do Varejo\",\n" +
                                "    \t\"detalhe_grafico\" : [\n" +
                                "    \t\t{\"rotulo\" : \"Perfumaria\", \"porcentagem\" : 24.8},\n" +
                                "    \t\t{\"rotulo\" : \"OTC\", \"porcentagem\" : 19.8},\n" +
                                "    \t\t{\"rotulo\" : \"Genéricos\", \"porcentagem\" : 11.0},\n" +
                                "    \t\t{\"rotulo\" : \"Marca\", \"porcentagem\" : 44.3}\n" +
                                "    \t]\n" +
                                "    },\n" +
                                "    \"conteudo_informacao\" : \"\",\n" +
                                "    \"conteudo_erro\" : {},\n" +
                                "    \"acao\": \"1\",\n" +
                                "    \"acao_conteudo\": \"Encerramos o 2T18 com uma receita bruta consolidada de R$ 3.791,6 milhões, um incremento de 11,6% em relação 2T17. Nossas operações de drogarias registraram um crescimento de 10,6%, enquanto a 4Bio cresceu 36,3% no período. OTC foi o destaque do trimestre com crescimento de 18,8% e ganho de 1,4 ponto percentual no mix de vendas. HPC cresceu 11,0%, ganhou 0,1 ponto percentual no mix. Em contrapartida, Marca cresceu 8,9% no trimestre e perdeu 0,7 ponto percentual. É importante mencionar que a forte performance de OTC foi alavancada pelo switch de alguns medicamentos de Marca para OTC, uma migração de 0,4 ponto percentual. Os Genéricos cresceram 3,4% em receita, mas registraram um incremento de 13,7% em unidades vendidas, refletindo um investimento bem-sucedido em preços ao passo em que preservamos a margem bruta total. Obtivemos um crescimento de 2,5% nas mesmas lojas e uma contração de 1,4% nas maduras. Registramos um efeito calendário de +0,6% no trimestre, que foi neutralizado pela queda de receita de mesmo montante em função dos jogos do Brasil na Copa do Mundo.\"\n" +
                                "}";
                        break;
                    case 2:
                        json = "{\n" +
                                "    \"tipo\": \"2\",\n" +
                                "    \"titulo\": \"Batch-job ENVIO-REMESSA\",\n" +
                                "    \"conteudo_relatorio\": {},\n" +
                                "    \"conteudo_informacao\" : \"\",\n" +
                                "    \"conteudo_erro\" : { \n" +
                                "    \t\"causa_erro\" : \"Timeout FTP\", \n" +
                                "    \t\"detalhe_erro\" : \"Erro ao conectar ao host 192.168.0.1 (timeout). O arquivo de remessa não pode ser entregue para processamento.\"\n" +
                                "    \t},\n" +
                                "    \"acao\": \"2\",\n" +
                                "    \"acao_conteudo\": \"+5541990120123\"\n" +
                                "}";
                        break;
                }
                txtJSON.setText(json);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtJSON.setText("");
            }
        });
    }

    @OnClick(R.id.sendButton)
    public void onSendRequest(Button button){
        progressBar.setVisibility(View.VISIBLE);
        button.setEnabled(false);
        String json = txtJSON.getText().toString();
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);
        Call<ResponseBody> call = apiService.sendMessage(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getBaseContext(), "Mensagem enviada com sucesso!", Toast.LENGTH_LONG).show();
                button.setEnabled(true);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro ao fazer envio da mensagem =(", Toast.LENGTH_LONG).show();
                t.printStackTrace();
                button.setEnabled(true);
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
