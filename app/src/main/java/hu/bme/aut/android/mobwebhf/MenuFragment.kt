package hu.bme.aut.android.mobwebhf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.mobwebhf.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private lateinit var binding : FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBudget.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_budgetFragment)
        }
        /**
        binding.btnAnalitics.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_analyticsFragment)
        }
        */
    }
}