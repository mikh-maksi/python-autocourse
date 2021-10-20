from telegram.ext import Updater, MessageHandler, Filters

string_2x = '2x2=4 2x3=6\n2x4=8 2x5=10\n2x6=12 2x7=14\n2x8=16 2x9=18'


def echo(update, context):
    string_in = update.message.text
    if string_in == '2x':
        string_out = string_2x

    update.message.reply_text(string_out)

updater = Updater("2013484013:AAGNF0KtAimfPfyLR2yPZs-JYsJNv7sFRwA")

dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()