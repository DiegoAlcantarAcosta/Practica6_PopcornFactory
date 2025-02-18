package alcantar.diego.popcornfactory_alcantardiego

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SeatSelection : AppCompatActivity() {
    private val reservedSeats = mutableSetOf<Int>() // Conjunto para almacenar asientos reservados

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)

        val title: TextView = findViewById(R.id.titleSeats)
        val bundle = intent.extras
        bundle?.let {
            title.text = it.getString("name")
        }

        val confirm: Button = findViewById(R.id.confirm_button)

        val row1: RadioGroup = findViewById(R.id.row1)
        val row2: RadioGroup = findViewById(R.id.row2)
        val row3: RadioGroup = findViewById(R.id.row3)
        val row4: RadioGroup = findViewById(R.id.row4)

        row1.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row1.check(checkedId)
            }
        }

        row2.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row2.check(checkedId)
            }
        }

        row3.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()

                row3.check(checkedId)
            }
        }

        row4.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()

                row4.check(checkedId)
            }
        }

        val seatGroups = listOf(row1, row2, row3, row4)

        confirm.setOnClickListener {
            val selectedSeat = getSelectedSeat(seatGroups)
            if (selectedSeat != -1 && !reservedSeats.contains(selectedSeat)) {
                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("seat_id", selectedSeat)
                startActivityForResult(intent, 100) // Esperamos la respuesta
            } else {
                Toast.makeText(this, "Please select a valid seat", Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun getSelectedSeat(groups: List<RadioGroup>): Int {
        for (group in groups) {
            val selectedId = group.checkedRadioButtonId
            if (selectedId != -1) return selectedId
        }
        return -1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            val reservedSeat = data?.getIntExtra("reserved_seat", -1) ?: -1
            if (reservedSeat != -1) {
                reservedSeats.add(reservedSeat)
                disableSeat(reservedSeat)
            }
        }
    }

    private fun disableSeat(seatId: Int) {
        val seat: RadioButton? = findViewById(seatId)
        seat?.isEnabled = false
        seat?.alpha = 0.5f
        seat?.setButtonDrawable(R.drawable.radio_disabled)

    }
}