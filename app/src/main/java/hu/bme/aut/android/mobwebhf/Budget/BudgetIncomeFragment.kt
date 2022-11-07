package hu.bme.aut.android.mobwebhf.Budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.android.mobwebhf.Data.DataManager
import hu.bme.aut.android.mobwebhf.databinding.FragmentBugdetIncomeBinding
import java.util.*


class BudgetIncomeFragment() : Fragment() {
    private lateinit var binding: FragmentBugdetIncomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBugdetIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = DataManager.item
        binding.tvType.text = "Income"
        binding.tvName.text = "stock"
        binding.tvPrice.text = "100"
        binding.tvTimeOfIncome.text = Date().toString()

    }

}