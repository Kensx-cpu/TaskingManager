package com.example.taskmanager

data class Tarea(
    val nombre: String,
    val categoria: String,
    val prioridad: String,
    var completado: Boolean,
    val valoracion: Float
)


