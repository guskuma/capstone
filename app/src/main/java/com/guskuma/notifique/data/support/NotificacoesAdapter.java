package com.guskuma.notifique.data.support;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.guskuma.notifique.R;
import com.guskuma.notifique.commons.ConteudoErro;
import com.guskuma.notifique.commons.ConteudoRelatorio;
import com.guskuma.notifique.commons.TipoNotificacao;
import com.guskuma.notifique.data.NotifiqueHelper;
import com.guskuma.notifique.data.model.Notificacao;
import timber.log.Timber;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NotificacoesAdapter extends RecyclerView.Adapter<NotificacoesAdapter.ViewHolder> {

    private final List<Notificacao> mValues = new ArrayList<>();
    private final NotificacaoInteractionListener mListener;
    private final Format sdf = SimpleDateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT);
    private Context mContext;

    public NotificacoesAdapter(NotificacaoInteractionListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false);
        Timber.plant(new Timber.DebugTree());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Notificacao n = mValues.get(position);
        holder.mItem = n;

        holder.mTitulo.setText(n.titulo);
        holder.mColorIndicator.setBackgroundColor(NotifiqueHelper.getColor(mContext, n.tipo));
        holder.mUltimaAtualizacao.setText(mContext.getString(R.string.em, sdf.format(n.ultima_atualizacao)));

        switch (n.tipo){
            case TipoNotificacao.INFORMACAO:
                holder.mConteudo.setText(Html.fromHtml(n.conteudo));
                break;
            case TipoNotificacao.RELATORIO:
                ConteudoRelatorio conteudoRelatorio = new Gson().fromJson(n.conteudo, ConteudoRelatorio.class);
                holder.mConteudo.setText(conteudoRelatorio.conteudo);
                break;
            case TipoNotificacao.ERRO:
                ConteudoErro conteudoErro = new Gson().fromJson(n.conteudo, ConteudoErro.class);
                holder.mConteudo.setText(mContext.getString(R.string.erro_causado_por, conteudoErro.causa_erro));
                break;
        }

        holder.mView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onItemClick(view, holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public long getItemId(int position) {
        return mValues.get(position).id;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        Notificacao mItem;

        @BindView(R.id.titulo) public TextView mTitulo;
        @BindView(R.id.conteudo) public TextView mConteudo;
        @BindView(R.id.ultimaAtualizacao) public TextView mUltimaAtualizacao;
        @BindView(R.id.colorIndicator) public View mColorIndicator;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
            mView = view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mItem.titulo + "'";
        }
    }

    public void setItems(List<Notificacao> items){
        mValues.clear();
        mValues.addAll(items);
    }

    public int addItem(Notificacao notificacao){
        mValues.add(0,notificacao);
        return 0;
    }

    public interface NotificacaoInteractionListener {
        void onItemClick(View view, Notificacao item);
    }



}
