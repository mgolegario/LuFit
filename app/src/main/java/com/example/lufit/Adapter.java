package com.example.lufit;

import static com.example.lufit.RvClass.LAYOUT_DOIS;
import static com.example.lufit.RvClass.LAYOUT_UM;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

    public class Adapter extends RecyclerView.Adapter {


        List<RvClass> list;
        Context context;

        public Adapter(List<RvClass> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getItemViewType(int position) {
           switch (list.get(position).getViewType()){
               case 1:
                       return LAYOUT_UM;
               case 2:
                   return LAYOUT_DOIS;

               default:
                   return -1;
           }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            switch (viewType){
                case LAYOUT_UM:
                    View layoutUm = LayoutInflater.from(context).inflate(R.layout.usuario_mensagem_layout, parent, false);
                    return new UsuarioMensagemViewHolder(layoutUm);

                case LAYOUT_DOIS:
                    View layoutDois = LayoutInflater.from(context).inflate(R.layout.lufit_mensagem_layout, parent, false);
                    return new LuFitMensagemViewHolder(layoutDois);

                default:
                   return null;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            switch (list.get(position).getViewType()) {

                case LAYOUT_UM:
                    String um=list.get(position).getMessage();
                    ((UsuarioMensagemViewHolder) holder).setView(um);
                    break;
                case LAYOUT_DOIS:
                    String lm=list.get(position).getMessage();
                    ((LuFitMensagemViewHolder) holder).setView(lm);
                    break;
            }


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class UsuarioMensagemViewHolder extends RecyclerView.ViewHolder{

            private final TextView tv_um;

            public UsuarioMensagemViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_um = itemView.findViewById(R.id.tv_um);
            }
            private void setView(String text){

                tv_um.setText(text);
            }

        }
    }

    class LuFitMensagemViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_lm;

        public LuFitMensagemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_lm = itemView.findViewById(R.id.tv_lm);
        }
        void setView(String text){

            tv_lm.setText(text);
        }

    }


