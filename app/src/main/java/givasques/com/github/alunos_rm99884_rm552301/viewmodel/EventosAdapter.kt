package givasques.com.github.alunos_rm99884_rm552301.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import givasques.com.github.alunos_rm99884_rm552301.R
import givasques.com.github.alunos_rm99884_rm552301.model.EventoModel
import java.text.SimpleDateFormat
import java.util.Locale

class EventosAdapter(private val onEventoRemoved: (EventoModel) -> Unit) :
RecyclerView.Adapter<EventosAdapter.EventoViewHolder>() {

    private var eventos = listOf<EventoModel>()

    inner class EventoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewEventoNome = view.findViewById<TextView>(R.id.textViewEventoNome)
        val textViewEventoTipo = view.findViewById<TextView>(R.id.textViewEventoTipo)
        val textViewEventoGrau = view.findViewById<TextView>(R.id.textViewEventoGrau)
        val textViewEventoData = view.findViewById<TextView>(R.id.textViewEventoData)
        val textViewEventoPessoasAfetadas = view.findViewById<TextView>(R.id.textViewEventoPessoasAfetadas)
        val button = view.findViewById<ImageButton>(R.id.imageButton)

        fun bind(evento: EventoModel) {
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val dataFormatada = formato.format(evento.data)

            textViewEventoNome.text = evento.nome
            textViewEventoTipo.text = "Tipo: ${evento.tipo}"
            textViewEventoGrau.text = "Grau de Impacto: ${evento.grauImpacto}"
            textViewEventoData.text = "Data do evento: ${dataFormatada.toString()}"
            textViewEventoPessoasAfetadas.text = "Nº de pessoas afetadas: ${evento.pessoasAfetadas.toString()}"

            button.setOnClickListener {
                onEventoRemoved(evento)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.evento_layout, parent, false)
        return EventoViewHolder(view)
    }

    override fun getItemCount(): Int = eventos.size

    override fun onBindViewHolder(holder: EventoViewHolder, position: Int) {
        val evento = eventos[position]
        holder.bind(evento)
    }

    fun updateEventos(newEventos: List<EventoModel>) {
        eventos = newEventos
        notifyDataSetChanged()
    }
}