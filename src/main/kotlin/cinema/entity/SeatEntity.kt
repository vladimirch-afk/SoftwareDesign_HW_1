package cinema.entity

data class SeatEntity (
    val number : Int,
    var isFree : Boolean,
    var isUsed : Boolean
)