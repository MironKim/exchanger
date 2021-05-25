###Тестовое задание
####Нужно реализовать бизнес логику для простого обменника.
Требования: SOLID, unit тестирование, желательно деление на микросервисы 
(но в целом для тестового опционально)

То есть делим на условные несколько частей:
* Мой баланс
* Создание ордера с передачей след. данных: 
   первой валюты, второй валюты, курса и кол-ва валюты на покупку или продажу
* Список ордеров
* Покупка / продажа одной валюты за другую по выбранному ордеру

`Архитектурные детали на Ваше усмотрение`

---
###Инструкция:
####Импортировать проект в IntellijIDEA
* На экране выбора проекта нажать `Ctrl+Shift+A`
* Найти действие `Project from Existing Sources`
* Выбрать папку с проектом (в корне должен находиться `pom.xml`)
* Далее выбрать тип проекта `maven`
####Сборка
* Выполнить сборку с помощью команды `mvn clean install`
* Или с помощью заготовленного runConfigurations - `ExchangerApplication [clean,install]`
* Проверить что папка по пути `target/generated-sources/openapi/src/main/java` отмечена как `Generated sources root`.  
  Если нет, то `правой кнопкой мыши по папке` > `Mark Directory as` > `Generated sources root`
####Запуск
* Запустить проект можно выполнив main метод из класса `com.example.exchanger.ExchangerApplication`
* Или с помощью заготовленного runConfigurations - `ExchangerApplication`
---
###Для просмотра содержимого [H2 DB](http://localhost/api/h2-console) `username=sa` `password=password`
###Для просмотра и проверки API [Swagger-UI](http://localhost/api/swagger-ui.html)

---
