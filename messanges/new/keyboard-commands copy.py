import logging

# Две основные кнопки: доход и расход. Нажимая любую - выводится сообщение: "Введите размер доход/расхода"

from telegram import InlineKeyboardButton, InlineKeyboardMarkup, Update
from telegram.ext import Updater, CommandHandler, CallbackQueryHandler, CallbackContext, MessageHandler, Filters

reg_list = ["",""]

account = 0
condition = 0

check_strings = ["Your input is correct","Your input is empty","Parameter of command is not digit"]

def check(string_in):
    n=0
    elements = string_in.split(' ')

    if not len(string_in) > 0:
        n=1
    elif not string_in.isdigit():
        n=2
    return n

def key_buttons():
    reg_title = ["Доход","Расход"]
    reg_code = ["income","cost"]
    key_lst = []    
    for i in range(len(reg_title)):
        key_lst.append(InlineKeyboardButton(reg_title[i], callback_data=reg_code[i]))
    kb = [key_lst]
    return kb

def start(update: Update, context: CallbackContext) -> None:
    keyboard = key_buttons()
    reply_markup = InlineKeyboardMarkup(keyboard)
    update.message.reply_text('Выберите один из вариантов:', reply_markup=reply_markup)


def button(update: Update, context: CallbackContext) -> None:
    global condition
    query = update.callback_query
    query.answer()
    if query.data == 'income':
        query.edit_message_text(text=f"Введите размер дохода")
        condition = 1
    elif query.data == 'cost':
        query.edit_message_text(text=f"Введите размер затрат")
        condition = 2

def echo(update, context):
    global condition, account
    string_in = update.message.text

    if string_in == '/start':
        string_out = 'Hello! This is own finances bot!'
    elif condition == 1:
        condition = 0
        if not check(string_in):
            account = account + int(string_in)
            string_out = "Состояние вашего счета: "+str(account)
        else:
            string_out = check_strings[check(string_in)]

    elif condition == 2:
        if not check(string_in):
            account = account - int(string_in)
            string_out = "Состояние вашего счета: "+str(account)
        else:
            string_out = check_strings[check(string_in)]
    else:
        string_out = string_in


    keyboard = key_buttons()
    reply_markup = InlineKeyboardMarkup(keyboard)

    update.message.reply_text(string_out,reply_markup=reply_markup)


def help_command(update: Update, context: CallbackContext) -> None:
    """Displays info on how to use the bot."""
    update.message.reply_text("Use /start to test this bot.")


def main() -> None:
    """Run the bot."""
    updater = Updater("2034824924:AAFc0q0PYPezeZ6G5kE10uBWhWSurKks-8A")

    updater.dispatcher.add_handler(CommandHandler('start', start))
    updater.dispatcher.add_handler(CallbackQueryHandler(button))
    updater.dispatcher.add_handler(CommandHandler('help', help_command))
    updater.dispatcher.add_handler(MessageHandler(Filters.all, echo))

    # Start the Bot
    updater.start_polling()

    # Run the bot until the user presses Ctrl-C or the process receives SIGINT,
    # SIGTERM or SIGABRT
    updater.idle()


if __name__ == '__main__':
    main()