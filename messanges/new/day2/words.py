from telegram.ext import Updater, MessageHandler, Filters, CommandHandler
# формируем схему - переменная-флаг задает состояние. После команды - она становится равно 1. При следующем вводе - равной 0.
account = 0


def echo(update, context):
    string_in = update.message.text

    if string_in == '/start':
        string_out = 'Hello! This is own finances bot!'
    elif string_in == 'Hello':
        string_out = 'Hello! How are you?'
    else:
        string_out = string_in

    update.message.reply_text(string_out)

updater = Updater("2034824924:AAFc0q0PYPezeZ6G5kE10uBWhWSurKks-8A")

dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))


updater.start_polling()
updater.idle()