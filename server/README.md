## Среда запуска Pythonanywhere
Создадим рабочее место на сервере [https://www.pythonanywhere.com/](https://www.pythonanywhere.com/).
<img src = "img/pythonanywhere1.jpg" height=600>  
Выбрать на главной странице создание кода  
<img src = "img/pythonanywhere2.jpg" height=600>  
Выбрать пакет "Начинающий"  
<img src = "img/pythonanywhere3.jpg" height=600>  
Зарегистрироваться  
<img src = "img/pythonanywhere4.jpg" height=600>  
Пропустить интерактивную инструкцию  
<img src = "img/pythonanywhere5.jpg" height=600>  
Перейти в раздел работы с файлами  
<img src = "img/start1.jpg" height=600>  
Создайте новый файл  

### Переменные в Python. Строки
"Hello, world" - это набор символов, т.е. строка. И мы можем эту строку сохранить в переменную. И выводить не сам набор символов, а уже значение переменной. Выглядит это как будто мы создали ящик с именем `s`, положили в этот ящик положили в определенной последовательности кубики Hello, world. А дальше - прочитали буквы на кубиках, которые лежали в ящике `s`  
```python
s = "Hello, world!"
print(s)
```
<img src = "img/start2.jpg" height=600>  

При этом для переменные со строками можно соединять:
```python
s1 = "Hello, "
s2 = "world!"
s = s1 + s2
print(s)
```
В дальнейшем - вы можете создавать файлы в разделе "Files" и запускать их.
### Закрытие запущенных консолей.
По условиям хостинга pythonanywhere может быть запущено одновременно не более чем 2 программы. В терминах данного хостинга - работать не более 2-х консолей одновременно.  
<img src = "./img/pythonanywhere16.jpg">  
Поэтому необходимо останавливать запущенные консоли.  
<img src = "./img/pythonanywhere18.jpg">  
<img src = "./img/pythonanywhere19.jpg">  