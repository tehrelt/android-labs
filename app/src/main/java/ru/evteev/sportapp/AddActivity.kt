package ru.evteev.sportapp

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.evteev.la2.R

class AddActivity : ComponentActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_add)

        val inputForm = findViewById<EditText>(R.id.editSportName)
        val saveButton = findViewById<FloatingActionButton>(R.id.save_button)

        saveButton.setOnClickListener {
            val newSport = inputForm.text.toString()

            if (!newSport.isEmpty()) {
                intent = Intent();
                intent.putExtra("newSport", newSport.trim())
                this.setResult(1, intent);
                this.finish()
            }
        }
    }

}