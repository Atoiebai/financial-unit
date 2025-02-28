Swagger по адресу: http://localhost:8080/swagger-ui.html 

Моменты которые можно улучшить

1. Сделать нормальное логирование, с большей детализацией, уровнями и форматом (в идеале направлять логи в какую нибудь систему)
2. Security, заложена ролевая модель, но в самом сервисе никак не используется, сделано через Basic для упрощения (в идеале поднять кейклок и настроить JWT, как минимум)
3. Exception handling и Валидация, можно вывести стандартный механизм обработки ошибок и формат ошибок
4. Кеш для оптимизации 
5. Запросы к БД , выполнено через JPA, при опр требованиях можно не затачиваться на интерфейсы Спринга и вывести свои, чтобы легко менять слой доступ к данным, для оптимизации использовал бы JOOQ / JdbcTemplate

Уточнения: 
1) Использован подход package-by-feature, по мере возможности старался не заводить публичные классы, инкапсулируя логику в сервисах, но не везде это возможно/уместно
2) В виду плавающих требований отталкивался от абстракций + того что в моем понимании могло бы иметь смысл в рамках задачи
3) Использован мапстракт в угоду перфоманса 
4) Использована мультистейдж сборка из с кешированием зависимостей, для оптимизации размера образа
5) Использован флайвей, то бишь просто и лаконично, нативный скл
6) mock_data.sql файл с данными, в виде инсертов - для наполнения базы. Дефолтный юзер islam:islam :)


Для запуска проекта достаточно команды docker compose up --build, доступен удаленный дебаг или же можно развернуть локально (profile=local) используя только постгрес из compose файла



Запросы: 
1) Создание юзера CREATE USER (При создании первого юзера, требуется отключить security, если ранее не накатывали юзера напрямую в БД через mock_data.sql например), в таком случае запрос будет: 

`   curl --location 'localhost:8080/api/v1/users' \
   --header 'Content-Type: application/json' \
   --header 'Cookie: JSESSIONID=06D1A4501B28A844DB7DE79E47F5E98A' \
   --data '{
   "username" : "admin",
   "password" : "admin",
   "state" : "ACTIVE"
   }'
`

В случае если секьюрити остается включенным, запрос должен включать Authorization header
Если учетная запись islam:islam записана в бд
Запрос будет следующий: 

`curl --location 'localhost:8080/api/v1/users' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=06D1A4501B28A844DB7DE79E47F5E98A; JSESSIONID=BDF0BF25364927E626C0B3C3F0728E13' \
--header 'Authorization: Basic aXNsYW06aXNsYW0=' \
--data '{
    "username" : "admin",
    "password" : "admin",
    "state" : "ACTIVE"
}'`

2) Получение списка юзеров: 

`curl --location 'localhost:8080/api/v1/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic aXNsYW06aXNsYW0=' \
--header 'Cookie: JSESSIONID=06D1A4501B28A844DB7DE79E47F5E98A; JSESSIONID=BDF0BF25364927E626C0B3C3F0728E13' \
--data ''`

3) Изменение статуса юзера:

`curl --location --request PUT 'localhost:8080/api/v1/users/change-state' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic aXNsYW06aXNsYW0=' \
--header 'Cookie: JSESSIONID=06D1A4501B28A844DB7DE79E47F5E98A; JSESSIONID=BDF0BF25364927E626C0B3C3F0728E13' \
--data '{
    "username" : "admin",
    "state" : "BLOCKED"
}'`

4) Изменение пароля юзера: 

`curl --location --request PUT 'localhost:8080/api/v1/users/change-password' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic aXNsYW06aXNsYW0==' \
--header 'Cookie: JSESSIONID=06D1A4501B28A844DB7DE79E47F5E98A' \
--data '{
    "username" : "admin",
    "newPassword" : "notdmin"
}'`

5) Создание транзакции 

`curl --location 'http://localhost:8080/api/v1/transactions' \
   --header 'Content-Type: application/json' \
   --header 'Authorization: Basic aXNsYW06aXNsYW0=' \
   --header 'Cookie: JSESSIONID=BDF0BF25364927E626C0B3C3F0728E13' \
   --data '{
   "amount" : 100,
   "type" : "CREDIT"
   }'`

6) Получение списка транзакций по юзеру (и интервалу дат):

`curl --location --request GET 'http://localhost:8080/api/v1/transactions?from=2023-10-15T14%3A30%3A45&to=2026-10-15T14%3A30%3A44' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic aXNsYW06aXNsYW0=' \
--header 'Cookie: JSESSIONID=BDF0BF25364927E626C0B3C3F0728E13' \
--data '{
"amount" : 100,
"type" : "CREDIT"
}'`


