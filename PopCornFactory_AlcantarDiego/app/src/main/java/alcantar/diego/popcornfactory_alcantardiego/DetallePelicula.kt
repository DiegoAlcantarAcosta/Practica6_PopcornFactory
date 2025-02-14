package alcantar.diego.popcornfactory_alcantardiego

import alcantar.diego.popcornfactory_alcantardiego.databinding.ActivityDetallePeliculaBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallePelicula : AppCompatActivity() {

    private lateinit var binding: ActivityDetallePeliculaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetallePeliculaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        if (bundle != null){
            binding.ivPeliculaImagen.setImageResource(bundle.getInt("header"))
            binding.tvNombrePelicula.setText(bundle.getString("titulo"))
            binding.tvPeliculaDesc.setText(bundle.getString("sinopsis"))
        }

    }
}