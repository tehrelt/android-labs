package ru.evteev.sportapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import ru.evteev.la2.R

private const val ARG_SPORT_ID = "id_sport";

@Suppress("DEPRECATION")
class SportFragment : Fragment() {

    private lateinit var nameTextBox: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sportId: Int = arguments?.getSerializable(ARG_SPORT_ID) as Int;
    }

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
        fun newInstance(sportId: Int): SportFragment {
            val args = Bundle().apply {
                putSerializable(ARG_SPORT_ID, sportId);
            }
            return SportFragment().apply {
                arguments = args;
            }
        }
    }
}