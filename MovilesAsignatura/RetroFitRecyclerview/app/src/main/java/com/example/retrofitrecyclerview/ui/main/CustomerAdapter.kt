package com.example.retrofitrecyclerview.ui.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitrecyclerview.databinding.ViewPersonaBinding
import com.example.retrofitrecyclerview.domain.Customer

class CustomerAdapter (
    var customers: List<Customer>,
    private val listener: (Customer)->Unit
) :
    RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {

    interface CustomerActions{
        fun onDelete(customer: Customer)
        fun onStartSelectMode(customer: Customer)
        fun itemHasClicked(customer: Customer)
    }


    //CREAR NUEVA VISTA CUANDO EL RECYCER VIEW SE LO PIDA
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
       val view = ViewPersonaBinding.inflate(LayoutInflater
           .from(parent.context),
           parent,
           false)

        return CustomerViewHolder(view)
    }

    //ESTE CREARA UNA VISTA CUANDO SE LO PIDA
    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customers[position]

        holder.bind(customer)
        holder.itemView.setOnClickListener {
            listener(customer)
        }

    }

    //ESTE DEVOLVERA EL NUMERO DE ELEMENTOS QUE TENDRA EL RECYCLER VIEW
    override fun getItemCount() = customers.size

    class CustomerViewHolder(private val binding:ViewPersonaBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(customer: Customer){
            binding.tvId.text = customer.id.toString()
            binding.tvNombre.text = customer.name
        }
    }
}

