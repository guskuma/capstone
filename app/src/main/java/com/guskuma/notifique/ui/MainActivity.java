package com.guskuma.notifique.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.firebase.messaging.FirebaseMessaging;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.TipoNotificacao;
import com.guskuma.notifique.data.NotifiqueHelper;
import com.guskuma.notifique.data.model.Notificacao;
import com.guskuma.notifique.data.support.NotificacoesAdapter;
import com.guskuma.notifique.service.MyFirebaseMessagingService;
import org.parceler.Parcels;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements NotificacoesAdapter.NotificacaoInteractionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @Nullable
    @BindView(R.id.detailFragmentPlaceHolder)
    FrameLayout mDetailFragmentPlaceholder;

    @Nullable
    @BindView(R.id.noFragment)
    TextView mNoFramentSelected;

    Fragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Timber.plant(new Timber.DebugTree());

        setSupportActionBar(toolbar);

        createNotificationChannel();

        FirebaseMessaging.getInstance().subscribeToTopic("all");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(MyFirebaseMessagingService.NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private boolean isBigScreen() {
        return (mDetailFragmentPlaceholder != null);
    }

    @Override
    public void onItemClick(final View clickedView, final Notificacao item) {
        Timber.d("Item clicado: %s", item.titulo);

        if (isBigScreen()) {

            //Verifica o tipo da notificação e aí gera o fragment adequado
            switch (item.tipo) {
                case TipoNotificacao.INFORMACAO:
                    mDetailFragment = DetailInformacaoFragment.newInstance(item);
                    break;
                case TipoNotificacao.RELATORIO:
                    mDetailFragment = DetailRelatorioFragment.newInstance(item);
                    break;
                case TipoNotificacao.ERRO:
                    mDetailFragment = DetailErroFragment.newInstance(item);
                    break;
                default:
                    throw new Resources.NotFoundException("TipoNotificacao desconhecido");
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.detailFragmentPlaceHolder, mDetailFragment).commit();

            mFab.setOnClickListener(view -> {
                Intent actionIntent = NotifiqueHelper.getActionIntent(item.acao, item.acao_conteudo);
                startActivity(Intent.createChooser(actionIntent, getResources().getText(R.string.intent_chooser)));
            });
            mFab.setImageDrawable(NotifiqueHelper.getDrawable(this, item.acao));
            mFab.setVisibility(View.VISIBLE);

            mNoFramentSelected.setVisibility(View.GONE);

        } else {
            Intent i = new Intent(this, DetailActivity.class);
            i.putExtra(DetailActivity.ARG_NOTIFICACAO, Parcels.wrap(item));

            Pair<View, String> p1 = Pair.create(clickedView, getResources().getString(R.string.transition_name_titulo));
            Pair<View, String> p2 = Pair.create(clickedView, getResources().getString(R.string.transition_name_conteudo));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2);
            startActivity(i, options.toBundle());
        }
    }
}
