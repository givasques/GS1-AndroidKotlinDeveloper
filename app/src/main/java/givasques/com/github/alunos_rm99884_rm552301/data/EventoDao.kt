package givasques.com.github.alunos_rm99884_rm552301.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import givasques.com.github.alunos_rm99884_rm552301.model.EventoModel

@Dao
interface EventoDao {

    @Query("SELECT * FROM EventoModel ORDER BY data DESC")
    fun getAll(): LiveData<List<EventoModel>>

    @Insert
    fun insert(evento: EventoModel)

    @Delete
    fun delete(evento: EventoModel)
}