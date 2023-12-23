package cinema.entity

import java.time.LocalDateTime

data class SessionEntity (
    val id : Int,
    val seats: MutableList<SeatEntity>,
    var movie : MovieEntity,
    var startTime : LocalDateTime,
    var endTime : LocalDateTime,
)