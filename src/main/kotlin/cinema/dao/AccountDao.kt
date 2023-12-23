package cinema.dao

import cinema.entity.AccountEntity

interface AccountDao {
    /**
     * Найти и вернуть аккаунт
     */
    fun findAccount(login : String) : AccountEntity

    /**
     * Авторизовать пользователя
     */
    fun authorizeUser(login: String, password : String) : Boolean

    /**
     * Создать новый аккаунт для пользователя
     */
    fun createAccount(login: String, password: String)

    /**
     * Загрузить данные об имеющихся аккаунтах из файла
     */
    fun loadData()

    /**
     * Сохранить данные о существующих аккаунтах в файл
     */
    fun saveData()
}