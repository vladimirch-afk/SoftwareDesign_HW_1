package cinema.service

import cinema.dao.MovieDao
import cinema.dao.SessionDao
import java.time.LocalDateTime

class CinemaServiceImpl (sessionD: SessionDao, movieD: MovieDao) : CinemaService {
    private val sessionDao : SessionDao
    private val movieDao : MovieDao
    init {
        sessionDao = sessionD
        movieDao = movieD
    }

    override fun createSession(movieId: Int, startTime : LocalDateTime, endTime: LocalDateTime) {
        sessionDao.createSession(movieDao.findMovie(movieId), startTime, endTime)
    }

    override fun createMovie(name: String, description: String, duration: Long) {
        movieDao.createMovie(name, description, duration)
    }

    override fun sellTicket(sessionId: Int, seatNum: Int) {
        try {
            sessionDao.sellTicket(sessionId, seatNum)
        } catch (ex : RuntimeException) {
            println(ex.message)
            println()
        }
    }

    override fun returnTicket(sessionId: Int, seatNum: Int, currTime: LocalDateTime) {
        try {
            sessionDao.returnTicket(sessionId, seatNum, currTime)
        } catch (ex : RuntimeException) {
            println(ex.message)
            println()
        }
    }

    override fun displaySeatsStatus(sessionId: Int) {
        try {
            sessionDao.showSeatsStatus(sessionId)
        } catch (ex : RuntimeException) {
            println(ex.message)
            println()
        }
    }

    override fun editMovie(id : Int, name: String, description: String, duration : Long) {
        movieDao.editMovie(id, name, description, duration)
    }

    override fun editSession(sessionId: Int, movieId: Int, startTime: LocalDateTime, endTime: LocalDateTime) {
        sessionDao.editSession(sessionId, movieDao.findMovie(movieId), startTime, endTime)
    }

    override fun makeSeatUsed(sessionId: Int, seatNum: Int) {
        try {
            sessionDao.makeSeatUsed(sessionId, seatNum)
        } catch (ex : RuntimeException) {
            println(ex.message)
            println()
        }
    }

    override fun printSessions() {
        sessionDao.printSessions()
    }

    override fun printMovies() {
        movieDao.printMovies()
    }

    override fun loadData() {
        sessionDao.loadData()
        movieDao.loadData()
    }

    override fun saveData() {
        movieDao.saveData()
        sessionDao.saveData()
    }
}