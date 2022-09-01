package com.hs.roomwitharchitecture.fragment.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hs.roomwitharchitecture.model.User
import com.hs.roomwitharchitecture.databinding.CustomRowBinding

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    inner class MyViewHolder(binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {
        private val id = binding.idTxt
        private val firstName = binding.firstNameTxt
        private val lastName = binding.lastNameTxt
        private val age = binding.ageTxt
        private val container = binding.rowLayout

        fun bind(userData: List<User>, position: Int) {
            val currentItem = userData[position]

            id.text = currentItem.id.toString()
            firstName.text = currentItem.firstName
            lastName.text = currentItem.lastName
            age.text = currentItem.age.toString()

            container.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                it.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        val view = CustomRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        return holder.bind(userList, position)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDate(user: List<User>) {
        this.userList = user
        // 아이템의 삽입, 삭제, 이동, 변경이 일어남을 어댑터에게 알림
        notifyDataSetChanged()
    }
}