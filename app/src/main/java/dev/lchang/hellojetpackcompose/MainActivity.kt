package dev.lchang.hellojetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.lchang.hellojetpackcompose.ui.theme.HelloJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeForm()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloComposeForm(){
    var talla by remember { mutableStateOf(value = "") }
    var peso by remember { mutableStateOf(value = "") }
    var imc by remember { mutableStateOf(value = "") }

    Scaffold(
        topBar = {
            TopAppBar(title = {Text("IMC")})
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(paddingValues=padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Text("Hola ESANINO")
            OutlinedTextField(
                value = talla,
                onValueChange = {talla = it},
                label = {Text("Talla (cm)")}
            )
            OutlinedTextField(
                value = peso,
                onValueChange = {peso = it},
                label = {Text("Peso (kg)")}
            )
            Button(
                onClick = {
                    val tallaNumero = talla.toDoubleOrNull()
                    val pesoNumero = peso.toDoubleOrNull()

                    if (tallaNumero != null && pesoNumero != null) {
                        val tallaMetros = tallaNumero/100
                        val resultado = pesoNumero / (tallaMetros * tallaMetros)
                        imc = "%.2f".format(resultado)
                    }},
                enabled = talla.isNotEmpty() && peso.isNotEmpty()
            ){
                Text("Calcular")
            }
            if (imc.isNotEmpty()) {
                Text(
                    text = "IMC: $imc"
                )
            }
        }
    }
}