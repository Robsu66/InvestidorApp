package br.ufc.quixada.investioraula

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import br.ufc.quixada.investioraula.view.InvestidorScreen
import br.ufc.quixada.investioraula.viewmodel.InvestimentosViewModel
import com.example.investidorapp.ui.theme.InvestidorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                101
            )
        }
        setContent {
            InvestidorAppTheme {
                val viewModel: InvestimentosViewModel = viewModel()
                InvestidorScreen(viewModel)
            }
        }
    }
}