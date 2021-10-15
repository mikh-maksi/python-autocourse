import logging

from telegram import InlineKeyboardButton, InlineKeyboardMarkup, Update
from telegram.ext import Updater, CommandHandler, CallbackQueryHandler, CallbackContext, MessageHandler, Filters

reg_list = ["","","",""]

condition = 0

def key_buttons():
    reg_title = ["Введите имя","Введите город","Введите возраст","Введите доход"]
    reg_code = ["name","city","age","income"]
    key_lst = []    
    for i in range(len(reg_list)):
        if reg_list[i]=="":
            print(reg_title[i],reg_code[i])
            key_lst.append(InlineKeyboardButton(reg_title[i], callback_data=reg_code[i]))
    kb = [key_lst]
    return kb

def start(update: Update, context: CallbackContext) -> None:
    """Sends a message with three inline buttons attached."""

    keyboard = key_buttons()
    reply_markup = InlineKeyboardMarkup(keyboard)

    update.message.reply_text('Выберите один из вариантов:', reply_markup=reply_markup)


def button(update: Update, context: CallbackContext) -> None:
    """Parses the CallbackQuery and updates the message text."""
    global condition
    query = update.callback_query

    # CallbackQueries need to be answered, even if no notification to the user is needed
    # Some clients may have trouble otherwise. See https://core.telegram.org/bots/api#callbackquery
    query.answer()
    print(query.data)
    if query.data == 'name':
        keyboard = key_buttons()
        reply_markup = InlineKeyboardMarkup(keyboard)
        query.edit_message_text(text=f"Введите имя")
        condition = 1
    elif query.data == 'city':
        query.edit_message_text(text=f"Введите ваш город")
        condition = 2

    elif query.data == 'age':
        query.edit_message_text(text=f"Введите ваш возраст")
        condition = 3

    elif query.data == 'income':
        query.edit_message_text(text=f"Введите ваш доход")
        condition = 4

def echo(update, context):
    global condition
    string_in = update.message.text

    if string_in == '/start':
        string_out = 'Hello! This is own finances bot!'
    elif condition == 1:
        condition = 0
        reg_list[0]=string_in
        string_out = "Your name is recived"
    elif condition == 2:
        condition = 0
        reg_list[1]=string_in
        string_out = "Your city is recived"
    elif condition == 3:
        condition = 0
        reg_list[2]=string_in
        string_out = "Your age is recived"
    elif condition == 4:
        condition = 0
        reg_list[3]=string_in
        string_out = "Your income is recived"
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
    # Create the Updater and pass it your bot's token.
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