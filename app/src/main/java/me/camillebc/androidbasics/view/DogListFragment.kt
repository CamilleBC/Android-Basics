package me.camillebc.androidbasics.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_dog_list.*
import me.camillebc.androidbasics.R

/**
 * A fragment representing a list of [Dog].
 * Activities containing this fragment MUST implement the
 * [DogListFragment.OnListFragmentInteractionListener] interface.
 */
class DogListFragment : Fragment() {

    private lateinit var activityCallback: OnAddClickListener
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

    /**
     * [onActivityCreated] is called AFTER [onCreateView] and AFTER the parent activity's [onCreate]
     * 1- We set the OnClickListener to the callback that is implemented (through an interface) in the parent activity.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { fragmentActivity ->
            activityCallback = fragmentActivity as OnAddClickListener
            button_dogList_add.setOnClickListener{ activityCallback.onAddClick() }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction()
    }
    interface OnAddClickListener {
        fun onAddClick()
    }
}
