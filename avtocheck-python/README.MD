# Автопроверка Python
**Автопроверка** - это покрытие тестов задач по Python. Для этого используется фреймворк **jinija**.
Алгоритм работы создания тестов:
* Написание кода программы, которая должна будет написана в результате (файт `answer.py`).
* Написание тестов: наличие переменных, тип переменных, значения переменных (`test.jinja`)
* Написание сообщений при непрохождения теста (`language.json`)
* Написание задания и теории (`description_ru.md`)
* Вводим описание задачи и данные для получения функцией input (`metadata.json`)

# Работа с Jinija
Базовые возможности
`assert "string_in" in user_ns, "{{string_in}}"` - наличие переменной `string_in` в коде программы. При ошибке - пользователю генерируется сообщение **string_in**.
`string_in = user_ns["string_in"]` - получаем значение переменной `string_in` из текста `answer.py`. 
`assert isinstance(string_in, str), f"{{string_in_type}}"` - проверяем тип значения переменной `string_in`. При ошибке - пользователю генерируется сообщение **string_in_type**.
`assert string_out=="Вы ввели:Yuriy", f"{{string_out_eq}}"` - проверка на то равна ли переменная `string_out` значению `Вы ввели:Yuriy`. При ошибке - пользователю генерируется сообщение **string_out_eq**.

`return "{{success}}"` - проверка полного выполнения задания. При выполнении генерируется сообщение **success**.


```
def test_variable(user_ns):
    assert "string_in" in user_ns, "{{string_in}}"
    assert "string_out" in user_ns, "{{string_out}}"

    string_in = user_ns["string_in"]
    string_out = user_ns["string_out"]

    assert isinstance(string_in, str), f"{{string_in_type}}"
    assert isinstance(string_out, str), f"{{string_out_type}}"

    assert string_out=="Вы ввели:Yuriy", f"{{string_out_eq}}"

    return "{{success}}"
```