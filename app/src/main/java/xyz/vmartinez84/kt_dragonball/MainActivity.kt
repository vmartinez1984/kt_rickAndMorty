package xyz.vmartinez84.kt_dragonball

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import xyz.vmartinez84.kt_dragonball.models.Personaje
import xyz.vmartinez84.kt_dragonball.services.ApiCallback
import xyz.vmartinez84.kt_dragonball.services.PersonajeService

class MainActivity : AppCompatActivity() {
    lateinit var service: PersonajeService
    lateinit var imageView: ImageView
    lateinit var textView: TextView
    var id = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imageView = findViewById<ImageView>(R.id.imageView)
        textView = findViewById<TextView>(R.id.textView)
        
        service = PersonajeService()
    }
    
    fun Adelante(view: View){
        if(id == 58) {
            id = 1
        }else{
            id++
        }
        service.obtener(id, object : ApiCallback<Personaje> {
            override fun onSuccess(personaje: Personaje) {
                textView.setText(personaje.name)
                Glide.with(this@MainActivity).load(personaje.image).into(imageView)
            }

            override fun onError(error: String) {
                Log.e("Error", error)
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun Atras(view: View){
        if(id == 1) {
            id = 58
        }else{
            id--
        }
        service.obtener(id, object : ApiCallback<Personaje> {
            override fun onSuccess(personaje: Personaje) {
                textView.setText(personaje.name)
                Glide.with(this@MainActivity).load(personaje.image).into(imageView)
            }

            override fun onError(error: String) {
                Log.e("Error", error)
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_LONG).show()
            }
        })
    }
}