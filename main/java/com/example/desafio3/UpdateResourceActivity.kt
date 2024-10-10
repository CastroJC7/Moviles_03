package com.example.desafio3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.desafio3.databinding.ActivityUpdateResourceBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateResourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateResourceBinding
    private var resourceId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resourceId = intent.getStringExtra("RESOURCE_ID")

        // Cargar la materia existente
        resourceId?.let {
            getResourceById(it)
        }

        // Configurar el botón de actualización
        binding.updateButton.setOnClickListener {
            updateResource()
        }
    }

    private fun getResourceById(id: String) {
        val service = RetrofitClient.instance.create(ResourceService::class.java)
        service.getResourceById(id).enqueue(object : Callback<Materia> {
            override fun onResponse(call: Call<Materia>, response: Response<Materia>) {
                if (response.isSuccessful) {
                    response.body()?.let { resource ->
                        binding.titleEditText.setText(resource.title)
                        binding.descriptionEditText.setText(resource.description)
                        binding.urlEditText.setText(resource.url)
                        binding.typeEditText.setText(resource.type)
                    }
                } else {
                    Toast.makeText(this@UpdateResourceActivity, "Recurso no encontrado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Materia>, t: Throwable) {
                Toast.makeText(this@UpdateResourceActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun updateResource() {
        val updatedResource = Materia(
            id = resourceId ?: "",
            title = binding.titleEditText.text.toString(),
            description = binding.descriptionEditText.text.toString(),
            url = binding.urlEditText.text.toString(),
            type = binding.typeEditText.text.toString()
        )

        val service = RetrofitClient.instance.create(ResourceService::class.java)
        service.updateResource(resourceId!!, updatedResource).enqueue(object : Callback<Materia> {
            override fun onResponse(call: Call<Materia>, response: Response<Materia>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@UpdateResourceActivity, "Recurso actualizado con éxito", Toast.LENGTH_LONG).show()
                    finish() // Cierra la actividad
                }
            }

            override fun onFailure(call: Call<Materia>, t: Throwable) {
                Toast.makeText(this@UpdateResourceActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
