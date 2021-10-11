from telegram.ext import Updater, MessageHandler, Filters
import requests
import json

url = 'https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5'

def echo(update, context):
    string_in = update.message.text

    elements = string_in.split(' ')

    if elements[0] == '/add':
        c = int(elements[1])+int(elements[2])
        string_out = c
    elif elements[0] == '/pb':

        response = requests.get(url)
        json_data = json.loads(response.text)

        string_out = ''

        for data in json_data:
	        buy = data['buy']
	        sale = data['sale']
	        ccy = data['ccy']
	        base_ccy = data['base_ccy']
	        string_out +=f'Валюта: {ccy} - {base_ccy} Покупка: {buy} - Продажа: {sale}\n'
    elif elements[0] == '/buyusd':
        response = requests.get(url)
        json_data = json.loads(response.text)

        string_out = json_data[0]['buy']

    else:
        string_out = string_in

    update.message.reply_text(string_out)

updater = Updater("1909698136:AAGZ6a_XYSdMyyVjnSws3Pdn7LoyY-y_KFc")

dispatcher = updater.dispatcher

dispatcher.add_handler(MessageHandler(Filters.all, echo))

updater.start_polling()
updater.idle()
