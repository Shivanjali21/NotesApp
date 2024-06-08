package com.practice.notesapp.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.notesapp.R
import com.practice.notesapp.databinding.RvNotesItemBinding
import com.practice.notesapp.model.Notes

class HomeAdapter() : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val diffUtil = object : DiffUtil.ItemCallback<Notes>(){
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
          return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
          return oldItem.id == newItem.id
        }
    }

    val asyncDiffUtil = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
      val view = RvNotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        holder.binding.apply {
            val noteList = asyncDiffUtil.currentList[position]
            mtvTitle.text = noteList.title
            mtvSubTitle.text = noteList.subTitle
            mtvDate.text = noteList.date
            when (noteList.priority) {
                "1" -> {
                    viewPriority.setBackgroundResource(R.drawable.green_dot)
                }

                "2" -> {
                    viewPriority.setBackgroundResource(R.drawable.yellow_dot)
                }

                "3" -> {
                    viewPriority.setBackgroundResource(R.drawable.red_dot)
                }
            }

            mcvNotesRoot.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(noteList)
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
      return asyncDiffUtil.currentList.size
    }

    inner class HomeViewHolder(val binding: RvNotesItemBinding) : RecyclerView.ViewHolder(binding.mcvNotesRoot)

}