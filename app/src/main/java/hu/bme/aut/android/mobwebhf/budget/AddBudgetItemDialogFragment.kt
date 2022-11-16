package hu.bme.aut.android.mobwebhf.budget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import hu.bme.aut.android.mobwebhf.Data.BudgetItem
import hu.bme.aut.android.mobwebhf.R
import hu.bme.aut.android.mobwebhf.databinding.DialogNewBudgetItemBinding


class AddBudgetItemDialogFragment : AppCompatDialogFragment() {

    private lateinit var binding: DialogNewBudgetItemBinding
    private lateinit var listener: AddBudgetItemDialogListener

    interface AddBudgetItemDialogListener {
        fun onBudgetItemAdded(item: BudgetItem?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        binding = DialogNewBudgetItemBinding.inflate(LayoutInflater.from(context))

        listener = context as? AddBudgetItemDialogListener
            ?: throw RuntimeException("Activity must implement the AddBudgetItemDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_item_title)
            .setView(binding.root)
            .setPositiveButton(R.string.ok) { _, _ ->
                run {
                    var cat = BudgetItem.Category.OTHER
                    when (binding.etCat.text.toString()) {
                        "Food" -> cat = BudgetItem.Category.FOOD
                        "Hobby" -> cat = BudgetItem.Category.HOBBY
                        "Clothes" -> cat = BudgetItem.Category.CLOTHES
                        "Entertainment" -> cat = BudgetItem.Category.ENTERTAINMENT
                        "Other" -> cat = BudgetItem.Category.OTHER
                        "Income" -> cat = BudgetItem.Category.INCOME
                        else -> {}
                    }
                    listener.onBudgetItemAdded(
                        BudgetItem(
                            binding.etNewBudgetItemName.text.toString(),
                            binding.etNewBudgetItemPrice.text.toString().toInt(),
                            cat
                        )
                    )
                }
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }
}
