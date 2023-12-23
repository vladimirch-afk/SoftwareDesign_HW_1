package cinema.service

interface AccountService {
    /**
     * Зашифровать пароль
     */
    fun encryptPassword(password: String) : String

    /**
     * Создать аккаунт пользователя и авторизовать его
     */
    fun createAccount(login : String, password: String)

    /**
     * Авторизовать пользователя
     */
    fun authorizeUser(login: String, password: String)

    /**
     * Загрузить данные об аккаунтах из файла
     */
    fun loadData()

    /**
     * Сохранить данные об аккаунтах в файлe
     */
    fun saveData()
}