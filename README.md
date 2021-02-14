## Programming Technologies
### Task 8. Thread.
>Implement the **ParallelMatrixProduct** class for multithreaded **UsualMatrix** matrix multiplication. In the constructor, the class gets the number of threads that will be used for multiplication (the number of threads can be less than the number of rows in the first matrix).  
In the **main** function, compare the time of multiplying large random matrices in the normal and multi-threaded way. You can get the current time using the methods of the **System**.

#### Additional task
> Implement a multi-threaded version of the program that calculates the number of ways to put N queens on the board N on N so that they do not beat each other.  
**int calcQueenNum (int N, int threadNum);**  
Choose N so that **calcQueenNum (N, some number> 1)** outperforms **calcQueenNum (N, 1)**.
Hint: The first thread is responsible for all combinations in which the queen in the first column is at position 1… .n / (number of threads).
---
### Задание 8. Thread.
>Реализовать класс **ParallelMatrixProduct** для многопоточного умножения матриц **UsualMatrix**. В конструкторе класс получает число потоков, которые будут использованы для перемножения (число потоков может быть меньше, чем число строк у первой матрицы).  
В функции **main** сравнить время перемножения больших случайных матриц обычным и многопоточным способом. Получить текущее время можно с помощью методов класса **System**.

####Дополнительное задание
>Реализуйте многопоточную версию программы, которая вычисляет число способов поставить N ферзей на доске N на N, чтобы они не били друг друга.  
**int calcQueenNum(int N, int threadNum);**  
Подберите N так, чтобы ** calcQueenNum(N, какое-то число > 1)**  выигрывает по времени работы у **calcQueenNum(N, 1)** .
Hint: Первый поток отвечает за все комбинации, в которых ферзь в первом столбце находится на позиции 1….n / (число потоков).
