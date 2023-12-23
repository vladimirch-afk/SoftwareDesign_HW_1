# Консольное приложение для работников кинотеатра
## О работе приложения
Данное приложение реализует основной фукционал задания и дополнительный функционал по авторизации пользователя с шифрованием паролей. Программа была реализована по принципам ООП и SOLID. Данные об аккаунтах, сеансах и фильмах хранятся в файлах в папке data в формате JSON, ввиду его удобства чтения человеком.
## Зависимости
В build.gradle.kts были указаны дополнительные dependencies "com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.0", "com.fasterxml.jackson.module:jackson-module-kotlin:2.12.0", "com.fasterxml.jackson.datatype:jackson-datatype-jsr310", они были добавлены для реализации сериализации данных в JSON и записи их в файлы.
## Работа приложения
При запуске приложения предлагают авторизоваться или зарегестрироваться <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/6f4b2d2d-9c31-41d2-b988-8afc567a6ae7) <br/>
При выборе регистрации надо ввести логин нового пользователя и пароль <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/e30d217d-e36a-4e43-a057-12fe1e374577) <br/>
При выборе авторизации надо ввести логин и пароль существующего пользователя <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/b722a08d-18e5-4b6e-b7e7-1b0bf2028615) <br/>
После авторизации выводится меню, где надо ввести код выполняемого действия <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/81299207-523d-4f96-afc8-101a44180ee1) <br/>
Про выборе 1 - продажа билета <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/957aa9c7-611a-4ce1-8808-3b97412c5833) <br/>
При выборе 2 - возвращение билета (если место свободно, посетитель уже пришел на это место или сеанс уже начался, то билет не будет возвращен, о чем будет написано в консоли) <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/ec94e042-16e9-495b-8188-66b3b0eaaf8e) <br/>
При выборе 3 - вывод статуса мест будет запрошен ID сеанса и в консоль выведутся списки свободных мест, купленных мест и уже занятых <br/>
При выборе 4 - редактирование существующего фильма <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/f4c88d23-7f71-4745-8d76-77e6a57b1d5c) <br/>
При выборе 5 - редактирование существующего сеанса <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/24b0e429-5ea5-49dc-8b55-e41a5e29dc38) <br/>
При выборе 6 - отметить, что посетитель пришел на место <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/07056995-8cd7-4085-bf12-914ceeb7e26b) <br/>
При выборе 7 - добавить новый сеанс <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/f3b6fafb-d793-4a36-bb04-d4f424cf1824) <br/>
При выборе 8 - добавить новый фильм <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/4b80d920-7bd2-4043-ba27-0a4c6c7c5d97) <br/>
При выборе 9 - вывод всех существующих сеансов <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/90f617ab-f265-4de2-952c-4e50a561fd26) <br/>
При выборе 10 - вывод всех существующих фильмов <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/7c69ab2e-abb7-483d-b6bd-201f84894670) <br/>
При выборе 11 - выход из программы <br/>
![image](https://github.com/vladimirch-afk/SoftwareDesign_HW_1/assets/93833696/eb2c6cf0-e35a-4c35-a24e-a10602f61307) <br/>
## Сообщения об ошибках
Ошибки выводятся в консоль во многих случаях. Когда пользователь вводит строку, которая не может быть приобразована к нужному типу (например, целочисленныму значению или LocalDateTime), когда время нового сеанса накладывается на время другого сеанса,
когда длительность указанного в новом сеансе фильма больше продолжительности самого сеанса, когда не найдены указанные пользователем фильмы, сеансы, места.
