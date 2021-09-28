debug.enable();


def day = commonActions.getUserVariable(user, 'pythonCurrentScore', 30));

def message = 
'''
Привет!

''' + day;

commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false]);