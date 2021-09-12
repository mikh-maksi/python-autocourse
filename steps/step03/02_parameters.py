from telegram.ext import Updater, MessageHandler, Filters, CommandHandler

def start(update, context):
    chat = update.effective_chat
    context.bot.send_message(chat_id=chat.id, text="Hello! This is own finances bot.")

def echo(update, context):
    string_out = ''
    string_in = update.message.text
    elements = string_in.split(' ')
    print(elements)
    string_out = len(elements)
    update.message.reply_text(string_out)

updater = Updater("1958845613:AAF48B3sKwrn-ggwr8LxGdYiygpyePLBs9I")
dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()
