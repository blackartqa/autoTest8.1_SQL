## Задача №1 - Скоро deadline
1. Создал Docker Container на базе MySQL 8
2. Запустил SUT (app-deadline.jar) с параметрами командой `java -jar artifacts/app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=app -P:jdbc.password=pass`
3. Решил проблему сначала с запуском SUT
4. А на следующий день и с перезапуском SUT. В первой итерации использовал простую схему c sql-файлом типа  
```
DELETE FROM auth_codes;

DELETE FROM card_transactions;

DELETE FROM cards;

DELETE FROM users;
```  
А во второй итерации добавил очистку в класс DataHelper и вызов его после завершения тестов.
