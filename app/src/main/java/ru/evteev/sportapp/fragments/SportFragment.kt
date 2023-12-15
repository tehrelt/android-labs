package ru.evteev.sportapp.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        nameTextBox = view.findViewById(R.id.sportNameText)
        vm.getSport(sportId).observe(viewLifecycleOwner, Observer {
            sport -> sport.let{
                item = sport;
                val idPlace = view.findViewById<TextView>(R.id.textView4);
                nameTextBox.setText(item.name);
                idPlace.setText("id: " + item.id);

                val updateButton = view.findViewById<FloatingActionButton>(R.id.update_button);
                val deleteButton = view.findViewById<FloatingActionButton>(R.id.delete_button);

                updateButton.setOnClickListener {
                    vm.update(item)
                    activity?.supportFragmentManager!!.beginTransaction()
                        .replace(R.id.flMain, SportListFragment())
                        .addToBackStack(null)
                        .commit()
                }

                deleteButton.setOnClickListener {
                    vm.delete(item.id)
                    activity?.supportFragmentManager!!.beginTransaction()
                        .replace(R.id.flMain, SportListFragment())
                        .commit()
                }
            }
        });

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                item.name = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                item.name = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
                item.name = s.toString()
            }
        }
        nameTextBox.addTextChangedListener(textWatcher);
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