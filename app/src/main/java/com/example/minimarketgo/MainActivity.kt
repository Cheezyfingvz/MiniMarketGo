package com.example.minimarketgo

// Importación de clases de Android necesarias para la interfaz y la navegación
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// Clase que representa la primera pantalla de la aplicación (Pantalla de Inicio)
class MainActivity : AppCompatActivity() {

    // Declaración de la variable para el botón de iniciar compra
    // 'lateinit' le indica a Kotlin que inicializaremos la variable más tarde en el onCreate
    lateinit var boton: Button

    // Función principal del ciclo de vida de la Activity, se ejecuta al abrir la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Conecta la lógica de esta clase Kotlin con la interfaz visual en XML (activity_main.xml)
        setContentView(R.layout.activity_main)

        // Enlaza la variable 'boton' con el botón definido en el archivo XML mediante su ID 'btnIniciar'
        boton = findViewById(R.id.btnIniciar)

        // Configura el evento que se ejecuta cuando el usuario presiona el botón 'INICIAR COMPRA'
        boton.setOnClickListener {
            // Crea un Intent (intención) para pasar de la pantalla actual (this) a la pantalla PedidoActivity
            val intento = Intent(this, PedidoActivity::class.java)
            
            // Ejecuta el paso hacia la siguiente pantalla
            startActivity(intento)
        }
    }
}
