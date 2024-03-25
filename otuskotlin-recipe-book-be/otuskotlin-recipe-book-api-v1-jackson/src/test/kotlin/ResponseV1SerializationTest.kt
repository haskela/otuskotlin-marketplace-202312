package ru.otus.otuskotlin.marketplace.api.v1


import apiV1Mapper
import ru.otus.otuskotlin.recipe.book.api.v1.models.*
import java.io.File
import java.util.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseV1SerializationTest {
    private val recipeId = UUID.randomUUID().toString()
    private val instructionId = UUID.randomUUID().toString()
    private val ingredientId = UUID.randomUUID().toString()

    private val testPhotoPath = "${System.getProperty("user.dir")}/build/test-results"

    private val response = RecipeCreateResponse(
        recipe = RecipeResponseObject(
            id = recipeId,
            title = "Recipe title",
            description = "Recipe description",
            category = Category.BREAKFAST,
            visibility = RecipeVisibility.PUBLIC,
            cuisine = Cuisine.GREEK,
            servingSize = 2,
            cookingTime = 30,
            instructions = listOf(
                InstructionObject(
                    id = instructionId,
                    title = "Recipe instruction",
                    order = 1,
                    description = "Instruction description",
                    photo = File("$testPhotoPath/testPhoto").apply {
                        writeBytes(
                            Base64.getDecoder()
                                .decode("iVBORw0KGgoAAAANSUhEUgAAAAYAAAAGCAYAAADgzO9IAAAASklEQVQIW2NkgIArQHweiBOB+A9IgBEqsQRIR0H5kkD6BUwCJN8JxGVADFIUiyxhARRgAeI8IA5DloCaytALZBSjS0wACm4B4j0AdUgLB6b86N4AAAAASUVORK5CYII="),
                        )
                    }
                )),
            ingredients = listOf(
                IngredientObject(
                    id = ingredientId,
                    title = "Recipe ingredient",
                    unit = IngredientUnit.ITEM,
                    count = 2,
                    isVegetarian = false,
                )
            )
        ),
        result = ResponseResult.SUCCESS
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"result\":\\s*\"success\""))

        assertContains(json, Regex("\"id\":\\s*\"$recipeId\""))
        assertContains(json, Regex("\"title\":\\s*\"Recipe title\""))
        assertContains(json, Regex("\"description\":\\s*\"Recipe description\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
        assertContains(json, Regex("\"category\":\\s*\"breakfast\""))
        assertContains(json, Regex("\"visibility\":\\s*\"public\""))
        assertContains(json, Regex("\"cuisine\":\\s*\"Greek\""))
        assertContains(json, Regex("\"servingSize\":\\s*2"))
        assertContains(json, Regex("\"cookingTime\":\\s*30"))

        assertContains(json, Regex("\"id\":\\s*\"$ingredientId\""))
        assertContains(json, Regex("\"title\":\\s*\"Recipe ingredient\""))
        assertContains(json, Regex("\"unit\":\\s*\"item\""))
        assertContains(json, Regex("\"count\":\\s*2"))
        assertContains(json, Regex("\"isVegetarian\":\\s*false"))

        assertContains(json, Regex("\"id\":\\s*\"$instructionId\""))
        assertContains(json, Regex("\"title\":\\s*\"Recipe instruction\""))
        assertContains(json, Regex("\"order\":\\s*1"))
        assertContains(json, Regex("\"description\":\\s*\"Instruction description\""))
        assertContains(json, Regex("\"photo\":\\s*\"$testPhotoPath/testPhoto\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IResponse::class.java) as RecipeCreateResponse

        assertEquals(response, obj)
    }
}
