package com.example.battleship.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.battleship.R
import com.example.battleship.model.entities.Cell
import com.example.battleship.model.entities.Matrix

class MatrixAdapter(private var matrix: Matrix) :
    RecyclerView.Adapter<MatrixAdapter.MyViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(i: Int, j: Int)
    }

    private var itemClickListener: OnItemClickListener? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_item, parent, false)
        val width = parent.width / matrix.rows()
        val height = parent.height / matrix.columns()

        view.layoutParams.height = height
        view.layoutParams.width = width
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val i = position % matrix.rows()
        val j = position / matrix.columns()

        when (matrix[i, j]) {
            Cell.Empty -> {
                holder.itemView.findViewById<ImageView>(R.id.cell_item)
                    .setImageResource(R.drawable.battleships_cell_empty)
                holder.itemView.setOnClickListener {
                    if (itemClickListener != null ) {
                        itemClickListener!!.onItemClick(i, j)
                        notifyDataSetChanged()
                    }
                }
            }
            Cell.Hurt -> {
                holder.itemView.findViewById<ImageView>(R.id.cell_item)
                    .setImageResource(R.drawable.battleships_cell_hurt)
            }
            Cell.Dead -> {
                holder.itemView.findViewById<ImageView>(R.id.cell_item)
                    .setImageResource(R.drawable.battleships_cell_dead)
            }
            Cell.Miss -> {
                holder.itemView.findViewById<ImageView>(R.id.cell_item)
                    .setImageResource(R.drawable.battleships_cell_miss)
            }
            Cell.Ship -> {
                holder.itemView.setOnClickListener {
                    if (itemClickListener != null ) {
                        itemClickListener!!.onItemClick(i, j)
                        notifyDataSetChanged()
                    }
                }
                holder.itemView.findViewById<ImageView>(R.id.cell_item)
                    .setImageResource(R.drawable.battleships_cell_ship)
            }
        }
    }

    override fun getItemCount(): Int {
        return matrix.rows() * matrix.columns()
    }
    fun setMatrix(matrix: Matrix) {
        this.matrix = matrix
        notifyDataSetChanged()
    }
}