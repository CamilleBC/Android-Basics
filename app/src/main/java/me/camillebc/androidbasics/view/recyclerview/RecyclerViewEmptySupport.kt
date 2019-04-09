package me.camillebc.androidbasics.view.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Extends the [RecyclerView] class to add support to display an empty [View] when data set is empty.
 */
class RecyclerViewEmptySupport : RecyclerView {
    private var emptyView: View? = null

    private val observer = object : RecyclerView.AdapterDataObserver() {
        /**
         * Is called when the entire data set has changed (probably by reference?)
         */
        override fun onChanged() {
            super.onChanged()
            checkIfEmpty()
        }

        /**
         * Is called when items are inserted in the data set
         */
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkIfEmpty()
        }

        /**
         * Is called when items are removed from the data set
         */
        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkIfEmpty()
        }
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    /**
     * Replace the current adapter with the new one and trigger listeners.
     *
     * Warning: recycle all the existing views. If this is not wanted use [swapAdapter]
     * Unregister the observer to the old adapter and register it to the new.
     * Then it checks if data set is empty.
     */
    override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
        val oldObserver =
        super.setAdapter(adapter)
        checkIfEmpty()
    }

    /**
     * Similar to [setAdapter] but it assumes the old and new adapters use the same [RecyclerView.ViewHolder]
     * and does not recycle the existing views
     */
    override fun swapAdapter(adapter: RecyclerView.Adapter<*>?, removeAndRecycleExistingViews: Boolean) {
        super.swapAdapter(adapter, removeAndRecycleExistingViews)
        checkIfEmpty()
    }

    /**
     * Indicates the view to be shown when the adapter for this object is empty
     *
     * @param emptyView the view to be shown when the data set is empty
     */
    fun setEmptyView(emptyView: View?) {
        if (this.emptyView != null) {
            this.emptyView!!.visibility = View.GONE
        }

        this.emptyView = emptyView
        checkIfEmpty()
    }

    /**
     * Check the adapter item count and toggle visibility of the empty view if the adapter is empty
     */
    private fun checkIfEmpty() {
        adapter?.let {
            emptyView?.visibility = if (it.itemCount > 0) View.GONE else View.VISIBLE
        }
    }
}
