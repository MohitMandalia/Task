package com.strings.task.ui.presentation.task

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.strings.task.database.entity.Task

@Composable
fun TaskItem(
    task: Task,
    onEvent: (TaskEvent) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                
                Text(
                    text = task.taskName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            task.taskDescription?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = it)
            }
        }
        Checkbox(checked = task.isCompleted,
            onCheckedChange = { isChecked ->
                onEvent(TaskEvent.OnCompletedChange(task, isChecked))
            }
        )
        
    }


}