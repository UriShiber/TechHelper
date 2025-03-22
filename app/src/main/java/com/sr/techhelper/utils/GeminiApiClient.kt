import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting
import com.google.ai.client.generativeai.type.TextPart
import com.google.ai.client.generativeai.type.generationConfig

class GeminiApiClient(apiKey: String) {
    val safetySettings = listOf(
        SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.MEDIUM_AND_ABOVE),
        SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.MEDIUM_AND_ABOVE)
    )
    val generationConfig = generationConfig {
        temperature = 0.9f
        topK = 1
        topP = 1f
        maxOutputTokens = 2048
    }
    private val model = GenerativeModel(
        modelName = "gemini-2.0-flash",
        apiKey = apiKey,
        generationConfig = generationConfig,
        safetySettings = safetySettings,
    )

    suspend fun generateTags(title: String, description: String): List<String> {
        val prompt = """
            Extract relevant tags for a post with the following title and description:
            Title: $title
            Description: $description
            Provide only tags separated by commas.
        """.trimIndent()

        val content = Content(parts = listOf(TextPart(prompt)))

        val response = model.generateContent(content)

        val responseText = response.text
        return responseText?.split(",")?.map { it.trim() } ?: emptyList()
    }
}