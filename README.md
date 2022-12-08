# Assignment

This is a Coffee Ordering service which is splitted into two microservices.


## Order Service:
### How to run?
in th order directory please run this command.
```shell
mvn clean insatll -DskipTests

java -jar target/order.jar
```
## Barista Service:
### How to run?
in th barista directory please run this command.
```shell
mvn clean insatll -DskipTests

java -jar target/barista.jar
```
## Curls
### how to add orders:
```text
POST /orders HTTP/1.1
Host: localhost:8081
Content-Type: application/json
Cache-Control: no-cache

{
"price": 3.40,
"milkType": "COW_MILK",
"coffeeSize": "SMALL",
"coffeeType": "LATTE",
"type": "TAKEAWAY"
}
```
### how to update order state:
```text
PUT /orders/1/update-state HTTP/1.1
Host: localhost:8081
Content-Type: application/json
Cache-Control: no-cache

{
	"state": "FINISHED"
}
```
### how to delete an order:
```text
DELETE /orders/2 HTTP/1.1
Host: localhost:8081
Cache-Control: no-cache
Content-Type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW
```
### how to cancel an order:
```text
GET /orders/2/cancel HTTP/1.1
Host: localhost:8081
Cache-Control: no-cache
```