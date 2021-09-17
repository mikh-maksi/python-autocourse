from telegram.ext import Updater, MessageHandler, Filters

def echo(update, context):
    print(update.message.text)
    update.message.reply_text(update.message.text)

updater = Updater("")
dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()