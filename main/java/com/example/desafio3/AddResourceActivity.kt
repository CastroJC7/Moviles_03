package com.example.desafio3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio3.databinding.ActivityAddResourceBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddResourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddResourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Agregar un nueva materia
        binding.addButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()
            val url = binding.urlEditText.text.toString()
            val type = binding.typeEditText.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && url.isNotEmpty() && type.isNotEmpty()) {
                val newResource = Materia(id = "", title = title, description = description, url = url, type = type)
                createResource(newResource)
            } else {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createResource(recurso: Materia) {
        val service = RetrofitClient.instance.create(ResourceService::class.java)
        service.createResource(recurso).enqueue(object : Callback<Materia> {
            override fun onResponse(call: Call<Materia>, response: Response<Materia>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@AddResourceActivity, "Recurso agregado con éxito", Toast.LENGTH_LONG).show()
                    finish() // Cierra la actividad después de agregar el recurso
                } else {
                    Toast.makeText(this@AddResourceActivity, "Error al agregar el recurso", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Materia>, t: Throwable) {
                Toast.makeText(this@AddResourceActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
