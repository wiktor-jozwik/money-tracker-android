package com.example.moneytracker.view.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneytracker.R
import com.example.moneytracker.databinding.FragmentOperationListBinding
import com.example.moneytracker.view.adapter.OperationAdapter
import com.example.moneytracker.viewmodel.OperationListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OperationListFragment : Fragment(R.layout.fragment_operation_list) {
    private val operationListViewModel: OperationListViewModel by viewModels()

    private var _binding: FragmentOperationListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOperationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            operationListViewModel.getAllOperations().observe(viewLifecycleOwner) {
                binding.recyclerViewOperationItems.adapter = OperationAdapter(
                    it,
                    OperationAdapter.OnClickListener { operationId ->
                        deleteOperation(operationId,
                            binding.recyclerViewOperationItems.adapter as OperationAdapter
                        )
                    }
                )
            }
        }

        binding.recyclerViewOperationItems.layoutManager = LinearLayoutManager(activity)
    }

    private fun deleteOperation(operationId: Int, adapter: OperationAdapter) {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Are you sure?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                viewLifecycleOwner.lifecycleScope.launch {
                    operationListViewModel.deleteOperation(operationId)
                    adapter.deleteOperation(operationId)
                }
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}