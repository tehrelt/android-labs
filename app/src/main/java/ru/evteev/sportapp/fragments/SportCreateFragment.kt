package ru.evteev.sportapp.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.evteev.la2.R

/**
 * A simple [Fragment] subclass.
 * Use the [SportCreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SportCreateFragment : Fragment() {

    interface CreateCallback {
        fun createSport(name: String)
    }

    private var callback: CreateCallback? = null;
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as CreateCallback?
    }

    override fun onDetach() {
        super.onDetach()
        callback = null;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sport_create, container, false)
        val inputForm = view.findViewById<EditText>(R.id.editSportName)
        val saveButton = view.findViewById<FloatingActionButton>(R.id.save_button)

        saveButton.setOnClickListener {
            val newSport = inputForm.text.toString()

            if (!newSport.isEmpty()) {
                callback?.createSport(newSport);
            }
        }

        return view
    }
}