package hu.bme.aut.android.mobwebhf.Budget

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BudgetPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment = when(position){
        0 -> BudgetSpendingFragment()
        1 -> BudgetIncomeFragment()
        else -> BudgetSpendingFragment()
    }

    companion object{
        const val NUM_PAGES = 2
    }
}