package com.example.minimarketgo

// Importación de componentes de Android para manejo de Bundle, TextView y la clase base Activity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Clase que representa la tercera pantalla (Pantalla de Resumen / Comprobante de Compra)
class ResumenActivity : AppCompatActivity() {

    // Declaración de variables para los componentes visuales TextView de la pantalla
    lateinit var productos: TextView
    lateinit var subtotal: TextView
    lateinit var iva: TextView
    lateinit var delivery: TextView
    lateinit var total: TextView

    // Función del ciclo de vida de la Activity que se ejecuta cuando se crea esta pantalla
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Asocia esta clase con la interfaz visual definida en XML (activity_resumen.xml)
        setContentView(R.layout.activity_resumen)

        // Enlaza las variables con los controles TextView del diseño XML mediante su ID
        productos = findViewById(R.id.tvProductos)
        subtotal = findViewById(R.id.tvSubtotal)
        iva = findViewById(R.id.tvIva)
        delivery = findViewById(R.id.tvDelivery)
        total = findViewById(R.id.tvTotal)

        // Recupera el paquete de datos extras (Bundle) enviado desde PedidoActivity
        val bundle: Bundle? = intent.extras

        // Extrae los valores numéricos del Bundle. El operador '?: 0' asigna 0 como valor por defecto si el paquete es nulo
        val cantBebida = bundle?.getInt("bebida") ?: 0
        val cantPan = bundle?.getInt("pan") ?: 0
        val cantLeche = bundle?.getInt("leche") ?: 0
        val subtotalBebida = bundle?.getInt("subtotalBebida") ?: 0
        val subtotalPan = bundle?.getInt("subtotalPan") ?: 0
        val subtotalLeche = bundle?.getInt("subtotalLeche") ?: 0
        val valorSubtotal = bundle?.getInt("subtotal") ?: 0
        val valorIva = bundle?.getInt("iva") ?: 0
        val valorDelivery = bundle?.getInt("delivery") ?: 0
        val valorTotal = bundle?.getInt("total") ?: 0

        // Variable String acumuladora para armar el desglose detallado de los productos seleccionados
        var detalle = ""

        // Agrega la línea de Bebida al resumen únicamente si el usuario solicitó al menos 1 unidad
        if (cantBebida > 0) {
            detalle = detalle + "Bebida | Cantidad: " + cantBebida + " | Precio: $1.500 | Subtotal: $" + subtotalBebida + "\n"
        }

        // Agrega la línea de Pan al resumen únicamente si el usuario solicitó al menos 1 unidad
        if (cantPan > 0) {
            detalle = detalle + "Pan | Cantidad: " + cantPan + " | Precio: $1.200 | Subtotal: $" + subtotalPan + "\n"
        }

        // Agrega la línea de Leche al resumen únicamente si el usuario solicitó al menos 1 unidad
        if (cantLeche > 0) {
            detalle = detalle + "Leche | Cantidad: " + cantLeche + " | Precio: $1.300 | Subtotal: $" + subtotalLeche
        }

        // Despliega la información calculada e histórica en las etiquetas TextView de la interfaz
        productos.text = detalle
        subtotal.text = "Subtotal: $" + valorSubtotal
        iva.text = "IVA 19%: $" + valorIva
        delivery.text = "Delivery: $" + valorDelivery
        total.text = "TOTAL: $" + valorTotal
    }
}
