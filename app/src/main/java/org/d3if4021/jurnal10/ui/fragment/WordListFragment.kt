package org.d3if4021.jurnal10.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import org.d3if4021.jurnal10.R
import org.d3if4021.jurnal10.data.Miwok
import org.d3if4021.jurnal10.databinding.FragmentWordListBinding
import org.d3if4021.jurnal10.ui.recyclerview.WordListAdapter
import org.d3if4021.jurnal10.ui.viewmodel.MiwokViewModel

/**
 * A simple [Fragment] subclass.
 */
class WordListFragment : Fragment() {

    private lateinit var binding: FragmentWordListBinding
    private lateinit var viewModel: MiwokViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_word_list, container, false)
        viewModel = ViewModelProvider(this).get(MiwokViewModel::class.java)
        binding.lifecycleOwner = this
        binding.vmMiwok = viewModel

        if (arguments != null){
            val category = arguments?.getString("category")
            val listData = mutableListOf<Miwok>()
            viewModel.data.observe(viewLifecycleOwner, Observer {
                it.map { miwok ->
                    if (miwok.category == category){
                        listData.add(miwok)
                    }
                }
                val adapter = WordListAdapter(listData, category!!)
                val recyclerView = binding.rvWordList
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
            })
        }

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it == false){
                binding.loadingBar.visibility - View.GONE
            }
        })

        return binding.root
    }

}
