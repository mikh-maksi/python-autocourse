import requests
import json

url = 'https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5'

response = requests.get(url)
json_data = json.loads(response.text)

string_out = ''

for data in json_data:
	buy = data['buy']
	sale = data['sale']
	string_out +=f'Покупка: {buy} - Продажа: {sale}\n'

print(string_out)