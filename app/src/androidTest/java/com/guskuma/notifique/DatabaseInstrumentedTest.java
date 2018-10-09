package com.guskuma.notifique;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.guskuma.notifique.commons.TipoAcao;
import com.guskuma.notifique.commons.TipoNotificacao;
import com.guskuma.notifique.data.AppDatabase;
import com.guskuma.notifique.data.dao.NotificacaoDAO;
import com.guskuma.notifique.data.model.Notificacao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
public class DatabaseInstrumentedTest {

    private NotificacaoDAO mNotificacaoDAO;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mNotificacaoDAO = mDb.notificacaoDAO();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void writeAndRead() {

        int i = 0;
        int numReg = new Random().nextInt(20);
        List<Notificacao> notificacoes;

        while (i < numReg){

            Notificacao n = createRandomNotificacao(new Random().nextInt());
            mNotificacaoDAO.insert(n);

            i++;
        }

        notificacoes = mNotificacaoDAO.getAll();
        assertThat(notificacoes, notNullValue());
        assertThat(notificacoes.size(),equalTo(numReg));

        mNotificacaoDAO.delete(notificacoes.get(numReg-1));
        notificacoes = mNotificacaoDAO.getAll();
        assertThat(notificacoes.size(), equalTo(numReg-1));

        notificacoes.parallelStream().forEach(n -> mNotificacaoDAO.delete(n));
        assertThat(mNotificacaoDAO.getAll().size(), equalTo(0));
    }

    private static Notificacao createRandomNotificacao(int id) {

        int tipoNotificacao = new Random().nextInt(3);
        int tipoAcao = new Random().nextInt(3);

        List<String> conteudos = new ArrayList<>();
        conteudos.add("Mauris vel arcu vel ex mollis iaculis. Suspendisse potenti. Phasellus in ex enim. In pellentesque ante in nulla aliquet tempus. Nunc sed ipsum metus. Curabitur vitae eros non libero mollis iaculis. Proin tristique eleifend egestas. Donec justo mauris, tempor in sem a, imperdiet suscipit dui. Pellentesque rhoncus imperdiet arcu quis dapibus. Maecenas dolor leo, imperdiet nec aliquet sit amet, gravida at mi. Nullam tincidunt mauris ac nunc interdum, eu volutpat velit aliquam. ");
        conteudos.add("Nunc porttitor sollicitudin ligula quis gravida. Aliquam lacus magna, suscipit a porttitor vitae, iaculis vitae felis. Sed mollis dui nec lobortis vestibulum. Maecenas velit urna, tincidunt eu justo vitae, tristique pretium purus. Etiam id lobortis diam. Vestibulum accumsan ornare nisl, facilisis scelerisque est laoreet eget. Proin condimentum eros est, vel tempus arcu porttitor vitae. Duis rhoncus, nisl ac tincidunt hendrerit, nisl odio fringilla elit, sed bibendum sem ex tincidunt magna.");
        conteudos.add("Morbi porta, neque a accumsan commodo, ipsum massa sodales ligula, cursus sollicitudin ligula nisi eget dolor. Aenean dapibus dui quis hendrerit luctus. Duis quis ante non lorem semper maximus.");
        conteudos.add("Phasellus mi tellus, vehicula feugiat dictum ac, faucibus id orci. Morbi interdum lectus a mauris commodo dignissim. Nam malesuada velit ut convallis iaculis. Phasellus tristique feugiat mi laoreet condimentum. Quisque euismod, mi eu iaculis fermentum, felis dolor iaculis sapien, eget tristique dolor lorem at mi.");
        conteudos.add("Fusce nec neque hendrerit, congue augue ut, tempus magna. Mauris vestibulum eget tortor ac consequat. Duis volutpat tincidunt pellentesque. Praesent id metus vitae nunc ultricies mollis. Maecenas non felis eros. Aenean sem leo, auctor et elit faucibus, porta posuere est. Pellentesque sodales ullamcorper metus, id sodales neque luctus ut.");
        conteudos.add("Nunc at est lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean lacinia convallis maximus. Aliquam nisi dui, commodo sed tempor eu, finibus eget neque. Nullam in dolor ornare risus vestibulum fringilla in in erat. Duis nisl mauris, aliquet non leo non, consequat tempus lacus. Vivamus eget lorem ligula.");
        conteudos.add("Donec gravida et sem vitae gravida. Maecenas mollis efficitur tellus, vitae consequat neque tempus quis. Vestibulum condimentum dapibus mauris eget hendrerit.");
        conteudos.add("Etiam id lobortis diam. Vestibulum accumsan ornare nisl, facilisis scelerisque est laoreet eget. Proin condimentum eros est, vel tempus arcu porttitor vitae. Duis rhoncus, nisl ac tincidunt hendrerit, nisl odio fringilla elit, sed bibendum sem ex tincidunt magna. Morbi porta, neque a accumsan commodo, ipsum massa sodales ligula, cursus sollicitudin ligula nisi eget dolor. Aenean dapibus dui quis hendrerit luctus.");
        conteudos.add("Quisque euismod, mi eu iaculis fermentum, felis dolor iaculis sapien, eget tristique dolor lorem at mi. Fusce nec neque hendrerit, congue augue ut, tempus magna.");

        Notificacao n = new Notificacao();
        n.remote_id = id;
        n.tipo = tipoNotificacao;
        n.titulo = TipoNotificacao.getDescricao(tipoNotificacao) + " " + id;
        n.conteudo = conteudos.get(new Random().nextInt(conteudos.size()));
        n.acao = tipoAcao;
        n.acao_conteudo = getConteudoAcao(tipoAcao);
        n.lida = false;
        n.fixa = false;
        n.ultima_atualizacao = new Date();
        return n;
    }

    private static String getConteudoAcao(int tipoAcao) {
        switch (tipoAcao){
            case TipoAcao.ABRIR_LINK:
                return "http://www.notifique.com";
            case TipoAcao.COMPARTILHAR:
                return "";
            case TipoAcao.LIGAR:
                return "+5541992460123";
            default:
                return "";
        }
    }
}
