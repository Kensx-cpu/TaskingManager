package com.example.taskmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TareaAdapter(
    private val listaTareas: MutableList<Tarea>
) : RecyclerView.Adapter<TareaAdapter.TareaViewHolder>() {

    class TareaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombre)
        val tvCategoria: TextView = view.findViewById(R.id.tvCategoria)
        val tvPrioridad: TextView = view.findViewById(R.id.tvPrioridad)
        val tvEstado: TextView = view.findViewById(R.id.tvEstado)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBarItem)
        val checkCompletar: CheckBox = view.findViewById(R.id.checkCompletar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        val tarea = listaTareas[position]
        holder.tvNombre.text = tarea.nombre
        holder.tvCategoria.text = "Categoría: ${tarea.categoria}"
        holder.tvPrioridad.text = "Prioridad: ${tarea.prioridad}"
        holder.tvEstado.text = if (tarea.completado) "Estado: Completada" else "Estado: Pendiente"
        holder.ratingBar.rating = tarea.valoracion
        holder.checkCompletar.isChecked = tarea.completado

        // 👉 Cambiar estado al marcar o desmarcar
        holder.checkCompletar.setOnCheckedChangeListener { _, isChecked ->
            tarea.completado = isChecked
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int = listaTareas.size
}
