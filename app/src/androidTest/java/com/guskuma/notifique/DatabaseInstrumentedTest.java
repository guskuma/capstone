package com.guskuma.notifique;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.guskuma.notifique.data.AppDatabase;
import com.guskuma.notifique.data.AppDatabaseHelper;
import com.guskuma.notifique.data.dao.NotificacaoDAO;
import com.guskuma.notifique.data.model.Notificacao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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

            Notificacao n = AppDatabaseHelper.createNotificacao(new Random().nextInt());
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
}
