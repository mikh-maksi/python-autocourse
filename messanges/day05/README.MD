# Функции
В библиотеке для работы с телеграммом, которую мы используем есть возможность запускать функции, когда пользователь использует команды.

Программу, которая позволяет создавать телеграм-бота можно разделить на такие части:
Импорт элементов. В нашем примере мы импортируем из библиотеки `telegram.ext` импортруем объекты `Updater, MessageHandler, Filters, CommandHandler`
```py
from telegram.ext import Updater, MessageHandler, Filters, CommandHandler
```
В следующей части - мы пишем функции, с которыми будем работать. Например, функция `start` - получает два параметра. Если упрощенно - `update` позволяет получать сообщения пользователя и отвечать на них, а `context` - отправлять сообщения вне зависимости от сообщений пользователя (в данной реализации функции `start` используется только `context`)

```py
def start(update, context):
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="Привет! Это own finances bot!")
```
И последняя часть - она "Оживляет" нашего бота:
`updater = Updater("")` - с помощью объекта `Updater` наша программа подключается к боту.
`dispatcher = updater.dispatcher` - устанавливаем "диспетчера", который контролирует то, что отправляет пользователя
`dispatcher.add_handler(CommandHandler("start", start))` - добавляем к "диспетчеру" "обработчик" (handler), данный обработчик является обработчиком команд (CommandHandler) и если пользователь вводит команды start (`"start"` - первый параметр) запускает функцию `start` (второй параметр).

Команды  
`updater.start_polling()`  
`updater.idle()`  
запускают работу бота

```py
from telegram.ext import Updater, MessageHandler, Filters, CommandHandler

def start(update, context):
    chat = update.effective_chat
    string_out = "Привет! Это own finances bot!"
    context.bot.send_message(chat_id=chat.id, text=string_out)

updater = Updater("")
dispatcher = updater.dispatcher

dispatcher.add_handler(CommandHandler("start", start))

updater.start_polling()
updater.idle()
```


```py
from telegram.ext import Updater, MessageHandler, Filters, CommandHandler

def start(update, context):
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="Привет! Это own finances bot!")

def rule1(update, context):
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="1. Считай мелкие расходы \n Небольшие расходы, которые ты обычно не учитываешь, в итоге перерастают в приличную сумму. Сегодня 40 гривен на кофе, завтра неожиданный хот-дог… Необязательно учитывать все расходы до копейки, но есть смысл записывать хотя бы те, которые больше 0,5 процента от дохода.")

def rule2(update, context):
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="2. Ставь долгосрочные финансовые цели  \n Например, ты хочешь отложить деньги на ноут или на отпуск — это и есть цель. Цели нужно знать, чтобы держать их в уме и понимать, ради чего ты сегодня не потратишь 40 гривен на кофе. Это психология — отказываться от каких-то расходов проще, если ты знаешь, ради чего это делаешь.")

def rule3(update, context):
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="3. Прокачивай свои профессиональные навыки \n Чтобы зарабатывать больше.")

def rule4(update, context):
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="4. Живи как инвестор \n Принимая решения, оценивай их так, как инвестор оценивает бизнес, в который намерен инвестировать. Задавай себе вопросы: а что я с этого буду иметь? Какой результат я получу и когда? Как я смогу получить еще лучший результат? Это упражнение, которые помогает прокачать личную продуктивность, освободить время от ненужных занятий и наполнить его полезными — и с точки зрения финансов, и с точки зрения моральной удовлетворенности от результата.")


def costs(update, context):
    string = update.message.text
    elements = string.split(' ')
    
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="Your cost is "+elements[1])


def echo(update, context):
    update.message.reply_text(update.message.text)

updater = Updater("")
dispatcher = updater.dispatcher

dispatcher.add_handler(CommandHandler("start", start))
dispatcher.add_handler(CommandHandler("rule1", rule1))
dispatcher.add_handler(CommandHandler("rule2", rule2))
dispatcher.add_handler(CommandHandler("rule3", rule3))
dispatcher.add_handler(CommandHandler("rule4", rule4))
dispatcher.add_handler(CommandHandler("eat", costs))
dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()

```