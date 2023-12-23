package cinema.dao

import cinema.entity.MovieEntity
import cinema.entity.SessionEntity
import java.time.LocalDateTime

interface SessionDao {
    /**
     * Создать сеанс
     */
    fun createSession(movie : MovieEntity, startTime : LocalDateTime, endTime: LocalDateTime)

    /**
     * Найти и вернуть сеанс по ID
     */
    fun findSession(sessionId: Int) : SessionEntity
    /**
     * Изменить существу
      */
    fun editSession(sessionId: Int, movie : MovieEntity, startTime: LocalDateTime, endTime: LocalDateTime)

    /**
     * Сделать место купленным
     */
    fun sellTicket(sessionId: Int, seatNum : Int)

    /**
     * Освободить место
     */
    fun returnTicket(sessionId: Int, seatNum : Int, currTime : LocalDateTime)

    /**
     * Показать купленные, свободные и уже занятые места
     */
    fun showSeatsStatus(sessionId: Int)

    /**
     * Сделать место занятым
     */
    fun makeSeatUsed(sessionId: Int, seatNum : Int)

    /**
     * Вывести в консоль существующие сеансы
     */
    fun printSessions()

    /**
     * Загрузить сеансы из файла
     */
    fun loadData()

    /**
     * Сохранить сеансы в файл
     */
    fun saveData()
}