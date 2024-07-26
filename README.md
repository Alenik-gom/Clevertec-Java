# Задание 3
Находясь в корневой папке проекта:
1)	Корректные параметры:
java -jar clevertec-check.jar 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 saveToFile=./result.csv datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=postgres

2)	Не хватает параметра:
java -jar clevertec-check.jar 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 saveToFile=./result.csv datasource.username=postgres datasource.password=postgres

