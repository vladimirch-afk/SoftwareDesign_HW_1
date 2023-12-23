package cinema.dao

import cinema.entity.SeatEntity
import cinema.entity.SessionEntity

interface SeatsDao {
    /**
     * Найти и вернуть сидение по его номеру и ID сеанса
     */
    fun findSeat(session: SessionEntity, seatNum : Int) : SeatEntity

    /**
     * Сделать кресло занятым (продать кресло)
     */
    fun makeSeatBusy(session: SessionEntity, seatNum: Int)

    /**
     * Освободить место
     */
    fun makeSeatFree(session: SessionEntity, seatNum: Int)

    /**
     * Отметить, что посетитель уже пришел и занял место
     */
    fun makeSeatUsed(session: SessionEntity, seatNum: Int)

    /**
     * Вывести список свободных, купленных и уже занятых мест
     */
    fun showSeatsStatus(session: SessionEntity)
}