package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.PreloadedData
import com.example.data.model.KnowledgeArticle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KnowledgeCenterScreen(
    isAmharic: Boolean
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("All") }
    var viewingArticle by remember { mutableStateOf<KnowledgeArticle?>(null) }

    val categories = listOf("All", "Disease Management", "Safe Pesticides", "Soil Fertility")

    val filteredArticles = PreloadedData.KNOWLEDGE_ARTICLES.filter { article ->
        val matchesCategory = selectedCategory == "All" || article.category == selectedCategory
        val query = searchQuery.lowercase()
        val matchesSearch = query.isEmpty() ||
                article.titleEn.lowercase().contains(query) ||
                article.titleAm.lowercase().contains(query) ||
                article.cropName.lowercase().contains(query)
        matchesCategory && matchesSearch
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
    ) {
        // Header
        item {
            Text(
                text = if (isAmharic) "የግብርና እውቀት ማዕከል" else "Agricultural Knowledge Center",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = if (isAmharic) "ስለ ሰብል በሽታዎች፣ አያያዝና ፀረ-ተባይ ጥንቃቄ ከመስመር ውጭ የሚሰሩ መመሪያዎች" else "Searchable disease guides, IPM practices & certified chemical safety",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp, bottom = 14.dp)
            )

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_articles_input"),
                placeholder = {
                    Text(if (isAmharic) "መመሪያዎችን ይፈልጉ (ስንዴ፣ ቡና፣ ኬሚካል...)" else "Search guides (Wheat, Enset, Fungicides...)", fontSize = 13.sp)
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        // Category Filter Chips
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(categories) { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = category },
                        label = { Text(category, fontSize = 12.sp) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Articles List
        items(filteredArticles) { article ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { viewingArticle = article }
                    .testTag("article_card_${article.id}"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = article.category,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CloudDone,
                                contentDescription = "Offline Ready",
                                tint = Color(0xFF2E7D32),
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Offline Ready",
                                fontSize = 10.sp,
                                color = Color(0xFF2E7D32),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (isAmharic) article.titleAm else article.titleEn,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = if (isAmharic) article.summaryAm else article.summaryEn,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 17.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "⏱ ${article.readTimeMinutes} min read • Target: ${article.cropName}",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.outline
                        )

                        Text(
                            text = if (isAmharic) "ሙሉውን አንብብ →" else "Read Guide →",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }

    // Article Reader Modal
    viewingArticle?.let { article ->
        AlertDialog(
            onDismissRequest = { viewingArticle = null },
            title = {
                Text(
                    text = if (isAmharic) article.titleAm else article.titleEn,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    item {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Category: ${article.category} • Target: ${article.cropName}",
                                fontSize = 11.sp,
                                modifier = Modifier.padding(8.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = if (isAmharic) article.fullContentAm else article.fullContentEn,
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = if (isAmharic) "ዋና ዋና ነጥቦች፡" else "Key Takeaways:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        val takeaways = if (isAmharic) article.keyTakeawaysAm else article.keyTakeawaysEn
                        takeaways.forEach { item ->
                            Text(
                                text = "• $item",
                                fontSize = 12.sp,
                                modifier = Modifier.padding(vertical = 2.dp),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(onClick = { viewingArticle = null }) {
                    Text(if (isAmharic) "ዝጋ" else "Close")
                }
            }
        )
    }
}
