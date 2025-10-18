<h1 align="center">SpaceGame</h1>
<h4 align="center"><i>SpaceGame — Юмористический текстовый квест</i></h4>

![](https://kartinki.pics/pics/uploads/posts/2022-08/1659533302_17-kartinkin-net-p-terminator-art-oboi-18.jpg)

<h4 align="center"><i>Проект создан для демонстрации базовых навыков работы с Java, Servlets, !!!!!!!! и MySQL</i></h4>


---


<h2 align="center">Описание проекта</h2>
- Тип проекта: пет-проект  
- Тип игры: юмористический текстовый квест с отсылками к поп-культуре, стилистика Космических Рейнджеров, с небольшим апгрейдом в сторону инвентаря и статов  
- Сюжет: альтернативная история появления Терминатора в прошлом с целью убийства Сары Коннор  
- Состояние: в стадии разработки


---


<h2 align="center">Требования к окружению</h2>
- Java: 17  
- Tomcat: 11  
- MySQL: 8.0.42  
- Maven: 3.9.11  
- Библиотеки: стандартные Jakarta EE (Servlet API, JPA/Hibernate), все зависимости указаны в pom.xml


---


<h2 align="center">Структура проекта</h2>

<h3 align="center">src/main/java/com/spacegame/</h3>

- 🗂️ controller (сервлеты)
- 🗂️ dao (DAO-классы)
- 🗂️ entity (сущности)
- 🗂️ service (сервисы)
- 🗂️ util (EntityManagerFactory и утилиты)


<h3 align="center">src/main/resources/META-INF/</h3>

- 📄 persistence.xml


<h3 align="center">src/main/webapp/</h3>

- 📄 login.jsp
- 📄 register.jsp
- 📄 register-success.jsp
- 🗂️ css (стили)
- 🗂️ images (картинки)
- 🗂️ WEB-INF (web.xml)
- 🗂️ jsp (все JSP-файлы, обслуживаемые сервлетами)


---


<h2 align="center">Настройка базы данных</h2>

1. Создать базу в MySQL:
```sql
CREATE DATABASE SpaceGame CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Hibernate/JPA создаст таблицы (users, players, player_inventory) автоматически при первом запуске приложения

---

<h2 align="center">Как запустить проект</h2>

1. Клонировать проект:
```bash
git clone https://github.com/TeddyFloodK-2SO/SpaceGame.git
```
2. Настроить SDK
3. Собрать проект:
```bash
mvn clean package
```
4. Скопировать из target/mySR.war в папку webapps TomCat.
5. Запустить TomCat
6. Браузер откроет страницу автоматически.  
   Если нет запустите:  
   http://localhost:8080/mySR/

   
---


<h2 align="center">Структура базы</h2>

 • users — таблица аккаунтов (id, username, password и др.)
 
 • players — таблица персонажей (id, health, money, currentImage, currentLocation, user_id)
 
 • player_inventory — таблица инвентаря (player_id, item) через @ElementCollection
 
 • Связь: players.user_id → users.id (один пользователь → один персонаж)


---

<h2 align="center">Особенности проекта</h2>

 • JSP в WEB-INF/jsp/ — нет прямого доступа из браузера
 
 • Все страницы обслуживаются сервлетами
 
 • Проверка сессии для защиты страниц
 
 • Прогресс игрока хранится в базе данных


---


<h2 align="center">🧾 GitHub</h2>

- Репозиторий: [https://github.com/TeddyFloodK-2SO/SpaceGame](https://github.com/TeddyFloodK-2SO/SpaceGame)
