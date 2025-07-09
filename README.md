
### Sprawdzenie środowiska

- **Uruchom infrastrukture**  [docker-compose](infrastructure/docker-compose.yml).

- **GUI do Kafki**  
  Po odpaleniu pod adresem [http://localhost:8080/](http://localhost:8080/) dostępne jest GUI do Kafki z podłączonym Schema Registry. Możesz tam weryfikować, jakie wiadomości pojawiły się na danym topicu.

### Baza danych

Każda baza danych to osobny schemat, co zapewnia separację i oszczędza lokalnie trochę zasobów, a na środowisku wdrożeniowym pozwala używać osobnych baz danych.
Po zalogowaniu się do bazy jako użytkownik `admin_user` z hasłem `admin_password` (`jdbc:postgresql://localhost:5432/kopytkadb`), masz dostęp do wszystkich schematów.

## PgAdmin

Po uruchomieniu będzie dostępny pod adresem:  
http://localhost:5050

Aby się zalogować:
- **Host address:** postgres
- **Port:** 5432
- **Username:** postgres
- **Password:** postgres

## Sprawdzenie działanie:

Uruchom wszystkie mikroserwisy.
Wyślij żądanie:

POST http://localhost:8581/api/customers

```json
{
"firstName": "Ferdynand",
"lastName": "Kiepski",
"email": "ferdynand.kiepski@example.com"
}
```

Po udanym utworzeniu customera sprawdź, czy jego ID zostało zreplikowane do schematu `order_schema` w tabeli `customer_view`.
Jeśli chcesz podejrzeć wiadomość, możesz to zrobić w GUI do Kafki.
Jeśli klient został zreplikowany znaczy, że infrastruktura działa, możemy przejść dalej — ręka do góry!

Zatrzymaj i wyczyść infrastrukturę, aby nie przeszkadzała podczas przełączania się na projekt Pnktozaura.

- **Czyszczenie infrastruktury**  
  Jeśli chcesz usunąć dane z bazy, topiki z Kafki i ubić wszystkie kontenery Docker, użyj skryptu [docker-clean.sh](infrastructure/docker-clean.sh).


