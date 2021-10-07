    def marathonDay = (int) commonActions.getUserVariable(user, 'dayNumberDone', '0');
    def blockHashes = ['1': '892345q94', '2': 'mnop67852', '3': '345qrst97'];
    def blockHash = blockHashes[marathonDay + ''];
    def token = userData.user.token;    
    def buttonName = "${marathonDay}-й день игры";

    def solveTasksLink = "https://goit.ua/python-marathon-autocheck/?token=${token}&block=${blockHash}";
    def keyboard = "inlineKeyboard\n${buttonName}=>${solveTasksLink}";
    
    def messages = [:];
    messages['1'] =
'''
Привет {name}
*Ссылка на первый день* уже ждет вас по кнопке ниже.👇 Успехов🙃
''';
    messages['2'] =
'''
*Ссылка на второй день* уже ждет вас по кнопке ниже.👇 Успехов🙃

''';
    messages['3'] =
'''
*Ссылка на третий день* уже ждет вас по кнопке ниже.👇 Успехов🙃
''';
    
    def message = messages[marathonDay + ''].replace('${name}', tgUser.firstName);
    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: tgUser, sendOrdered: false, botName: 'GoITeens_Python_autocourse_bot'], keyboard);
