# Задание 1
	Находясь в корневой папке проекта:
1)	Корректные параметры:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 
2)	Отсутствует обязательный параметр id-quantity:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java discountCard=1234 balanceDebitCard=500 
3)	Недостаточный баланс:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-10 2-5 5-1 discountCard=1111 balanceDebitCard=8 

