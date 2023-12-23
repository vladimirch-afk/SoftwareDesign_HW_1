package cinema

import cinema.dao.RuntimeAccountDao
import cinema.dao.RuntimeMovieDao
import cinema.dao.RuntimeSessionDao
import cinema.service.AccountServiceImpl
import cinema.service.CinemaServiceImpl
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

/**
 * Вывести меню и принять ответ пользователя авторизованного пользователя
 */
fun displayMenu() {
    val options = arrayListOf("Buy ticket", "Return ticket", "Display free and sold seats",
        "Edit movie", "Edit session", "Check the customer", "Add new session", "Add new movie",
        "Print existing sessions", "Print existing movies", "Exit program")
    println("Select option [print a number]:")
    for (i in 0..<options.size) {
        println("#${i + 1} ${options[i]}")
    }
}

/**
 * Принять у пользователя решение
 */
fun getOptionFromUser() : Int {
    while (true) {
        try {
            val input = readln()
            val option = input.toInt()
            if (option in 1..11) {
                return option
            } else {
                throw RuntimeException()
            }
        } catch (ex: Exception) {
            println("Wrong input, try again:")
        }
    }
}

/**
 * Купить билут (сделать место занятым)
 */
fun buyTicket(service : CinemaServiceImpl) {
    println("Input session ID:")
    val sessionId : Int = readln().toInt()
    println("Input seat number:")
    val seatNum : Int = readln().toInt()
    println()
    service.sellTicket(sessionId, seatNum)
}

/**
 * Вернуть билет (освободить место)
 */
fun returnTicket(service: CinemaServiceImpl) {
    println("Input session ID:")
    val sessionId : Int = readln().toInt()
    println("Input seat number:")
    val seatNum : Int = readln().toInt()
    println()
    service.returnTicket(sessionId, seatNum, LocalDateTime.now())
}

/**
 * Вывести купленные, свободные и уже занятые места
 */
fun displaySeatStatus(service: CinemaServiceImpl) {
    println("Input session ID:")
    val sessionId : Int = readln().toInt()
    println()
    service.displaySeatsStatus(sessionId)
}

/**
 * Изменить существующий фильм
 */
fun editMovie(service: CinemaServiceImpl) {
    println("Input movie ID:")
    val movieId : Int = readln().toInt()
    println("Input new movie name:")
    val name = readln()
    println("Input new movie description:")
    val description = readln()
    println("Input movie duration in minutes:")
    val duration = readln().toLong()
    println()
    service.editMovie(movieId, name, description, duration)
    println("Movie was edited successfully")
}

/**
 * Изменить существующий сеанс
 */
fun editSession(service: CinemaServiceImpl) {
    println("Input session ID:")
    val sessionId : Int = readln().toInt()
    println("Input new movie ID:")
    val movieName = readln().toInt()
    println("Input new start time [dd/MM/yy:HH:mm]:")
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH:mm")
    val startTime = LocalDateTime.parse(readln(), formatter)
    println("Input new end time [dd/MM/yy:HH:mm]:")
    val endTime = LocalDateTime.parse(readln(), formatter)
    println()
    service.editSession(sessionId, movieName, startTime, endTime)
    println("Session was edited successfully")
}

/**
 * Отметить, что поситель пришел, сделать место уже занятым
 */
fun checkCustomer(service: CinemaServiceImpl) {
    println("Input session ID:")
    val sessionId : Int = readln().toInt()
    println("Input seat number:")
    val seatNum = readln().toInt()
    println()
    service.makeSeatUsed(sessionId, seatNum)
}

/**
 * Создать новый сеанс
 */
fun createSession(service: CinemaServiceImpl) {
    println("Input movie ID:")
    val movieId : Int = readln().toInt()
    println("Input start time [dd/MM/yy:HH:mm]:")
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH:mm")
    val startTime = LocalDateTime.parse(readln(), formatter)
    println("Input end time [dd/MM/yy:HH:mm]:")
    val endTime = LocalDateTime.parse(readln(), formatter)
    println()
    service.createSession(movieId, startTime, endTime)
}

/**
 * Создать новый фильм
 */
fun createMovie(service: CinemaServiceImpl) {
    println("Input movie name:")
    val name = readln()
    println("Input movie description:")
    val description = readln()
    println("Input movie duration in minutes:")
    val duration = readln().toLong()
    println()
    service.createMovie(name, description, duration)
}

/**
 * Завершить выполнение программы
 */
fun exit(serviceCinema: CinemaServiceImpl, serviceAccount : AccountServiceImpl) {
    serviceAccount.saveData()
    serviceCinema.saveData()
    println("Application is closed...")
    exitProcess(0)
}

/**
 * Получить данные пользователя из консоли и создать аккаунт
 */
fun createAccount(service: AccountServiceImpl) {
    println("Enter user login:")
    val login = readln()
    println("Enter password:")
    val password = readln()
    service.createAccount(login, password)
    println("Account was created")
    println("User is authorized")
    println()
}

/**
 * Принять данные пользователя из консоли и авторизовать его
 */
fun logIn(service: AccountServiceImpl) {
    println("Enter user login:")
    val login = readln()
    println("Enter password:")
    val password = readln()
    service.authorizeUser(login, password)
    println("User is authorized")
    println()
}

/**
 * Вывести меню авторизации и получить ответ пользователя
 */
fun authorizeUser(service: AccountServiceImpl) {
    while (true) {
        println("#1 Log in")
        println("#2 Sign in")
        try {
            val input = readln().toInt()
            if (input in 1..2) {
                if (input == 1) {
                    logIn(service)
                }
                if (input == 2) {
                    createAccount(service)
                }
                break
            } else {
                throw RuntimeException("Wrong input, try again...")
            }
        } catch (ex : Exception) {
            println(ex.message)
        }
    }
}

/**
 * Обработать ответ пользователя из меню
 */
fun processOption(option : Int, serviceCinema : CinemaServiceImpl, serviceAccount : AccountServiceImpl) {
    try {
        when (option) {
            1 -> {
                buyTicket(serviceCinema)
            }
            2 -> {
                returnTicket(serviceCinema)
            }
            3 -> {
                displaySeatStatus(serviceCinema)
            }
            4 -> {
                editMovie(serviceCinema)
            }
            5 -> {
                editSession(serviceCinema)
            }
            6 -> {
                checkCustomer(serviceCinema)
            }
            7 -> {
                createSession(serviceCinema)
            }
            8 -> {
                createMovie(serviceCinema)
            }
            9 -> {
                serviceCinema.printSessions()
            }
            10 -> {
                serviceCinema.printMovies()
            }
            11 -> {
                exit(serviceCinema, serviceAccount)
            }
        }
        return
    } catch (ex : Exception) {
        println(ex.message)
        println("Error occurred, try again\n")
    }
}

fun main(args: Array<String>) {
    val accountServiceImpl = AccountServiceImpl(RuntimeAccountDao())
    val cinemaServiceImpl = CinemaServiceImpl(RuntimeSessionDao(), RuntimeMovieDao())
    try {
        accountServiceImpl.loadData()
        cinemaServiceImpl.loadData()
    } catch (ex : Exception) {
        println("Unable to load data from files")
    }
    authorizeUser(accountServiceImpl)
    while (true) {
        displayMenu()
        val option = getOptionFromUser()
        processOption(option, cinemaServiceImpl, accountServiceImpl)
    }
}