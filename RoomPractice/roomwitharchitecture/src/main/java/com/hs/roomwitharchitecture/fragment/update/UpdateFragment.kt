package com.hs.roomwitharchitecture.fragment.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hs.roomwitharchitecture.R
import com.hs.roomwitharchitecture.databinding.FragmentUpdateBinding
import com.hs.roomwitharchitecture.model.User
import com.hs.roomwitharchitecture.viewmodel.UserViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding

        binding.updateFirstNameEt.setText(args.currentUser.firstName)
        binding.updateLastNameEt.setText(args.currentUser.lastName)
        binding.updateAgeEt.setText(args.currentUser.age.toString())

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.updateBtn.setOnClickListener {
            updateItem()
        }

        return view.root
    }

    private fun updateItem() {
        val firstName = binding.updateFirstNameEt.text.toString()
        val lastName = binding.updateLastNameEt.text.toString()
        val age = Integer.parseInt(binding.updateAgeEt.text.toString())

        if (inputCheck(firstName, lastName, binding.updateAgeEt.text)) {
            // 새로운 User 객체에 현 User 정보
            val updateUser = User(args.currentUser.id, firstName, lastName, age)
            // 현재 User를 업데이트
            mUserViewModel.updateUser(updateUser)
            Toast.makeText(this.requireContext(), "성공적으로 수정했습니다", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(this.requireContext(), "올바른 값을 모든 필드에 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        // 값이 형식에 맞춰서 제대로 들어왔는지 확인
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}