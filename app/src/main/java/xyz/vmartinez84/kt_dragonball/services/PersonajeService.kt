package xyz.vmartinez84.kt_dragonball.services

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import xyz.vmartinez84.kt_dragonball.models.Personaje

class PersonajeService {

    var BASE_URL = "https://rickandmortyapi.com/api/"
    private val apiService: ApiService

    //contructor
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    private interface ApiService {
        @GET("character/{id}")
        fun obtener(@Path("id") id : Int ): Call<Personaje>
    }

    fun obtener(id: Int, callback: ApiCallback<Personaje>){
        val call = apiService.obtener(id)
        call.enqueue(object : Callback<Personaje> {
            override fun onResponse(call: Call<Personaje>, response: Response<Personaje>) {
                val  personaje: Personaje? = response.body()
                callback.onSuccess(personaje as Personaje)
            }

            override fun onFailure(call: Call<Personaje>, t: Throwable) {
                callback.onError(t.message?: "Error")
            }
        })
    }
}