package cinema.dao

import cinema.entity.MovieEntity

interface MovieDao {
    /**
     * Найти и вернуть фильм по его ID
     */
    fun findMovie(id : Int) : MovieEntity

    /**
     * Создать новый фильм
     */
    fun createMovie(name : String, description : String, duration: Long)

    /**
     * Отредактировать существующий фильм
     */
    fun editMovie(id : Int, name : String, description : String, duration: Long)

    /**
     * Вывести в консоль список фильмов
     */
    fun printMovies()

    /**
     * Загрузить данные о фильмах из файла
     */
    fun loadData()

    /**
     * Сохранить данные о существующих фильиах в файл
     */
    fun saveData()
}