package com.ptrnd.cashapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ptrnd.cashapp.data.Flow
import com.ptrnd.cashapp.databinding.FlowCardBinding
import kotlinx.android.synthetic.main.flow_card.view.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class ListAdapter: RecyclerView.Adapter<ListAdapter.DetailViewHolder>() {

    private var flowList = ArrayList<Flow>()

    class DetailViewHolder(private val binding: FlowCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(keyFlow: Flow){
//            val currentItem = flowList[position]
            with(binding){
                if (keyFlow.tipe_flow == "Pemasukan"){
                    var isiPemasukan = keyFlow.pemasukan
                    val numberFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                    val rupiah = numberFormat.format(isiPemasukan).toString()
                    nominalFlow.text = "[ + ] $rupiah"
                    gambarFlow.setImageResource(R.drawable.income_arrow_icon)
                } else {
                    var isiPengeluaran = keyFlow.pengeluaran
                    val numberFormat = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
                    val rupiah = numberFormat.format(isiPengeluaran).toString()
                    nominalFlow.text = "[ - ] $rupiah"
                    gambarFlow.setImageResource(R.drawable.outcome_arrow_icon)
                }
                keteranganFlow.text = keyFlow.keterangan.toString()
                tanggalFlow.text = keyFlow.tanggal.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        //menampilkan data2 berupa kartu yang sudah didesain pada layout flow_card
        val binding = FlowCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailViewHolder(binding)
//        return DetailViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.flow_card, parent, false))
    }

    override fun getItemCount(): Int {
        //mengembalikan jumlah data flow
        return flowList.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(flowList[position])
    }

    fun setData(flow: List<Flow>){
        this.flowList.addAll(flow)
        notifyDataSetChanged()
    }
}