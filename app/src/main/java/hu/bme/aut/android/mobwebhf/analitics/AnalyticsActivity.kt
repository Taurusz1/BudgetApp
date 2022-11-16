package hu.bme.aut.android.mobwebhf.analitics

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import hu.bme.aut.android.mobwebhf.Data.BudgetItem
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
            Color.rgb(128,0,128)
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

    private fun caclIncome():Int{
        var sum = 0
        for(i in incomeItems){
            sum+=i.Price
        }
        return sum
    }
    private fun calcExpense():ArrayList<Int>{
        var foodSum = 0
        var hobbySum = 0
        var clothesSum = 0
        var entertainmentSum = 0
        var otherSum = 0
        var sum = 0
        var sumList = ArrayList<Int>()
        for(i in expenseItems){
            when(i.cat.name){
                "FOOD" -> foodSum+=i.Price
                "HOBBY" -> hobbySum+=i.Price
                "CLOTHES" -> clothesSum+=i.Price
                "ENTERTAINMENT" -> entertainmentSum+=i.Price
                "OTHER"-> otherSum+=i.Price
                else -> {}
            }
            sum+=i.Price
        }
        sumList.add(foodSum)
        sumList.add(hobbySum)
        sumList.add(clothesSum)
        sumList.add(entertainmentSum)
        sumList.add(otherSum)
        sumList.add(sum)
        return sumList
    }

    private fun drawBudget(){
        val sumList = calcExpense()
        var entries = listOf(
            PieEntry(caclIncome().toFloat(), "Income"),
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
        binding.tv1.text ="Income: "+ caclIncome().toString()
        binding.tv2.text ="Food: "+ sumList[0].toString()
        binding.tv3.text ="Hobby: "+ sumList[1].toString()
        binding.tv4.text ="Clothes: "+ sumList[2].toString()
        binding.tv5.text ="Entertainment: "+ sumList[3].toString()
        binding.tv6.text ="Other: "+ sumList[4].toString()
    }
}