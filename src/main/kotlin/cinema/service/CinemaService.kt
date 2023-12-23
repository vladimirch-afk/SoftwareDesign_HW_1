package cinema.service

import java.time.LocalDateTime

interface CinemaService {
    /**
     * Создать новый сеанс
     */
    fun createSession(movieId : Int, startTime : LocalDateTime, endTime: LocalDateTime)

    /**
     * Создать новый фильм
     */
    fun createMovie(name: String, description: String, duration: Long)

    /**
     * Купить билет (изменить статус места на купленное)
     */
    fun sellTicket(sessionId : Int, seatNum : Int)

    /**
     * Вернуть билет (освободить место)
     */
    fun returnTicket(sessionId : Int, seatNum : Int, currTime : LocalDateTime)

    /**
     * Вывести купленные, свободные и уже занятые места в сеансе с указанным ID
     */
    fun displaySeatsStatus(sessionId: Int)

    /**
     * Изменить фильм по ID
     */
    fun editMovie(id : Int, name: String, description: String, duration : Long)

    /**
     * Изменитт сеанс по ID
     */
    fun editSession(sessionId: Int, movieId: Int, startTime: LocalDateTime, endTime: LocalDateTime)

    /**
     * Отметить, что посетитель уже пришел на место
     */
    fun makeSeatUsed(sessionId: Int, seatNum : Int)

    /**
     * Вывести существующие сеансы
     */
    fun printSessions()

    /**
     * Вывести существующие фильмы
     */
    fun printMovies()

    /**
     * Загрузить данные о фильмах и сеансах из файла
     */
    fun loadData()

    /**
     * Сохранить данные о фильмах и сеансах в файл
     */
    fun saveData()
}