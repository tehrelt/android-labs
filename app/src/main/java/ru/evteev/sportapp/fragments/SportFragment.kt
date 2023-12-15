package ru.evteev.sportapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import ru.evteev.la2.R
import ru.evteev.sportapp.domain.Sport
import ru.evteev.sportapp.viewmodels.SportsListViewModel
import kotlin.properties.Delegates

private const val ARG_SPORT_ID = "id_sport";

@Suppress("DEPRECATION")
class SportFragment : Fragment() {

    private var sportId by Delegates.notNull<Int>();
    private lateinit var nameTextBox: EditText
    private lateinit var vm: SportsListViewModel
    private lateinit var item: Sport

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sport, container, false);
        vm = SportsListViewModel(requireActivity().application);
        nameTextBox = view.findViewById(R.id.sportNameText);
        vm.getSport(sportId).observe(viewLifecycleOwner, Observer {
            sport -> sport.let{
                item = sport;
                nameTextBox.setText(item.name);
            }
        });
        return view;
    }

    companion object {
        fun newInstance(sportId: Int): SportFragment {
            val inst = SportFragment();
            inst.sportId = sportId
            return inst;
        }
    }
}