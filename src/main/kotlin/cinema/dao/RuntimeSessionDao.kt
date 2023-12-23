package cinema.dao

import cinema.entity.MovieEntity
import cinema.entity.SeatEntity
import cinema.entity.SessionEntity
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.time.LocalDateTime
import kotlin.io.path.Path

class RuntimeSessionDao : SessionDao {
    @JsonProperty
    private var sessions = mutableListOf<SessionEntity>()
    private val seatDao = RuntimeSeatsDao()
    override fun createSession(movie: MovieEntity, startTime: LocalDateTime, endTime: LocalDateTime) {
        if (!(sessions.all { (it.startTime < startTime && it.endTime < startTime) ||
                    (it.endTime > startTime && it.endTime > endTime)})) {
            throw RuntimeException("Time of the new session intersects with time of another session")
        }
        if (startTime.plusMinutes(movie.duration) > endTime) {
            throw RuntimeException("Movie is longer than the session duration...")
        }
        val seats = mutableListOf<SeatEntity>()
        for (i in 1..30) {
            seats.add(SeatEntity(i, true, false))
        }
        sessions.add(SessionEntity(sessions.size, seats, movie, startTime, endTime))
        println("Session was created. ID: ${sessions.size-1}")
    }

    override fun findSession(sessionId: Int): SessionEntity {
        return sessions.find { it.id == sessionId } ?: throw RuntimeException("Session not found")
    }

    override fun editSession(sessionId: Int, movie: MovieEntity, startTime: LocalDateTime, endTime: LocalDateTime) {
        val session = findSession(sessionId)
        if (startTime.plusMinutes(movie.duration) > endTime) {
            throw RuntimeException("Movie is longer than the session duration...")
        }
        session.movie = movie
        session.startTime = startTime
        session.endTime = endTime
    }

    override fun sellTicket(sessionId: Int, seatNum : Int) {
        seatDao.makeSeatBusy(findSession(sessionId), seatNum)
    }

    override fun returnTicket(sessionId: Int, seatNum: Int, currTime: LocalDateTime) {
        if (currTime > LocalDateTime.now()) {
            throw RuntimeException("Ticket cannot be returned, session has already started")
        } else {
            seatDao.makeSeatFree(findSession(sessionId), seatNum)
        }
    }

    override fun showSeatsStatus(sessionId: Int) {
        seatDao.showSeatsStatus(findSession(sessionId))
    }

    override fun makeSeatUsed(sessionId: Int, seatNum : Int) {
        seatDao.makeSeatUsed(findSession(sessionId), seatNum)
    }

    override fun printSessions() {
        println("Existing sessions:")
        for (item in sessions) {
            println("ID: ${item.id}, Movie: ${item.movie.name}, Start time: ${item.startTime}, End time: ${item.endTime}")
        }
        println()
    }

    override fun loadData() {
        val directory = "data"
        val fileName = "sessions.json"
        File(directory).mkdirs()
        val file = Path(directory, fileName).toFile()
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.registerKotlinModule()
        sessions = objectMapper.readValue<MutableList<SessionEntity>>(file.readText())
    }

    override fun saveData() {
        val directory = "data"
        val fileName = "sessions.json"
        File(directory).mkdirs()
        val file = Path(directory, fileName).toFile()
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.registerKotlinModule()
        objectMapper.writeValue(file, sessions)
    }
}