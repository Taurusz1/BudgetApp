package hu.bme.aut.android.mobwebhf.analitics

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import hu.bme.aut.android.mobwebhf.Data.BudgetItem
import hu.bme.aut.android.mobwebhf.R
import hu.bme.aut.android.mobwebhf.databinding.ActivityAnalyticsBinding
import hu.bme.aut.android.mobwebhf.sqlite.DbConstants
import hu.bme.aut.android.mobwebhf.sqlite.PersistentDataHelper

class AnalyticsActivity : AppCompatActivity() {
    private lateinit var dataHelper: PersistentDataHelper
    private lateinit var binding : ActivityAnalyticsBinding
    private val incomeDbName = DbConstants.IncomeItems.DATABASE_TABLE
    private val expenseDbName = DbConstants.ExpenseItems.DATABASE_TABLE
    private lateinit var incomeItems: MutableList<BudgetItem>
    private lateinit var expenseItems: MutableList<BudgetItem>


    inner class ChartColors{
        val COLORS = intArrayOf(
            Color.rgb(46, 204, 113),
            Color.rgb(241, 196, 15),
            Color.rgb(255, 208, 140),
            Color.rgb(231, 76, 60),
            Color.rgb(52, 152, 219),
            Color.rgb(128,0,128),

            Color.rgb(3, 248, 252),
            Color.rgb(252, 3, 206),
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataHelper = PersistentDataHelper(this)
        dataHelper.open()
        incomeItems = dataHelper.restoreItems(incomeDbName)
        expenseItems = dataHelper.restoreItems(expenseDbName)
        drawBudget()
    }

    private fun drawBudget(){
        val sumList = dataHelper.calcExpense(DbConstants.DATABASE_TABLE_EXPENSE)
        val income = dataHelper.calcSums(DbConstants.DATABASE_TABLE_INCOME)
        val expense = dataHelper.calcSums(DbConstants.DATABASE_TABLE_EXPENSE)
        val savings = dataHelper.calcSums(DbConstants.DATABASE_TABLE_SAVINGS)
        var entries = listOf(
            PieEntry(income.toFloat(), "Income"),
            PieEntry(expense.toFloat(), "Expense"),
            PieEntry(savings.toFloat(), "Savings"),
            PieEntry(sumList[0].toFloat(),"Food"),
            PieEntry(sumList[1].toFloat(),"Hobby"),
            PieEntry(sumList[2].toFloat(),"Clothes"),
            PieEntry(sumList[3].toFloat(),"Entertainment"),
            PieEntry(sumList[4].toFloat(),"Other"),
        )

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = ChartColors().COLORS.toList()
        val data = PieData(dataSet)
        binding.chartBudget.data = data
        binding.chartBudget.invalidate()

        var res = resources
        binding.tv1.text = res.getString(R.string.incomeValue, income)
        binding.tv2.text = res.getString(R.string.expenseValue, expense)
        binding.tv3.text = res.getString(R.string.savingsValue, savings)
        binding.tv4.text = res.getString(R.string.food, sumList[0])
        binding.tv5.text = res.getString(R.string.hobby, sumList[1])
        binding.tv6.text = res.getString(R.string.clothes, sumList[2])
        binding.tv7.text = res.getString(R.string.entertainment, sumList[3])
        binding.tv8.text = res.getString(R.string.other, sumList[4])
    }
}