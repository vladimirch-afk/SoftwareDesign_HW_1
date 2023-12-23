package cinema.dao

import cinema.entity.AccountEntity
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import kotlin.io.path.Path

class RuntimeAccountDao : AccountDao {
    private var accounts = mutableListOf<AccountEntity>()
    override fun findAccount(login: String): AccountEntity {
        return accounts.find { it.login == login } ?: throw RuntimeException("User not found")
    }

    override fun authorizeUser(login: String, password: String) : Boolean {
        val acc = findAccount(login)
        return acc.password == (password ?: throw RuntimeException("Password is wrong"))
    }

    override fun createAccount(login: String, password: String) {
        if (accounts.any {it.login == login}) {
            throw RuntimeException("User with this login already exists")
        }
        accounts.add(AccountEntity(login, password))
    }

    override fun loadData() {
        val directory = "data"
        val fileName = "accounts.json"
        File(directory).mkdirs()
        val file = Path(directory, fileName).toFile()
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.registerKotlinModule()
        accounts = objectMapper.readValue<MutableList<AccountEntity>>(file.readText())
    }

    override fun saveData() {
        val directory = "data"
        val fileName = "accounts.json"
        File(directory).mkdirs()
        val file = Path(directory, fileName).toFile()
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.registerKotlinModule()
        objectMapper.writeValue(file, accounts)
    }
}