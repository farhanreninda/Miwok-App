package org.d3if4021.jurnal10.ui.recyclerview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if4021.jurnal10.R
import org.d3if4021.jurnal10.data.Miwok
import org.d3if4021.jurnal10.databinding.RecyclerviewWordListBinding

class WordListAdapter(
    private val miwok: List<Miwok>,
    private val category: String
): RecyclerView.Adapter<WordListAdapter.WordListViewHolder>() {

    inner class WordListViewHolder(
        val recyclerviewWordListBinding: RecyclerviewWordListBinding
    ): RecyclerView.ViewHolder(recyclerviewWordListBinding.root)

    override fun getItemCount() = miwok.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WordListViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.recyclerview_word_list,parent,false)
    )

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
       if (miwok[position].category.contains(category)){
           holder.recyclerviewWordListBinding.wordList.setBackgroundColor(
               Color.parseColor(miwok[position].background)
           )
           holder.recyclerviewWordListBinding.tvMiwok.text = miwok[position].miwokWord
           holder.recyclerviewWordListBinding.tvInggris.text = miwok[position].defaultWord
//           holder.recyclerviewWordListBinding.image

           if (miwok[position].image == ""){
               Glide.with(holder.itemView.context).clear(holder.recyclerviewWordListBinding.image)
               holder.recyclerviewWordListBinding.image.setImageDrawable(null)
               holder.recyclerviewWordListBinding.image.visibility = View.GONE
           }else{
               Glide.with(holder.itemView.context)
                   .load("http://dif.indraazimi.com/miwok/${miwok[position].image}")
                   .placeholder(R.drawable.ic_launcher_foreground)
                   .dontAnimate()
                   .into(holder.recyclerviewWordListBinding.image)
           }
       }
    }
}