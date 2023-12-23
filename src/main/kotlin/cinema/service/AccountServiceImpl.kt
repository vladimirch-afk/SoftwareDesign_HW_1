package cinema.service

import cinema.dao.AccountDao
import java.nio.charset.Charset
import java.security.MessageDigest

class AccountServiceImpl (accountD : AccountDao) : AccountService {
    private val accountDao : AccountDao;
    init {
        accountDao = accountD
    }

    override fun encryptPassword(password: String): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.update(password.toByteArray(Charset.defaultCharset()))
        val array = messageDigest.digest()
        return array.joinToString ("") {"%02x".format(it)}
    }

    override fun createAccount(login: String, password: String) {
        val encPassword = encryptPassword(password)
        accountDao.createAccount(login, encPassword)
    }

    override fun authorizeUser(login: String, password: String) {
        val result = accountDao.authorizeUser(login, encryptPassword(password))
        if (result) {
            println("User $login was logged in successfully")
        } else {
            throw RuntimeException("Wrong password")
        }
    }

    override fun loadData() {
        accountDao.loadData()
    }

    override fun saveData() {
        accountDao.saveData()
    }
}