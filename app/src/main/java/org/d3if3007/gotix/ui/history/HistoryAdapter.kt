package org.d3if3007.gotix.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3007.gotix.R
import org.d3if3007.gotix.databinding.HistoryItemBinding
import org.d3if3007.gotix.db.GotixDb
import org.d3if3007.gotix.db.GotixEntity
import org.d3if3007.gotix.model.hitungP
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter :
    androidx.recyclerview.widget.ListAdapter<GotixEntity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<GotixEntity>() {
                override fun areItemsTheSame(
                    oldData: GotixEntity, newData: GotixEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: GotixEntity, newData: GotixEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView)
    }

    class ViewHolder(
        private val binding: HistoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
        Locale("id", "ID"))

        fun bind(item: GotixEntity, view: View) = with(binding) {
            val hasilN = item.hitungP()
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            kuotaTextView.text = root.context.getString(R.string.kuota_x, hasilN.kuota.toString())
            filmTextView.text = root.context.getString(R.string.film_x, item.film)
            totalTextView.text = root.context.getString(R.string.total_x, hasilN.harga.toString())
            binding.button.setOnClickListener { hapusData(item.id, view.context) }
        }

        private fun hapusData(id: Long, context: Context) {
            val db = GotixDb.getInstance(context)
            val GotixDao = db.dao
            MaterialAlertDialogBuilder(context)
                .setMessage(context.getString(R.string.konfirmasi_hapus))
                .setPositiveButton(context.getString(R.string.hapus)) { _, _ ->
                    CoroutineScope(Dispatchers.IO).launch {
                        withContext(Dispatchers.IO) {
                            GotixDao.deleteHistory(id)
                        }
                    }
                }
                .setNegativeButton(context.getString(R.string.batal)) { dialog, _ ->
                    dialog.cancel()
                }
                .show()
        }
    }
}