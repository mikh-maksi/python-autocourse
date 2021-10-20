from telegram.ext import Updater, MessageHandler, Filters

def echo(update, context):
    string_in = update.message.text

    elements = string_in.split(' ')
    if elements[0] == '/task':
        if elements[1] == '1':
            string_out = 'answer for task 1'
        elif elements[1] == '2':
            string_out = 'answer for task 2'
        elif elements[1] == '3':
            string_out = 'answer for task 3'
        elif elements[1] == '4':
            string_out = 'answer for task 4'
        elif elements[1] == '5':
            string_out = 'answer for task 5'
        else:
            string_out = 'There is no answer for this task!'

    update.message.reply_text(string_out)

updater = Updater("")

dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()