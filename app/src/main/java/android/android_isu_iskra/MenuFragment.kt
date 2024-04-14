package android.android_isu_iskra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import android.android_isu_iskra.databinding.FragmentMenuBinding
import android.widget.Toast
import androidx.fragment.app.activityViewModels

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MenuFragment : Fragment() {
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var binding: FragmentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.login.observe(viewLifecycleOwner){ l ->
            binding.shop.text = l
            Toast.makeText(requireActivity(), l, Toast.LENGTH_LONG)
                .show()
        }
       binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_promoFragment)
        }
    }


}