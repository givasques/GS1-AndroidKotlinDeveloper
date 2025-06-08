package givasques.com.github.alunos_rm99884_rm552301.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EventosViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventosViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}