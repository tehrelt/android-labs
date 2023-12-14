package ru.evteev.sportapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import ru.evteev.la2.R

class SportFragment : Fragment() {

    private lateinit var nameTextBox: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sport, container, false);
        nameTextBox = view.findViewById(R.id.sportNameText);
        return view;
    }

    companion object {
        fun newInstance(): SportFragment {
            return SportFragment();
        }
    }
}