package givasques.com.github.alunos_rm99884_rm552301

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EquipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_equipe)
        title = "Equipe"

        val botaoVoltar = findViewById<Button>(R.id.button)
        botaoVoltar.setOnClickListener {
            finish()
        }
    }
}