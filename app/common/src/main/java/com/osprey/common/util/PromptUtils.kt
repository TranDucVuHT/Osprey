package com.wodox.common.util

object PromptUtils {

    private val workKeywords = listOf(
        "task", "work", "job", "project", "todo",
        "assignment", "deadline", "due", "plan"
    )

    fun getPrompt(input: String): String {
        val lowerInput = input.lowercase()

        val isWorkRelated = workKeywords.any { keyword ->
            lowerInput.contains(keyword)
        }

        return if (isWorkRelated) {
            """
You are a professional AI task management assistant.
Analyze and summarize the following task or work-related information in a clear, detailed paragraph format:

$input

Your summary should include:
- Task urgency level and priority assessment
- Current status and progress
- Key dates (creation date, start date, due date if available)
- Important tags or categories
- Assignment details
- Any attachments or additional information
- Recommended next steps

Write in a natural, flowing paragraph style. Be specific about dates using full formats (e.g., "November 3, 2025"). 
Use professional, clear, and concise language.
            """.trimIndent()
        } else {
            """
You are a helpful AI assistant. Respond naturally and helpfully to the following message:

$input
            """.trimIndent()
        }
    }
}
