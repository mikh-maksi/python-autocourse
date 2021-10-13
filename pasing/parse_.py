import requests
import json

url = 'https://www.albion-online-data.com/api/v2/stats/Gold'

response = requests.get(url)
json_data = json.loads(response.text)

for data in json_data:
	price = data['price']
	timestamp = data['timestamp']
	print(f'Дата: {timestamp} - Цена: {price}')