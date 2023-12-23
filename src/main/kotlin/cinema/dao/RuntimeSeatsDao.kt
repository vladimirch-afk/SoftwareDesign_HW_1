package cinema.dao

import cinema.entity.SeatEntity
import cinema.entity.SessionEntity

class RuntimeSeatsDao : SeatsDao {
    override fun findSeat(session: SessionEntity, seatNum: Int) : SeatEntity {
        return session.seats.find { it.number == seatNum } ?: throw RuntimeException("Seat is not found")
    }

    override fun makeSeatBusy(session: SessionEntity, seatNum: Int) {
        val seat = findSeat(session, seatNum)
        if (seat.isFree) {
            seat.isFree = false
        } else {
            throw RuntimeException("Seat is already sold")
        }
        println("Seat was sold")
    }

    override fun makeSeatFree(session: SessionEntity, seatNum: Int) {
        val seat = findSeat(session, seatNum)
        if (seat.isUsed) {
            throw RuntimeException("Customer has already come to the session")
        }
        if (seat.isFree) {
            throw RuntimeException("Seat is already free")
        }
        seat.isFree = true
        println("Seat was made free")
    }

    override fun makeSeatUsed(session: SessionEntity, seatNum: Int) {
        val seat = findSeat(session, seatNum)
        if (seat.isFree) {
            throw RuntimeException("This seat wasn't sold to be used")
        } else {
            if (seat.isUsed) {
                throw RuntimeException("Someone is already sitting on the seat")
            } else {
                seat.isUsed = true
            }
        }
    }

    override fun showSeatsStatus(session: SessionEntity) {
        println("Free seats:")
        for (seat in session.seats) {
            if (seat.isFree) {
                println(seat.number)
            }
        }
        println("Bought seats:")
        for (seat in session.seats) {
            if (!seat.isFree) {
                println(seat.number)
            }
        }
        println("Checked seats:")
        for (seat in session.seats) {
            if (seat.isUsed) {
                println(seat.number)
            }
        }
        println()
    }
}