package givasques.com.github.alunos_rm99884_rm552301.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class EventoModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String, val tipo: String, val grauImpacto : String,
    val data : Date, val pessoasAfetadas: Int)