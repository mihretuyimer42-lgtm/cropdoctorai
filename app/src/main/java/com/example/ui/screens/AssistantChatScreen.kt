package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ai.ChatMessage
import com.example.viewmodel.CropDoctorViewModel
import kotlinx.coroutines.launch

@Composable
fun AssistantChatScreen(
    viewModel: CropDoctorViewModel,
    chatMessages: List<ChatMessage>,
    isAmharic: Boolean
) {
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val quickQuestions = if (isAmharic) {
        listOf(
            "የበቆሎ ቅጠል ለምን ወደ ቢጫነት ተቀየረ?",
            "የስንዴ ዋግ (Ug99) እንዴት ይከላከላል?",
            "የመኸር ሰራዊት አባጨጓሬ መከላከያ ዘዴ",
            "የቡና ፍሬ ማበስበስ በሽታ (CBD) መድሃኒት",
            "የእንሰት ባክቴሪያ አገርሽ በሽታ መፍትሄ",
            "የኬሚካል ፀረ-ተባይ አጠቃቀም ጥንቃቄ"
        )
    } else {
        listOf(
            "Why are my maize leaves turning yellow?",
            "How to treat Wheat Stem Rust (Ug99)?",
            "Fall armyworm organic ash remedy",
            "How to control Coffee Berry Disease (CBD)?",
            "Enset bacterial wilt quarantine rules",
            "Safe pesticide handling & intervals"
        )
    }

    LaunchedEffect(chatMessages.size) {
        if (chatMessages.isNotEmpty()) {
            listState.animateScrollToItem(chatMessages.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Chat Header
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.SmartToy,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = if (isAmharic) "ክሮፕዶክተር ረዳት (AI)" else "CropDoctor Assistant (AI)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = if (isAmharic) "የግብርናና የዕፅዋት ህክምና ባለሙያ" else "Ethiopian Agronomy & Plant Health Companion",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }

        // Quick Suggestion Chips
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(quickQuestions) { question ->
                SuggestionChip(
                    onClick = {
                        viewModel.sendUserMessage(question)
                    },
                    label = { Text(question, fontSize = 12.sp) },
                    modifier = Modifier.testTag("chat_chip_${question.take(6)}")
                )
            }
        }

        // Messages List
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(chatMessages) { message ->
                val isUser = message.isUser
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
                ) {
                    Surface(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomStart = if (isUser) 16.dp else 4.dp,
                            bottomEnd = if (isUser) 4.dp else 16.dp
                        ),
                        color = if (isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        tonalElevation = if (isUser) 0.dp else 2.dp,
                        border = if (!isUser) CardDefaults.outlinedCardBorder() else null,
                        modifier = Modifier.widthIn(max = 310.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = if (isAmharic) message.textAm else message.textEn,
                                color = if (isUser) Color.White else MaterialTheme.colorScheme.onSurface,
                                fontSize = 14.sp,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }

        // Bottom Input Field
        Surface(
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(bottom = 80.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = {
                        Text(
                            if (isAmharic) "የግብርና ጥያቄዎን እዚህ ይፃፉ..." else "Ask about your crop issue...",
                            fontSize = 13.sp
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("chat_input_field"),
                    shape = RoundedCornerShape(24.dp),
                    maxLines = 3
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            viewModel.sendUserMessage(inputText)
                            inputText = ""
                            coroutineScope.launch {
                                listState.animateScrollToItem(chatMessages.size)
                            }
                        }
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
                        .testTag("chat_send_btn")
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = Color.White
                    )
                }
            }
        }
    }
}
