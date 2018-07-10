package com.guskuma.notifique;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.guskuma.notifique.data.AppDatabase;
import com.guskuma.notifique.data.Notificacao;
import com.guskuma.notifique.data.TipoAcao;
import com.guskuma.notifique.data.TipoNotificacao;
import com.guskuma.notifique.data.dao.NotificacaoDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
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
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void writeAndRead() throws Exception {

        int i = 0;
        int numReg = new Random().nextInt(20);
        List<Notificacao> notificacoes = null;

        while (i < numReg){

            Notificacao n = createNotificacao(new Random().nextInt());
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

    private Notificacao createNotificacao(int id) {
        Notificacao n = new Notificacao();
        n.remote_id = id;
        n.tipo = TipoNotificacao.INFORMACAO;
        n.titulo = "Testando "+ id;
        n.conteudo = "Conteudo "+ id;
        n.acao = TipoAcao.ABRIR_LINK;
        n.acao_conteudo = "http://www.notifique.com";
        n.lida = false;
        n.fixa = false;
        n.ultima_atualizacao = new Date();
        return n;
    }
}
