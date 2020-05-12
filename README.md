# Тест REST-сервисов
* Получить все Boxes: 
curl --location --request GET 'localhost:8080/boxes' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--header 'Content-Length: 25'

* Получить все Items:
curl --location --request GET 'localhost:8080/items' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--header 'Content-Length: 25'

* Получить id всех Items из box с цветом color:
curl --location --request POST 'localhost:8080/elements' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--header 'Content-Length: 25' \
--data-raw '{"box":"1","color":"red"}'
