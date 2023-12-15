package ru.evteev.sportapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.evteev.la2.R
import ru.evteev.sportapp.domain.Sport
import ru.evteev.sportapp.viewmodels.SportsListViewModel

class SportListFragment : Fragment(R.layout.fragment_sport_list) {
    interface Callback {
        fun onSportSelected(sportId: Int);

    }

    private lateinit var viewModel: SportsListViewModel;
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
        viewModel = SportsListViewModel(requireActivity().application)
        val lv = view.findViewById<ListView>(R.id.list_view);

        viewModel.getSports().observe(viewLifecycleOwner, Observer<List<Sport>> { sportsList ->
            run {
                val adapter = ArrayAdapter<Sport>(requireContext(), android.R.layout.simple_list_item_1, sportsList)
                lv.adapter = adapter;
            }
        })
        val addButton = view.findViewById<FloatingActionButton>(R.id.add_button)

        addButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.flMain, SportCreateFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

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