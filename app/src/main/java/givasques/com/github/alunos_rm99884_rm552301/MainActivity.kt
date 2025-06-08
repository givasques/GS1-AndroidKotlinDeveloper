package givasques.com.github.alunos_rm99884_rm552301

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import givasques.com.github.alunos_rm99884_rm552301.viewmodel.EventosAdapter
import givasques.com.github.alunos_rm99884_rm552301.viewmodel.EventosViewModel
import givasques.com.github.alunos_rm99884_rm552301.viewmodel.EventosViewModelFactory
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: EventosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Registro de Eventos Extremos"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val eventosAdapter = EventosAdapter { evento ->
            viewModel.removeEvento(evento)
        }
        recyclerView.adapter = eventosAdapter

        val button = findViewById<Button>(R.id.button)
        val editTextNome = findViewById<EditText>(R.id.editTextNome)
        val editTextTipo = findViewById<EditText>(R.id.editTextTipo)
        val editTextGrau = findViewById<EditText>(R.id.editTextGrau)
        val editTextData = findViewById<EditText>(R.id.editTextData)
        val editTextPessoasAfetadas = findViewById<EditText>(R.id.editTextPessoasAfetadas)

        val formatador = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        button.setOnClickListener {
            if (editTextNome.text.isEmpty()) {
                editTextNome.error = "Preencha um valor"
                return@setOnClickListener
            }
            if (editTextTipo.text.isEmpty()) {
                editTextTipo.error = "Preencha um valor"
                return@setOnClickListener
            }
            if (editTextGrau.text.isEmpty()) {
                editTextGrau.error = "Preencha um valor"
                return@setOnClickListener
            }
            if (editTextData.text.isEmpty()) {
                editTextData.error = "Preencha um valor"
                return@setOnClickListener
            }

            val pessoasAfetadas = editTextPessoasAfetadas.text.toString().toIntOrNull()
            if (pessoasAfetadas == null || pessoasAfetadas <= 0) {
                editTextPessoasAfetadas.error = "Preencha um valor maior do que 0!"
                return@setOnClickListener
            }

            viewModel.addEvento(
                editTextNome.text.toString(), editTextTipo.text.toString(),
                editTextGrau.text.toString(), formatador.parse(editTextData.text.toString()),
                editTextPessoasAfetadas.text.toString().toInt()
            )
            editTextNome.text.clear()
            editTextTipo.text.clear()
            editTextGrau.text.clear()
            editTextData.text.clear()
            editTextPessoasAfetadas.text.clear()
        }

        val viewModelFactory = EventosViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(EventosViewModel::class.java)

        viewModel.eventosLiveData.observe(this) { eventos ->
            eventosAdapter.updateEventos(eventos)
        }

        val botaoEquipe = findViewById<Button>(R.id.buttonEquipe)
        botaoEquipe.setOnClickListener {
            val intent = Intent(this, EquipeActivity::class.java)
            startActivity(intent)
        }
    }
}