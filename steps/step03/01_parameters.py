from telegram.ext import Updater, MessageHandler, Filters, CommandHandler

def start(update, context):
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="Hello! This is own finances bot.")

def echo(update, context):
    elements = update.message.text.split(' ')
    print(elements)
    update.message.reply_text(update.message.text)

updater = Updater("1958845613:AAF48B3sKwrn-ggwr8LxGdYiygpyePLBs9I")
dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()
