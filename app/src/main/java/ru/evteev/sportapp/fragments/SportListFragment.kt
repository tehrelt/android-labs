package ru.evteev.sportapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import ru.evteev.la2.R
import ru.evteev.sportapp.domain.Sport

/**
 * A simple [Fragment] subclass.
 * Use the [SportListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SportListFragment : Fragment(R.layout.fragment_sport_list) {
    interface Callback {
        fun onSportSelected(sportId: Int);
    }

    private lateinit var arrayList: ArrayList<Sport>
    private lateinit var adapter: ArrayAdapter<Sport>


    private var callback: Callback? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sport_list, container, false);
        val list = view.findViewById<ListView>(R.id.list_view)
        arrayList = ArrayList();
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayList);
        list.adapter = adapter
        return view;
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