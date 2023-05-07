package com.example.tasktoday

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasktoday.model.Tarefa.Tarefa
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
            }
        }
    }


@Composable
fun MainScreenContent(drawerState: DrawerState) {
    val scaffoldState = rememberScaffoldState( drawerState = drawerState)
    var scope = rememberCoroutineScope()
    var tabIndex by remember { mutableStateOf( 0) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "TaskTodayApp") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Drawer Menu")
                    }
                }
            )
        },
        drawerBackgroundColor = Color.Red,
        drawerGesturesEnabled = drawerState.isOpen,
        drawerContent = {
                        Box(
                            modifier = Modifier
                                .background(Color.Magenta)
                                .height(16.dp)
                        ) {
                            Text(text = "Opções!!!")
                        }
            //Drawer Content
            Column() {
                Text(text = "Opção de Menu 1")
                Text(text = "Opção de Menu 2")
                Text(text = "Opção de Menu 3")
            }
        },
        content = {
            paddingValues -> Log.i("paddingValues", "$paddingValues")
            Column(
                modifier = Modifier
                    .background(Color.Yellow)
                    .fillMaxSize()
            ) {
                MySearchField(modificador = Modifier.fillMaxWidth())
                //Variavel para lidar com as datas
                //LISTA DE WIDGETS

                //CADA WIDGET É UMA TAREFA
                //passar os valores que a função precisa, lista de tarefas
                //LISTA DE WIDGETS

                //CADA WIDGET É UMA TAREFA
                val tProvaDeCalculo = Tarefa(
                    "Estudar Prova de Calculo",
                    "Cap 1 Livro xyz",
                    Date(),
                    Date(),
                    status = 0.0
                )
                val tProvaDeKotlin = Tarefa(
                    "Estudar Prova de Kotlin",
                    "Cap 1 Livro xyz",
                    Date(),
                    Date(),
                    status = 0.0 //0 a 100 por cento
                )

                val minhaListaDeTarefas = listOf<Tarefa>(tProvaDeCalculo, tProvaDeKotlin)

                MyTaskWidgetList(minhaListaDeTarefas)


            }
        },
        bottomBar = {
            BottomAppBar(
                content = { Text("asdf") }
            )
        },
        isFloatingActionButtonDocked = false,
        floatingActionButton = { ExtendedFloatingActionButton(
            icon = {
                   Icon(imageVector = Icons.Default.AddCircle,
                       contentDescription = "Add Task")
            },
            text = { Text(text ="ADD") },
            onClick = { /*TODO*/ })

        }
    )//Scaffold
}//fun MainScreenContent(){
@Composable
//widget de listar vai receber as tarefas
fun MyTaskWidgetList(listaDeTarefas: List<Tarefa>) {
// Não sabemos o tamanho da lista que pode vir de um Db sqlite
// tem que ter uma forma de o user rolar as tarefas existentes
    listaDeTarefas.forEach(
        action = { MyTaskWidget(modificador = Modifier.fillMaxWidth(), tarefaASerMostrada = it) })
}
@Composable
fun MySearchField(modificador: Modifier) {
    TextField(value = "",
        onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "Pesquisar tarefas")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        })
}

@Composable
fun MyTaskWidget(
    modificador: Modifier,
    tarefaASerMostrada: Tarefa
    ) {
    val dateFormatter = SimpleDateFormat("EEE, MMM, dd, yyyy", Locale.getDefault())
    Row(modifier = modificador) {
        Column() {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Icons of a pendent task"
            )
            Text(
                text = dateFormatter.format(tarefaASerMostrada.pzoFinal),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }//Column Icone e Data // Column do taskname abaixo
        Column(
            modifier = modificador
                .border(width = 1.dp, color = Color.Black)
                .padding(3.dp)
        ) {
            Text(
                text = tarefaASerMostrada.nome,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = tarefaASerMostrada.detalhes.toString(),
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal
            )
        }
    }//Row
    Spacer(modifier = Modifier.height(16.dp))
}// fun MyTaskWidget()

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreenContent(DrawerState(initialValue = DrawerValue.Closed))
}