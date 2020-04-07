package org.d3if4021.jurnal10.ui.recyclerview

import android.view.View
import org.d3if4021.jurnal10.data.Miwok

interface RecyclerViewClickListener {

    fun onRecyclerViewItemClicked(view: View, miwok: Miwok)

}