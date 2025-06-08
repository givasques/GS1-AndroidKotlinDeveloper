package givasques.com.github.alunos_rm99884_rm552301.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import givasques.com.github.alunos_rm99884_rm552301.data.EventoDao
import givasques.com.github.alunos_rm99884_rm552301.data.EventoDataBase
import givasques.com.github.alunos_rm99884_rm552301.model.EventoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class EventosViewModel (application: Application) : AndroidViewModel(application)  {

    private val eventoDao: EventoDao
    val eventosLiveData: LiveData<List<EventoModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication(),
            EventoDataBase::class.java,
            "eventos_database").build()

        eventoDao = database.eventoDao();
        eventosLiveData = eventoDao.getAll ();
    }

    fun addEvento(nome: String, tipo: String, grauImpacto : String,
                  data : Date, pessoasAfetadas: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newEvento = EventoModel(nome = nome, tipo = tipo, grauImpacto = grauImpacto, data = data, pessoasAfetadas = pessoasAfetadas)
            eventoDao.insert(newEvento)
        }
    }

    fun removeEvento(evento: EventoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            eventoDao.delete(evento)
        }
    }
}