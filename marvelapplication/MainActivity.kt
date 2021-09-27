package com.example.marvelapplication

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.marvelapplication.model.APIClient
import retrofit2.Call
import retrofit2.Response
import com.example.marvelapplication.model.Character

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = APIClient.apiService.fetchCharacters()

        client.enqueue(object : retrofit2.Callback<List<Character>> {
            override fun onResponse(
                    call: Call<List<Character>>,
                    response: Response<List<Character>>
            ) {
                if (response.isSuccessful) {
                    Log.d("characters", "" + response.body())

                    val result = response.body()
                    result?.let {
                        val adapter = MainAdapter(result, this@MainActivity)
                        val recyclerView = findViewById<RecyclerView>(R.id.rv_characters)
                        recyclerView?.layoutManager = StaggeredGridLayoutManager(
                                2, StaggeredGridLayoutManager.VERTICAL)
                        recyclerView?.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                Log.e("failed", "" + t.message)
            }
        })
    }
}