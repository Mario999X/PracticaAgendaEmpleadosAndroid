package com.example.agendaempleadosandroid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendaempleadosandroid.data.EmpleadoEntity;

import java.util.List;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    List<EmpleadoEntity> empleadoEntityList;
    Activity context;
    ClickListener clickListener;

    public AdapterRecyclerView(List<EmpleadoEntity> empleadoEntityList, Activity context, ClickListener clickListener) {
        this.empleadoEntityList = empleadoEntityList;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerView.ViewHolder holder, int position) {

        EmpleadoEntity item = empleadoEntityList.get(position);

        holder.textoId.setText("ID: " + item.getId());
        holder.textoNombreDepartamento.setText("Nombre: " + item.getNombre() + " | Departamento: " + item.getDepartamento());

    }

    @Override
    public int getItemCount() {
        return empleadoEntityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textoId, textoNombreDepartamento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textoId = itemView.findViewById(R.id.textoId);
            textoNombreDepartamento = itemView.findViewById(R.id.textoNombreDepartamento);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition());
        }
    }
}
