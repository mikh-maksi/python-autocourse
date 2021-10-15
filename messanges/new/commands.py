from telegram.ext import Updater, MessageHandler, Filters
# формируем схему - переменная-флаг задает состояние. После команды - она становится равно 1. При следующем вводе - равной 0.
condition = 0

def echo(update, context):
    global condition
    string_in = update.message.text

    if string_in == '/start':
        string_out = 'Hello! This is own finances bot!'
    elif string_in == '/reg':
        string_out = "Enter your name, please"
        condition = 1
    elif condition == 1:
        string_out = "We recived your name"
        condition=0
    else:
        string_out = string_in

    update.message.reply_text(string_out)

updater = Updater("2034824924:AAFc0q0PYPezeZ6G5kE10uBWhWSurKks-8A")

dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()