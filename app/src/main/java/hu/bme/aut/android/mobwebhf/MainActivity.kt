package hu.bme.aut.android.mobwebhf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.mobwebhf.analitics.AnalyticsActivity
import hu.bme.aut.android.mobwebhf.budget.BudgetActivity
import hu.bme.aut.android.mobwebhf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    companion object {
        const val TYPE_INCOME = 1
        const val TYPE_EXPENSE = 2
        const val TYPE_SAVINGS = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIncome.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            intent.putExtra(BudgetActivity.KEY_TRANSPORT_TYPE, TYPE_INCOME)
            startActivity(intent)
        }
        binding.btnSpending.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            intent.putExtra(BudgetActivity.KEY_TRANSPORT_TYPE, TYPE_EXPENSE)
            startActivity(intent)
        }
        binding.btnAnalitics.setOnClickListener {
            val intent = Intent(this, AnalyticsActivity::class.java)
            startActivity(intent)
        }
        binding.btnVault.setOnClickListener {
            val intent = Intent(this, BudgetActivity::class.java)
            intent.putExtra(BudgetActivity.KEY_TRANSPORT_TYPE, TYPE_SAVINGS)
            startActivity(intent)
        }
    }
}