package android.android_isu_iskra

import android.android_isu_iskra.Data.data
import android.android_isu_iskra.databinding.FragmentPromoBinding
import android.os.Bundle
import android.telecom.Call.Details
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels



/**
 * A simple [Fragment] subclass.
 * Use the [PromoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PromoFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentPromoBinding
    lateinit var expandableListView: ExpandableListView
    lateinit var expandableListAdapter: ExpandableListAdapter
    lateinit var expandableListTitle:List<String>
    var expandableListDetail: HashMap<String,List<String>> = HashMap()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPromoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        expandableListView = binding.expandableListView
        expandableListDetail = data
        expandableListTitle =ArrayList<String>(expandableListDetail.keys)
        expandableListAdapter = ExpandableListAdapter(requireActivity(),expandableListTitle,expandableListDetail)
        expandableListView.setAdapter(expandableListAdapter)
        expandableListView.setOnGroupExpandListener { groupPosition->
            Toast.makeText(requireActivity(), expandableListTitle[groupPosition], Toast.LENGTH_LONG)
                .show()
        }
        expandableListView.setOnGroupCollapseListener { groupPosition->
            Toast.makeText(requireActivity(), expandableListTitle[groupPosition], Toast.LENGTH_LONG)
                .show()
        }
        expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            Toast.makeText(requireActivity(), expandableListDetail[expandableListTitle[groupPosition]]!![childPosition], Toast.LENGTH_LONG)
                .show()
            false
        }

        viewModel.login.observe(viewLifecycleOwner){ l ->
            Toast.makeText(requireActivity(), l, Toast.LENGTH_LONG)
                .show()
        }
        /*  binding.buttonSecond.setOnClickListener {
              findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
          }*/
    }
}