    package com.example.hh_pantallas.reciclerVierw


    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import com.example.homeless_helper_vs29.R
    import com.example.model.Lugares


    class LugaresRvAdapter(private val lugares:List<Lugares>): RecyclerView.Adapter<LugaresViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugaresViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return LugaresViewHolder(layoutInflater.inflate(R.layout.rv_layout_lugares, parent, false))
        }

        override fun onBindViewHolder(holder: LugaresViewHolder, position: Int) {
            //holder.printLugares(lugares[position])

            val item = lugares[position]
            val context = holder.itemView.context
            holder.printLugares(item,context);

        }

        override fun getItemCount(): Int {
        return  lugares.size
        }



    }
