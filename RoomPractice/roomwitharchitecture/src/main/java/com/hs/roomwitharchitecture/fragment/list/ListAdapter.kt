package com.hs.roomwitharchitecture.fragment.list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hs.roomwitharchitecture.data.User
import com.hs.roomwitharchitecture.databinding.CustomRowBinding

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<User>()

    class MyViewHolder(binding: CustomRowBinding) : RecyclerView.ViewHolder(binding.root) {
        private val id = binding.idTxt
        private val firstName = binding.firstNameTxt
        private val lastName = binding.lastNameTxt
        private val age = binding.ageTxt

        fun bind(userData: List<User>, position: Int) {
            id.text = userData[position].id.toString()
            firstName.text = userData[position].firstName
            lastName.text = userData[position].lastName
            age.text = userData[position].age.toString()
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
        // 아이템의 삽입, 삭제, 이동, 변경이 일어남
        notifyDataSetChanged()
    }
}