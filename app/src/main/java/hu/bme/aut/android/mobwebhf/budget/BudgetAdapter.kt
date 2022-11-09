package hu.bme.aut.android.mobwebhf.budget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.mobwebhf.Data.BudgetItem
import hu.bme.aut.android.mobwebhf.R
import hu.bme.aut.android.mobwebhf.databinding.ItemBudgetItemBinding


class BudgetAdapter(private val listener: OnBudgetItemSelectedListener) : RecyclerView.Adapter<BudgetAdapter.BudgetItemViewHolder>() {
    private val items: MutableList<BudgetItem> = ArrayList()

    interface OnBudgetItemSelectedListener {
        fun onBudgetItemSelected(item: BudgetItem?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_budget_item, parent, false)
        return BudgetItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BudgetItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun addBudgetItem(newItem: BudgetItem) {
        items.add(newItem)
        notifyItemInserted(items.size - 1)
    }

    fun removeBudgetItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        if (position < items.size) {
            notifyItemRangeChanged(position, items.size - position)
        }
    }

    inner class BudgetItemViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemBudgetItemBinding.bind(itemView)
        var item: BudgetItem? = null

        init {
            binding.root.setOnClickListener { listener.onBudgetItemSelected(item) }

            binding.budgetItemRemoveButton.setOnClickListener{
                removeBudgetItem(items.indexOf(item))
            }
        }

        fun bind(newItem: BudgetItem?) {
            item = newItem
            binding.tvName.text = item.toString()
        }
    }
}