package ru.evteev.sportapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.evteev.la2.R

/**
 * A simple [Fragment] subclass.
 * Use the [SportListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SportListFragment : Fragment(R.layout.fragment_sport_list) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SportListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SportListFragment().apply {

            }
    }
}