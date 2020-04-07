package org.d3if4021.jurnal10.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.d3if4021.jurnal10.MainActivity

import org.d3if4021.jurnal10.R
import org.d3if4021.jurnal10.data.Miwok
import org.d3if4021.jurnal10.databinding.FragmentMiwokBinding
import org.d3if4021.jurnal10.ui.recyclerview.MiwokAdapter
import org.d3if4021.jurnal10.ui.recyclerview.RecyclerViewClickListener
import org.d3if4021.jurnal10.ui.viewmodel.MiwokViewModel

class MiwokFragment : Fragment(), RecyclerViewClickListener{

    private lateinit var binding: FragmentMiwokBinding
    private lateinit var viewModel: MiwokViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        judul()
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_miwok, container, false)
        viewModel = ViewModelProviders.of(this).get(MiwokViewModel::class.java)

        binding.vmMiwok = viewModel
        binding.lifecycleOwner = this

        viewModel.data.observe(viewLifecycleOwner, Observer {
            val datafix = it.distinctBy { miwok -> miwok.category }
            val adapter = MiwokAdapter(datafix)
            val recyclerview = binding.rvMiwok
            recyclerview.adapter = adapter
            recyclerview.layoutManager = LinearLayoutManager(this.requireContext())
            adapter.listener = this
        })

        viewModel.response.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                Snackbar.make(requireView(),it,Snackbar.LENGTH_SHORT).show()
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it == false){
                binding.loadingBar.visibility = View.GONE
            }
        })
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onRecyclerViewItemClicked(view: View, miwok: Miwok) {
        val bundle = Bundle()
        bundle.putString("category", miwok.category)
        view.findNavController().navigate(R.id.action_miwokFragment_to_wordListFragment,bundle)
    }

    private fun judul() {
        val getActivity = activity!! as MainActivity
        getActivity.supportActionBar?.title = "Miwok || 6706184021"
    }
}
