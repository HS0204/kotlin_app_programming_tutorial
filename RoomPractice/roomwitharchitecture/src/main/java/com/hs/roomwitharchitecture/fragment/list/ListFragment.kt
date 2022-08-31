package com.hs.roomwitharchitecture.fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hs.roomwitharchitecture.R
import com.hs.roomwitharchitecture.data.UserViewModel
import com.hs.roomwitharchitecture.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModler: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding

        // recyclerView
        val adapter = ListAdapter(this.requireContext())
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        // UserViewModel
        mUserViewModler = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModler.readAllData.observe(viewLifecycleOwner, Observer {
            // user -> 이렇게 바꾸는 게 훨씬 직관적인 코드지만 아직 헷갈리니까 자료형이 보이는 it으로 두자
            // LiveData인 readAllData를 관찰하면서 바뀌는 걸 즉각적으로 반영함!
            adapter.setDate(it)
        })

        binding.floatingActionButton.setOnClickListener {
            // NavController가 addFragment로 이동시킴
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}