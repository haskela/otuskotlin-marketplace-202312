package ru.otus.otuskotlin.marketplace.api.v1

import apiV1Mapper
import ru.otus.otuskotlin.recipe.book.api.v1.models.*
import java.io.File
import java.util.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestV1SerializationTest {

    private val testPhotoPath = "${System.getProperty("user.dir")}/build/test-results"

    private val request = RecipeCreateRequest(
        debug = RecipeDebug(
            mode = RecipeRequestDebugMode.STUB,
            stub = RecipeRequestDebugStubs.BAD_RECIPE_ID
        ),
        recipe = RecipeCreateObject(
            title = "Recipe title",
            description = "Recipe description",
            category = Category.BREAKFAST,
            visibility = RecipeVisibility.PUBLIC,
            cuisine = Cuisine.GREEK,
            servingSize = 2,
            cookingTime = 30,
            instructions = listOf(
                BaseInstruction(
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
                BaseIngredient(
                    title = "Recipe ingredient",
                    unit = IngredientUnit.ITEM,
                    count = 2,
                    isVegetarian = false,
                )
            )
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"title\":\\s*\"Recipe title\""))
        assertContains(json, Regex("\"description\":\\s*\"Recipe description\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"badRecipeId\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
        assertContains(json, Regex("\"category\":\\s*\"breakfast\""))
        assertContains(json, Regex("\"visibility\":\\s*\"public\""))
        assertContains(json, Regex("\"cuisine\":\\s*\"Greek\""))
        assertContains(json, Regex("\"servingSize\":\\s*2"))
        assertContains(json, Regex("\"cookingTime\":\\s*30"))
        assertContains(json, Regex("\"title\":\\s*\"Recipe ingredient\""))
        assertContains(json, Regex("\"unit\":\\s*\"item\""))
        assertContains(json, Regex("\"count\":\\s*2"))
        assertContains(json, Regex("\"isVegetarian\":\\s*false"))
        assertContains(json, Regex("\"title\":\\s*\"Recipe instruction\""))
        assertContains(json, Regex("\"order\":\\s*1"))
        assertContains(json, Regex("\"description\":\\s*\"Instruction description\""))
        assertContains(json, Regex("\"photo\":\\s*\"$testPhotoPath/testPhoto\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IRequest::class.java) as RecipeCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"recipe": null}
        """.trimIndent()
        val obj = apiV1Mapper.readValue(jsonString, RecipeCreateRequest::class.java)

        assertEquals(null, obj.recipe)
    }
}
