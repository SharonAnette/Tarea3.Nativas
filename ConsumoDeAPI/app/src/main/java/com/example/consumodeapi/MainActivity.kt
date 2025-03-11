package com.example.consumodeapi

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BookAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        searchView = findViewById(R.id.searchView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BookAdapter(emptyList())
        recyclerView.adapter = adapter

        // Cargar libros por defecto
        fetchBooks("Android")

        // Implementar pull-to-refresh correctamente
        swipeRefreshLayout.setOnRefreshListener {
            fetchBooks(searchView.query.toString())
        }

        // Escuchar cambios en la búsqueda
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    fetchBooks(query) // Buscar cuando el usuario presione "Enter"
                }
                if (query != null) {
                    if(query.isEmpty()){
                        fetchBooks("Android")
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false // No hacer búsqueda en cada letra para evitar llamadas excesivas
            }
        })
    }

    private fun fetchBooks(query: String) {
        progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            val response = ApiClient.apiService.searchBooks(query, 1)

            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false // Ahora se desactiva después de la respuesta

                if (response.isSuccessful) {
                    response.body()?.let {
                        adapter.updateBooks(it.docs)
                    }
                } else {
                    Toast.makeText(this@MainActivity, "No se encontraron resultados", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
