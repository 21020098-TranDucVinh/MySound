package uet.app.mysound.adapter.moodandgenre.genre

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uet.app.mysound.data.model.explore.mood.genre.Content
import uet.app.mysound.databinding.ItemHomeContentPlaylistBinding

class GenreContentAdapter(private var contentList: ArrayList<Content>): RecyclerView.Adapter<GenreContentAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemHomeContentPlaylistBinding, var listener: OnClickListener): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                listener.onClick(bindingAdapterPosition)
            }
        }
    }
    interface OnClickListener{
        fun onClick(position: Int)
    }
    private lateinit var mListener: OnClickListener
    fun setOnClickListener(listener: OnClickListener){
        mListener = listener
    }
    fun updateData(newList: ArrayList<Content>){
        contentList.clear()
        contentList.addAll(newList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(ItemHomeContentPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false), mListener)

    override fun getItemCount(): Int = contentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val content = contentList[position]
        with(holder){
            binding.tvTitle.text = content.title.title
            binding.tvDescription.text = content.title.subtitle
            if (content.thumbnail != null) {
                binding.ivArt.load(content.thumbnail.last().url)
            }
        }
    }
}