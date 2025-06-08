package givasques.com.github.alunos_rm99884_rm552301.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import givasques.com.github.alunos_rm99884_rm552301.model.EventoModel

@Database(entities = [EventoModel::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class EventoDataBase : RoomDatabase() {
    abstract fun eventoDao(): EventoDao
}