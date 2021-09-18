# Команды в Телеграм-боте
В этот учебный день мы с вами научимся создавать команды в боте. 
Первые команды, которые мы научимся обрабатывать - это команда `/start`, которая выполняется всеми подписчиками бота в момент подписки и команда `/help` в ответ на которую всегда отправляется информация о боте со всеми возможными командами.
# Команда /start
Если смотреть по сути, то команда `/start` - это просто соответствующая строка. При этом мы уже научились читать строки - мы все получаемые значения отправляем в переменную `start_in`.
Соответственно решением данной задачи будет то, что мы проверяем строку, которую вводит пользователь. Если она равна `/start`, то мы отправляем пользователю приветственное сообщение. Во всех других случаях - отпраляем то же сообщение, которое отправляет пользователь.
```py
from telegram.ext import Updater, MessageHandler, Filters

def echo(update, context):
    string_in = update.message.text

    if string_in == '/start':
        string_out = 'Hello! This is own finances bot!'
    else:
        string_out = string_in

    update.message.reply_text(string_out)

updater = Updater("")

dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()
```

В возвращаемой строке при вводе команды `/start` может возвращаться любое значение. Вы можете корректировать его самостоятельно.

## Команда /help
По общепринятому правилу если пользователь вводит команду `/help`, то бот ему возвращает список команд, которые он воспринимает, а также обычно содержит краткое описание бота.  
Добавим к отправке информации при получении команды `/start` реакцию на команду `/help`

```py
from telegram.ext import Updater, MessageHandler, Filters

def echo(update, context):
    string_in = update.message.text

    if string_in == '/start':
        string_out = 'Hello! This is own finances bot!'
    elif  string_in =='/help':
        string_out = 'Hello! This is own finances bot! \n Аvailable commands are /start and /help'
    else:
        string_out = string_in


    update.message.reply_text(string_out)

updater = Updater("2048746376:AAE0TM68eADs00mdnbjxGUrnXJ4vfNx4-UQ")

dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()

```
Обратите внимание, что используется конструкция `ielf` - она позволяет добавить условие выполнени в операторе выбора.

## Дополнительное задание
* Создайте минимум 3 команды для бота и "научите" бота отправлять в ответ на такие команды инфорционные сообщения. Например: `/savings` - информация о том, как эффективно создавать сбережения, `/income` - информация о том, как постоянно увеличивать доходы, `/investment` - информация о том, как инвестировать накопленные средства.