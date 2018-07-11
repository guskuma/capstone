package com.guskuma.notifique.data.support;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guskuma.notifique.R;
import com.guskuma.notifique.data.model.Notificacao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class NotificacoesAdapter extends RecyclerView.Adapter<NotificacoesAdapter.ViewHolder> {

    private final List<Notificacao> mValues = new ArrayList<>();
    private final NotificacaoInteractionListener mListener;

    public NotificacoesAdapter(NotificacaoInteractionListener listener) {
        mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        Timber.plant(new Timber.DebugTree());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Notificacao n = mValues.get(position);
        holder.mItem = n;

        holder.mTitulo.setText(n.titulo);

        holder.mView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onItemClick(holder.mItem);
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

    public void addItems(List<Notificacao> items){
        mValues.addAll(items);
    }

    public interface NotificacaoInteractionListener {
        void onItemClick(Notificacao item);
    }

}
