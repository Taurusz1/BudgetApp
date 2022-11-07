package hu.bme.aut.android.mobwebhf.Budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.android.mobwebhf.databinding.FragmentBudgetBinding
import hu.bme.aut.android.mobwebhf.sqlite.PersistentDataHelper

class BudgetFragment : Fragment() {
    private lateinit var binding: FragmentBudgetBinding
    private lateinit var dataHelper: PersistentDataHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBudgetBinding.inflate(inflater, container, false)
        dataHelper = PersistentDataHelper(binding.root.context)
        dataHelper.open()
        restorePersistedObjects()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpProfile.adapter = BudgetPageAdapter(this)
    }

    private fun restorePersistedObjects() {
        //binding.canvas.restoreObjects(dataHelper.restorePoints(), dataHelper.restoreLines())
    }
}