# Ограниченный контекст и ключевая сущность

   _**Ограниченный контекст**_: создание и просмотр пользователями рецептов приготовления блюд

   _**Смысловое ядро (ключевая сущность)**_: recipe (рецепт)

## Описание сущности recipe

1. **Title**
2. **Description**
3. **Owner**
4. **isPublished**: true/false (уровени видимости - доступен всем/приватный рецепт)
5. **CookingTime**: время приготовления блюда
6. **ServingSize**: размер порции
7. **Category**: завтрак/обед/ужин
8. **Cuisine**: название кухни (европейская, азиатская)
9. **Rating**: рейтинг на основе оценок пользователей
10. **CreatedAt**: дата создания рецепта
11. **UpdatedAt**: дата обновления рецепта
12. **PublishedAt**: дата публикации рецепта (актуально для опубликованных рецептов)

### Описание сущности ingredient
1. **Title**
2. **Unit**: единица измерения
3. **Count**: количество
4. **RecipeId**: уникальный идентификатор рецепта
5. **Instructions**: шаги приготовления
6. **isVegetarian**: true/false (отсутствие/наличие продуктов животного происхождения)

### Описание сущности instruction
1. **Title**
2. **Order**: порядковый номер
3. **Description**
4. **RecipeId**: уникальный идентификатор рецепта
5. **Photo**: фотоизображение

### Описание сущности shoppingList (опционально)
1. **Title**
2. **Ingredients**: список названий продуктов
3. **CreatedAt**: дата создания списка
4. **UpdatedAt**: дата изменения списка

## Операции над сущностью recipe (эндпониты)

1. CRUDS (create, read, update, delete, search) для рецептов (recipe)
2. CRUDS (create, read, update, delete, search) для списков покупок (shoppingList) (опционально)
