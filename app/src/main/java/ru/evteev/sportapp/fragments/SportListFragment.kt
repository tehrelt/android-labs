package ru.evteev.sportapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.evteev.la2.R

/**
 * A simple [Fragment] subclass.
 * Use the [SportListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SportListFragment : Fragment(R.layout.fragment_sport_list) {

    interface Callback {
        fun onSportSelected(sportId: Int);
    }

    private var callback: Callback? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callback?
    }

    override fun onDetach() {
        super.onDetach()
        callback = null;
    }

    companion object {
        fun newInstance(): SportListFragment {
            return SportListFragment();
        }
    }
}