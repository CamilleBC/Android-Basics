package me.camillebc.androidbasics.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.fragment_dog_editor.*
import me.camillebc.androidbasics.R


class DogEditorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dog_editor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            setOnClickListeners(it)
        }
    }

    private fun setOnClickListeners(activity: FragmentActivity) {
        val onCancelClickListener = activity as OnCancelClickListener
        val onAddClickListener = activity as OnAddClickListener
        button_dogEditor_cancel.setOnClickListener { onCancelClickListener.onEditorCancelClick() }
        button_dogEditor_add.setOnClickListener {
            onAddClickListener.onEditorAddClick(
                editText_dogEditor_name.text.toString(),
                editText_dogEditor_breed.text.toString(),
                editText_dogEditor_subBreed.text.toString()
            )
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnCancelClickListener {
        fun onEditorCancelClick()
    }
    interface OnAddClickListener {
        fun onEditorAddClick(name: String, breed: String, subBreed: String)
    }
}
