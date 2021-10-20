from telegram.ext import Updater, MessageHandler, Filters

name = 'Mikhail'
city = 'Kharkiv'
age = 'не скажу'

def echo(update, context):
    string_in = update.message.text
    if string_in == 'name':
        string_out = name
    elif string_in == 'city':
        string_out = city
    elif string_in == 'age':
        string_out = age


    update.message.reply_text(string_out)

updater = Updater("2013484013:AAGNF0KtAimfPfyLR2yPZs-JYsJNv7sFRwA")

dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()