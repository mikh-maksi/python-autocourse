debug.enable();


def message = 
'''
Привет!

''';

commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false], keyboard);