package hu.bme.aut.android.mobwebhf.budget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.mobwebhf.Data.BudgetItem
import hu.bme.aut.android.mobwebhf.MainActivity
import hu.bme.aut.android.mobwebhf.databinding.ActivityBudgetBinding


class BudgetActivity : AppCompatActivity(), BudgetAdapter.OnBudgetItemSelectedListener, AddBudgetItemDialogFragment.AddBudgetItemDialogListener{
    private lateinit var binding: ActivityBudgetBinding
    private lateinit var adapter: BudgetAdapter

    companion object {
        const val KEY_TRANSPORT_TYPE = "KEY_TRANSPORT_TYPE"
    }
    private fun getTypeString(transportType: Int): String {
        return when (transportType) {
            MainActivity.TYPE_INCOME -> "Income"
            MainActivity.TYPE_EXPENSE -> "Expense"
            else -> "Unknown pass type"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFab()
        initRecyclerView()
    }

    private fun initFab() {
        binding.fab.setOnClickListener {
            AddBudgetItemDialogFragment().show(supportFragmentManager, AddBudgetItemDialogFragment::class.java.simpleName)
        }
    }

    private fun initRecyclerView() {
        binding.budgetRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        adapter = BudgetAdapter(this)
        adapter.addBudgetItem(BudgetItem("fasz1",2))
        adapter.addBudgetItem(BudgetItem("fasz2",2))
        adapter.addBudgetItem(BudgetItem("fasz3",2))
        adapter.addBudgetItem(BudgetItem("fasz4",2))
        binding.budgetRecyclerView.adapter = adapter
    }

    override fun onBudgetItemSelected(item: BudgetItem?) {
        //val showDetailsIntent = Intent()
        //showDetailsIntent.setClass(this@CityActivity, DetailsActivity::class.java)
        //showDetailsIntent.putExtra(DetailsActivity.EXTRA_CITY_NAME, city)
        //startActivity(showDetailsIntent)
    }

    override fun onBudgetItemAdded(item: BudgetItem?) {
        adapter.addBudgetItem(item!!)
    }

}