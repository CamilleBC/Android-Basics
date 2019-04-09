package me.camillebc.androidbasics.view.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_dog.view.*
import me.camillebc.androidbasics.R
import me.camillebc.androidbasics.view.fragment.DogListFragment
import me.camillebc.androidbasics.view.model.Dog

/**
 * [RecyclerView.Adapter] that can display a [Dog] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class MyDogRecyclerViewAdapter(
    private var dogList: List<Dog>,
    private val listener: DogListFragment.OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyDogRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Dog
            // Notify the active callbacks interface that an item has been selected.
            listener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_dog, parent, false)
        return ViewHolder(view)
    }

    /**
     * This function binds the [RecyclerView] item data to the view's data, as well as the view's [onClickListener].
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dogList[position]
        holder.dogName.text = item.name
        holder.dogBreed.text = item.breed
        holder.dogSubBreed.text = item.subBreed

        with(holder.view) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int = dogList.size

    /**
     * Set the data for the [RecyclerView] and notify the UI it has changed.
     *
     * @param data List of [Dog] to display in the [RecyclerView]
     */
    fun setData(data: List<Dog>) {
        dogList = data
        notifyDataSetChanged()
    }

    /**
     * This class describes the bindings between the [RecyclerView] data and the [Dog] item view content
     */
    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val dogName: TextView = view.textView_dog_name
        val dogBreed: TextView = view.textView_dog_breed
        val dogSubBreed: TextView = view.textView_dog_subBreed
    }
}
