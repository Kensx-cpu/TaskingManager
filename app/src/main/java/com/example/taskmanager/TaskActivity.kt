package com.example.taskmanager

import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val tareas = mutableListOf<Tarea>()
    private lateinit var adapter: TareaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        val etTask = findViewById<EditText>(R.id.etTask)
        val spinner = findViewById<Spinner>(R.id.spinnerCategory)
        val rbUrgente = findViewById<RadioButton>(R.id.rbUrgente)
        val checkbox = findViewById<CheckBox>(R.id.checkboxHecho)
        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val btnAgregar = findViewById<Button>(R.id.btnAgregar)

        // Spinner data
        val categorias = arrayOf("Trabajo", "Personal", "Estudio")
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categorias)

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerTasks)
        adapter = TareaAdapter(tareas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnAgregar.setOnClickListener {
            val nombre = etTask.text.toString()
            val categoria = spinner.selectedItem.toString()
            val prioridad = if (rbUrgente.isChecked) "Urgente" else "Normal"
            val completado = checkbox.isChecked
            val valoracion = ratingBar.rating

            if (nombre.isNotEmpty()) {
                val tarea = Tarea(nombre, categoria, prioridad, completado, valoracion)
                tareas.add(tarea)
                adapter.notifyItemInserted(tareas.size - 1)
                etTask.text.clear()
                checkbox.isChecked = false
                ratingBar.rating = 3f
            } else {
                Toast.makeText(this, "Ingresa un nombre de tarea", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
