package com.example.simpleretrofitapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.simpleretrofitapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder


const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getItemData()
    }

    private fun getItemData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<DataItem>?> {
            override fun onFailure(call: Call<List<DataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }

            override fun onResponse(
                call: Call<List<DataItem>?>,
                response: Response<List<DataItem>?>
            ) {
                val responseBody = response.body()!!
                val stringBuilder = StringBuilder()

                for (Data in responseBody) {
                    stringBuilder.append(Data.id)
                    stringBuilder.append(" - ")
                    stringBuilder.append(Data.title)
                    stringBuilder.append("\n")
                }

                MainActivityTextView.text = stringBuilder
            }
        })
    }
}