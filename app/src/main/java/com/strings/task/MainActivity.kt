package com.strings.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.strings.task.ui.presentation.task.TaskScreen
import com.strings.task.ui.presentation.task_add_edit.AddEditTaskScreen
import com.strings.task.ui.theme.TaskTheme
import com.strings.task.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController,
                startDestination = Routes.TASK_SCREEN ){
                
                composable(Routes.TASK_SCREEN){
                    TaskScreen(
                        onNavigate = {
                            navController.navigate(it.route)
                        }
                    )
                }
                composable(
                    route = Routes.ADD_EDIT_TASK + "?taskId={taskId}",
                    arguments = listOf(
                        navArgument(name = "taskId"){
                            type = NavType.LongType
                            defaultValue = -1
                        }
                    )
                ){
                    AddEditTaskScreen(onPopBackStack = {
                        navController.popBackStack()
                    })
                }
            }
        }
    }
}

