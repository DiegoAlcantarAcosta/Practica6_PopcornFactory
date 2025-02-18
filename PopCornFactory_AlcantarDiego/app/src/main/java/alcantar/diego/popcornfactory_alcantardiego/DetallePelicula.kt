package alcantar.diego.popcornfactory_alcantardiego

import alcantar.diego.popcornfactory_alcantardiego.databinding.ActivityDetallePeliculaBinding
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetallePelicula : AppCompatActivity() {

    private lateinit var binding: ActivityDetallePeliculaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetallePeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        var ns = 0;
        var id = -1;
        var title = "";

        if (bundle != null) {

            ns = bundle.getInt("numberSeats")
            binding.ivPeliculaImagen.setImageResource(bundle.getInt("header"))
            binding.tvNombrePelicula.setText(bundle.getString("titulo"))
            binding.tvPeliculaDesc.setText(bundle.getString("sinopsis"))
            binding.seatsLeft.setText("$ns seats available")
            id = bundle.getInt("pos")
            title = bundle.getString("titulo")!!
        }

        if (ns == 0) {
            binding.buyTickets.isEnabled = false
        } else {
            binding.buyTickets.isEnabled = true
            binding.buyTickets.setOnClickListener {
                val intent: Intent = Intent(this, SeatSelection::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", title)
                this.startActivity(intent)
            }
        }

    }
}