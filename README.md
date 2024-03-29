﻿## Сервис сокращения ссылки - UrlShortCut

Сервис работает через REST API, реализован на SpringBoot.

Хранение данных выполняется в БД PostgreSQL.

Используется конструкция `Controller` `>` `Service` `>` `Repository`.

Используется DTO/DAO объекты, реализована валидация для моделей, поступающих в качестве входных данных на сервис.

#### Технологии проекта
![badge](https://img.shields.io/badge/Spring-Boot/Security/Data-green)
![badge](https://img.shields.io/badge/PostgreSQL-14-blue)

### Описание функционала сервиса

#### 1. Регистрация сайта.

Сервисом могут пользоваться разные сайты. Каждому сайту выдается пара логин/пароль.
 
Чтобы зарегистрировать сайт в систему нужно отправить запрос.

URL `POST /registration`

C телом JSON объекта `{site: "job4j.ru"}`

Ответ от сервера `{registration: true, login: "7_qEQC2", password: ">n8R$f055"}`

Флаг `registration` указывает, что регистрация выполнена или нет, true - если регистрируем новый сайт, false - если сайт уже есть в системе.

#### 2. Авторизация.

Для авторизации при регистрации URL сайта используется `JWT`.

Для получения `JWT` в систему нужно отправить запрос.

URL `POST /login`

C телом JSON объекта, логин/пароль полученные при регистрации сайта, `{"login": "7_qEQC2", "password": ">n8R$f055"}`

Ответ от сервера в заголовке `Authorization` будет содержать ключ `JWT` - `Bearer e25d31c5-db66-4cf2-85d4-8faa8c544ad6`

#### 3. Регистрация URL.

После того как пользователь зарегистрировал свой сайт, он может отправлять на сервис ссылки и получать преобразованные ссылки.

URL `POST /convert`

Заголовок `Authorization: Bearer e25d31c5-db66-4cf2-85d4-8faa8c544ad6`

C телом JSON объекта `{url: "https://job4j.ru/profile/exercise/106/task-view/532"}`

Ответом от сервера будет код - `{code: 8DxXHq}`.

Сокращенная ссылки будет образована адресом нашего сервиса + /redirect + код

`http://localhost:8080 + /redirect + 8DxXHq` `>` `http://localhost:8080/redirect/8DxXHq`

#### 4. Переадресация. Выполняется без авторизации.

При обращении к нашему сервису по сокращения ссылке, сервис вернет ассоциированный адрес и статус `302`.

URL `GET /redirect/8DxXHq`

Ответ от сервера в заголовке `HTTP CODE - 302 REDIRECT URL`

#### 5. Статистика.

В сервисе считается количество вызовов каждого адреса и конкретного сайта.

Вывод общей статистики.

URL `GET /statistic`

Ответом сервера будет JSON.

`[{"url": "https://job4j.ru/profile/exercise/106/task-view/532", "total": 7 }, {"url": "https://www.baeldung.com/spring-jpa-like-queries", "total": 11}]`

Вывод общей статистики по конкретному сайту.

URL `GET /statistic/job4j.ru`

Ответом сервера будет JSON.

`{"url": "https://job4j.ru/profile/exercise/106/task-view/532","total": 7}`
