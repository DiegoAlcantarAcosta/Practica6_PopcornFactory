package alcantar.diego.popcornfactory_alcantardiego

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)

        val seatInfo: TextView = findViewById(R.id.seat_info)
        val nameInput: EditText = findViewById(R.id.name_input)
        val confirmButton: Button = findViewById(R.id.final_confirm_button)

        val seatId = intent.getIntExtra("seat_id", -1)
        seatInfo.text = if (seatId != -1) "Selected Seat: $seatId" else "No seat selected"

        confirmButton.setOnClickListener {
            val userName = nameInput.text.toString().trim()

            if (userName.isNotEmpty()) {
                Toast.makeText(this, "Reservation confirmed for $userName!", Toast.LENGTH_LONG).show()

                val returnIntent = Intent()
                returnIntent.putExtra("reserved_seat", seatId)
                setResult(RESULT_OK, returnIntent)

                finish() 
            } else {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}