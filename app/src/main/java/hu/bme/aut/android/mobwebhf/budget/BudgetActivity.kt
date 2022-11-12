package hu.bme.aut.android.mobwebhf.budget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.mobwebhf.Data.BudgetItem
import hu.bme.aut.android.mobwebhf.MainActivity
import hu.bme.aut.android.mobwebhf.databinding.ActivityBudgetBinding
import hu.bme.aut.android.mobwebhf.sqlite.PersistentDataHelper

class BudgetActivity : AppCompatActivity(), BudgetAdapter.OnBudgetItemSelectedListener, AddBudgetItemDialogFragment.AddBudgetItemDialogListener{
    private lateinit var binding: ActivityBudgetBinding
    private lateinit var adapter: BudgetAdapter
    private lateinit var dataHelper: PersistentDataHelper
    private lateinit var typeString: String
    companion object {
        const val KEY_TRANSPORT_TYPE = "KEY_TRANSPORT_TYPE"
    }
    private fun getTypeString(type: Int): String {
        return when (type) {
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

        dataHelper = PersistentDataHelper(this)
        dataHelper.open()
        val type = this.intent.getIntExtra(KEY_TRANSPORT_TYPE, -1)
        typeString = getTypeString(type)
        restorePersistedItems()


    }
    override fun onResume() {
        super.onResume()
        dataHelper.open()
    }

    override fun onPause() {
        dataHelper.close()
        super.onPause()
    }

    override fun onBackPressed() {
        onExit()
    }

    private fun onExit() {
        when(typeString){
            "Income" -> {
                dataHelper.persistBudgetItems(adapter.getItems())
            }
            "Expense" -> {
                dataHelper.persistBudgetItems(adapter.getItems())
            }
        }
        dataHelper.close()
        finish()
    }

    private fun restorePersistedItems(){
        when(typeString){
            "Income" ->{
                val items = dataHelper.restoreIncomeItems()
                for(i in items){
                    onBudgetItemAdded(i)
                }
            }
            "Expense" ->{
                val items= dataHelper.restoreExpenseItems()
                for(i in items){
                    onBudgetItemAdded(i)
                }
            }
            else ->{}
        }

    }

    private fun initFab() {
        binding.fab.setOnClickListener {
            AddBudgetItemDialogFragment().show(supportFragmentManager, AddBudgetItemDialogFragment::class.java.simpleName)
        }
    }

    private fun initRecyclerView() {
        binding.budgetRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)
        adapter = BudgetAdapter(this)
        binding.budgetRecyclerView.adapter = adapter
    }

    override fun onBudgetItemSelected(item: BudgetItem?) {
        //val showDetailsIntent = Intent()
        //showDetailsIntent.setClass(this@BudgetActivity, AddBudgetItemDialogFragment::class.java)
        //showDetailsIntent.putExtra(DetailsActivity.EXTRA_CITY_NAME, city)
        //startActivity(showDetailsIntent)
    }

    override fun onBudgetItemAdded(item: BudgetItem?) {
        adapter.addBudgetItem(item!!)
    }
}