package hu.bme.aut.android.mobwebhf.Budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.android.mobwebhf.Data.DataManager
import hu.bme.aut.android.mobwebhf.databinding.FragmentBudgetSpendingBinding
import java.util.Date


class BudgetSpendingFragment : Fragment(){
    private lateinit var binding: FragmentBudgetSpendingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBudgetSpendingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = DataManager.item
        binding.tvType.text = "Expense"
        binding.tvName.text = item.Name
        binding.tvPrice.text = item.Price.toString()
        binding.tvCategory.text = "food"
        binding.tvTimeOfPurchase.text = Date().toString()
    }
}