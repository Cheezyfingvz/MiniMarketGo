package com.example.minimarketgo

// Importación de clases de Android para eventos, componentes de interfaz, navegación y alertas
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Clase que representa la segunda pantalla (Pantalla de Selección e Ingreso del Pedido)
class PedidoActivity : AppCompatActivity() {

    // Declaración de variables para los campos donde el usuario escribe las cantidades
    lateinit var bebida: EditText
    lateinit var pan: EditText
    lateinit var leche: EditText

    // Declaración de la variable para la casilla de selección del servicio de delivery
    lateinit var delivery: CheckBox

    // Declaración de la variable para el botón que procesa el pedido
    lateinit var boton: Button

    // Función del ciclo de vida de la Activity que se ejecuta al iniciar la pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Asocia esta clase con la interfaz gráfica en XML (activity_pedido.xml)
        setContentView(R.layout.activity_pedido)

        // Enlaza cada variable con el ID del componente correspondiente en el layout XML
        bebida = findViewById(R.id.etBebida)
        pan = findViewById(R.id.etPan)
        leche = findViewById(R.id.etLeche)
        delivery = findViewById(R.id.chkDelivery)
        boton = findViewById(R.id.btnResumen)

        // Asigna el evento del botón 'VER RESUMEN' cuando es presionado
        boton.setOnClickListener {
            // Obtiene el texto escrito por el usuario en cada campo de texto
            val textoBebida = bebida.text.toString()
            val textoPan = pan.text.toString()
            val textoLeche = leche.text.toString()

            // VALIDACIÓN 1: Comprueba que ningún campo haya quedado vacío
            if (textoBebida.isEmpty() || textoPan.isEmpty() || textoLeche.isEmpty()) {
                // Muestra un mensaje flotante notificando al usuario que faltan campos por llenar
                Toast.makeText(this, "Faltan datos por ingresar", Toast.LENGTH_SHORT).show()
            } else {
                // Convierte las cadenas de texto a enteros (Int) para realizar los cálculos matemáticos
                val cantidadBebida = textoBebida.toInt()
                val cantidadPan = textoPan.toInt()
                val cantidadLeche = textoLeche.toInt()

                // VALIDACIÓN 2: Evita el ingreso de cantidades negativas
                if (cantidadBebida < 0 || cantidadPan < 0 || cantidadLeche < 0) {
                    Toast.makeText(this, "Las cantidades no pueden ser negativas", Toast.LENGTH_SHORT).show()
                } 
                // VALIDACIÓN 3: Exige que el usuario pida al menos un producto (no permite todo en cero)
                else if (cantidadBebida == 0 && cantidadPan == 0 && cantidadLeche == 0) {
                    Toast.makeText(this, "Debe seleccionar al menos un producto", Toast.LENGTH_SHORT).show()
                } 
                // Si los datos ingresados son válidos, realiza las operaciones numéricas
                else {
                    // Cálculo del subtotal de cada producto (Cantidad solicitada × Precio unitario)
                    val subtotalBebida = cantidadBebida * 1500
                    val subtotalPan = cantidadPan * 1200
                    val subtotalLeche = cantidadLeche * 1300

                    // Cálculo del subtotal acumulado sumando los subtotales de cada producto
                    val subtotal = subtotalBebida + subtotalPan + subtotalLeche

                    // Cálculo del IVA correspondiente al 19% del subtotal general
                    val iva = subtotal * 19 / 100

                    // Variable para definir el costo adicional de envío
                    var costoDelivery = 0

                    // Si el usuario marcó el CheckBox de delivery, se suma el costo de $2.500
                    if (delivery.isChecked) {
                        costoDelivery = 2500
                    }

                    // Cálculo del Total final a pagar: Subtotal + IVA + Costo de Delivery
                    val total = subtotal + iva + costoDelivery

                    // Prepara la navegación hacia la tercera pantalla (ResumenActivity)
                    val intento = Intent(this, ResumenActivity::class.java)

                    // Empaqueta los datos calculados mediante claves (String) y valores para enviarlos a la otra pantalla
                    intento.putExtra("bebida", cantidadBebida)
                    intento.putExtra("pan", cantidadPan)
                    intento.putExtra("leche", cantidadLeche)
                    intento.putExtra("subtotalBebida", subtotalBebida)
                    intento.putExtra("subtotalPan", subtotalPan)
                    intento.putExtra("subtotalLeche", subtotalLeche)
                    intento.putExtra("subtotal", subtotal)
                    intento.putExtra("iva", iva)
                    intento.putExtra("delivery", costoDelivery)
                    intento.putExtra("total", total)

                    // Abre la Activity de Resumen pasando la información empaquetada
                    startActivity(intento)
                }
            }
        }
    }
}
