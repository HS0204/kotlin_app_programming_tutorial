package com.hs.roomwitharchitecture.fragment.add

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
import com.hs.roomwitharchitecture.R
import com.hs.roomwitharchitecture.data.User
import com.hs.roomwitharchitecture.data.UserViewModel
import com.hs.roomwitharchitecture.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var mUserViewModler: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        val view = binding

        mUserViewModler = ViewModelProvider(this)[UserViewModel::class.java]

        view.addBtn.setOnClickListener {
            insertDataToDatabase()
        }

        return view.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun insertDataToDatabase() {
        val firstName = binding.addFirstNameEt.text.toString()
        val lastName = binding.addLastNameEt.text.toString()
        val age = binding.addAgeEt.text

        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            mUserViewModler.addUser(user)

            Toast.makeText(this.requireContext(), "성공적으로 추가했습니다", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(this.requireContext(), "올바른 값을 모든 필드에 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        // 값이 형식에 맞춰서 제대로 들어왔는지 확인
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }


}