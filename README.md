# Задание 2
	Находять в корневой папке проекта:
1)	Корректные параметры:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-10 discountCard=5555 balanceDebitCard=100 pathToFile=./products.csv saveToFile=./correct_data.csv
2)	Отсутствует параметр pathToFile:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 discountCard=1111 balanceDebitCard=12.1 saveToFile=./error_result.csv
3)	Отсутствует параметр saveToFile: 
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 discountCard=1111 balanceDebitCard=100 pathToFile=./products.csv

