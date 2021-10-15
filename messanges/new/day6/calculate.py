from telegram.ext import Updater, MessageHandler, Filters, CommandHandler
# формируем схему - переменная-флаг задает состояние. После команды - она становится равно 1. При следующем вводе - равной 0.
account = 0


def costs(update, context):
    global account
    string_in = update.message.text
    elements = string_in.split(' ')
    account = account - int(elements[1])
    string_out = str(account)
    update.message.reply_text(string_out)

def income(update, context):
    global account
    string_in = update.message.text
    elements = string_in.split(' ')
    account = account + int(elements[1])
    string_out = str(account)
    update.message.reply_text(string_out)

def echo(update, context):
    string_in = update.message.text

    if string_in == '/start':
        string_out = 'Hello! This is own finances bot!'
    else:
        string_out = string_in

    update.message.reply_text(string_out)

updater = Updater("2034824924:AAFc0q0PYPezeZ6G5kE10uBWhWSurKks-8A")

dispatcher = updater.dispatcher

dispatcher.add_handler(CommandHandler('costs', costs))
dispatcher.add_handler(CommandHandler('income', income))
dispatcher.add_handler(MessageHandler(Filters.all, echo))


updater.start_polling()
updater.idle()