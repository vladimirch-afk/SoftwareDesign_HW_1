package cinema.dao

import cinema.entity.MovieEntity
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import kotlin.io.path.Path

class RuntimeMovieDao : MovieDao {
    private var movies = mutableListOf<MovieEntity>()
    override fun findMovie(id: Int) : MovieEntity{
        return movies.find { it.id == id } ?: throw RuntimeException("Movie with this ID does not exist")
    }

    override fun createMovie(name: String, description: String, duration: Long) {
        movies.add(MovieEntity(movies.size, name, description, duration))
        println("Movie was created. ID: ${movies.size-1}")
    }

    override fun editMovie(id: Int, name: String, description: String, duration: Long) {
        val movie = findMovie(id)
        movie.name = name
        movie.description = description
        movie.duration = duration
    }

    override fun printMovies() {
        println("Existing movies:")
        for (item in movies) {
            println("ID: ${item.id}, Name: ${item.name}, Description: ${item.description}, Duration: ${item.duration}")
        }
        println()
    }

    override fun loadData() {
        val directory = "data"
        val fileName = "movies.json"
        File(directory).mkdirs()
        val file = Path(directory, fileName).toFile()
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.registerKotlinModule()
        movies = objectMapper.readValue<MutableList<MovieEntity>>(file.readText())
    }

    override fun saveData() {
        val directory = "data"
        val fileName = "movies.json"
        File(directory).mkdirs()
        val file = Path(directory, fileName).toFile()
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.registerKotlinModule()
        objectMapper.writeValue(file, movies)
    }
}