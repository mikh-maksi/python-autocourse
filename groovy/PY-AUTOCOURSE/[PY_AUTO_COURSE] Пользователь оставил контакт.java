import java.time.*;

//Включаем отладку
debug.enable();

//Замыкание для определения текущего дня марафона
def getMarathonDay = {tgUser ->
    def gameDate = LocalDateTime.parse(commonActions.getUserVariable(user, 'pyGameStartDate', 'none') + 'T00:00:00');
    def today = LocalDate.now().toString() + "T00:00:00";
    
    return commonActions.getDateDiff(gameDate, today, 'days') + 1;
}

//Фиксим дату события, если это реферальный юзер
if (telegramUser.fromUserId > 0) {
    def whoInvitedTelegramUser = commonActions.getTelegramUserService().getById(telegramUser.fromUserId);
    def whoInvitedUser = commonActions.getTelegramUserService().getRelatedInnerUser(whoInvitedTelegramUser);
    def whoInvitedUserGameStartDate = commonActions.getUserVariable(whoInvitedUser, 'pyGameStartDate', 'none');
    
    commonActions.setUserVariable(user, 'pyGameStartDate', whoInvitedUserGameStartDate);
}

//Ссылки на чаты - каждая дата запуска - свой чат
def chatLinks = commonActions.getJsonPref('pythonGame').chatLinks;
// [
//     '2020-11-23' : 'https://t.me/joinchat/NvnDERsVf-7-9GobIGqG6w',
//     '2020-12-07' : 'https://t.me/joinchat/NvnDERwX5pCzuhYuaWh0Sw',
//     '2020-12-14': 'https://t.me/joinchat/NvnDERwTT4NXa5iHoEkeFw'
// ];

def startTexts = commonActions.getJsonPref('pythonGame').startDateTimes;
// [
//     '2020-11-23': '23 ноября в 19:00',
//     '2020-12-07': '7 декабря в 19:00',
//     '2020-12-14': '14 декабря в 19:00'
// ];

//Замыкание - определить ближайшую дату марафона
def getNearestMarathonStartDate = {
    //Debug
    // if (telegramUser.userId == 473264504) {
    //     return 'none';
    // }
    
    def today = LocalDateTime.now();
    def nearestMarathonDate = 'none';
    
    def mDates = chatLinks.keySet() as String[];
    for(def mDate: mDates) {
        def fullMDate = mDate + 'T00:00:00';
        
        def diff = commonActions.getDateDiff(today, fullMDate, 'days');
        if (diff >= 0) {
            nearestMarathonDate = mDate;
            break;
        }
    }
    
    return nearestMarathonDate;
};

//Дата марафона не задана - показываем текст-заглушку, и на этом все
if (getNearestMarathonStartDate().equals('none')) {
    def message =
'''
К сожалению, игра уже стартовала. Мы вышлем вам приглашение на следующую игру в этот бот. 

На связи😉
''';
    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goit_python_game_bot']);
    return;
}

def pyGameStartDate = commonActions.getUserVariable(user, 'pyGameStartDate', 'none');

//Если человек пришел откуда-то без нужной даты - автоматически ставим ему дату ближайшего марафона
if (pyGameStartDate.equals('none')) {
    pyGameStartDate = getNearestMarathonStartDate();
    commonActions.setUserVariable(user, 'pyGameStartDate', pyGameStartDate);
}

//Определяем текущий день и час, нужно чтобы понять на какой стадии марафона находится человек
def marathonDay = getMarathonDay(telegramUser);
def currentHour = LocalDateTime.now().getHour();

debug.log(marathonDay + ', ' + currentHour);

//Если человек зарегистрировался до старта марафона, или в день марафона до 19:00
if (marathonDay < 1 || (marathonDay == 1 && currentHour < 19)) {
    //Получаем актуальную ссылку на чат, базируясь на дате события
    def chatLink = chatLinks[pyGameStartDate];
    
    //Отсылаем первое сообщение с предложением перейти в чат
    def firstMessage =
    '''
Спасибо за регистрацию. В этом чат-боте вы будете получать все необходимые материалы и ссылки для прохождения игры 👌

Игра стартует *${startDate}.* 

Прямо сейчас подключитесь к *чату игроков*:

[${chatLink}]

В нем мы будем публиковать важную информацию и объявления. 

📣 А еще в любой момент вы сможете обратиться в нем к своему ментору за помощью в выполнении задания, вопросом или консультацией 🤗

*Поэтому обязательно подключайтесь!* 👇

[${chatLink}]
    ''';
    
    firstMessage = firstMessage.replace('${startDate}', startTexts[pyGameStartDate]);
    firstMessage = firstMessage.replace('${chatLink}', chatLink);
    
    def firstMessageKeyboard = "inlineKeyboard\nЯ подписался в чат. Что дальше?=>pymarathon_chat_subscribed";
    
    commonActions.sendTelegramSimpleTextMessage(firstMessage, [telegramUser: telegramUser, sendOrdered: true, botName: 'goit_python_game_bot'], firstMessageKeyboard);
    
} else if (marathonDay == 1 && currentHour >= 19) { //Человек зарегался на марафон с опозданием, но еще успевает
     //Получаем актуальную ссылку на чат, базируясь на дате события
    def chatLink = chatLinks[pyGameStartDate];
    
    //Отправляем первое сообщение
    def message1 =
'''
${name}, привет 👋
Игра уже началась🚀 Включайтесь скорее😎

*Шаг 1.* Подключитесь к *чату игроков*:

[${chatLink}]

В нем мы публикуем важную информацию и объявления.
📣 А еще в любой момент вы можете обратиться в нем к своему ментору за помощью в выполнении задания, вопросом или консультацией 🤗

Поэтому *обязательно подключайтесь!* 👉 [${chatLink}]

'''.replace('${name}', telegramUser.firstName);
    message1 = message1.replace('${chatLink}', chatLink);
    def message1Keyboard = "inlineKeyboard\nПерейти в чат=>${chatLink}";
    commonActions.sendTelegramSimpleTextMessage(message1, [telegramUser: telegramUser, sendOrdered: true, botName: 'goit_python_game_bot'], message1Keyboard);
    
    //Отправляем второе сообщение
    def message2 =
'''
*Шаг 2.* Прочтите внимательно это сообщение и приступайте к решению задач.

*Ваша роль в игре - будущий стартапер.*😎 Вы придумали крутую идею, способную изменить мир. Вы нашли инвестора и теперь *ваша цель - собрать команду мечты* из тысяч претендентов и показать ее инвестору, чтобы получить деньги. 💪

Чтобы справиться с задачей, вам предстоит создать *свою CRM-систему* - эдакую прокачанную записную книжку, которая посчитает все сама.🔥

Сегодня вы сделаете первые шаги к ее созданию - познакомитесь с *синтаксисом языка Python.*😎

*На реализацию у вас будет 4 дня = 3 жизни* 👌 
Каждый день будет приходить *ссылка на блок задач, которые нужно выполнить до 18:00* следующего дня. Не выполняете - жизни сгорают. 

*Если сгорят все - Game over*, игра окончена 🙌

Поэтому отнеситесь #повнимательнее к *дедлайну и решению задач.* А если возникнут вопросы - пишите их в чат 👉 

[${chatLink}] 🔥

*Ссылка на первый день* уже ждет вас по кнопке ниже.👇 Успехов🙃

''';
    message2 = message2.replace('${chatLink}', chatLink);

    def token = user.token;
    def link = "https://goit.ua/python-marathon-autocheck/?token=${token}&block=892345q94";
    def message2Keyboard = "inlineKeyboard\n1-й день марафона=>${link}";
    
    commonActions.sendTelegramSimpleTextMessage(message2, [telegramUser: telegramUser, sendOrdered: true, botName: 'goit_python_game_bot'], message2Keyboard);
    
} else { //Человек зарегался на марафон с сильным опозданием - ищем ближайший марафон, и направляем туда

        pyGameStartDate = getNearestMarathonStartDate();
        commonActions.setUserVariable(user, 'pyGameStartDate', pyGameStartDate);
        
        def message =
'''
Спасибо за регистрацию. В этом чат-боте вы будете получать все необходимые материалы и ссылки для прохождения игры👌

Игры уже стартовала,  поэтому я записал вас на следующую.🚀
🕖*Старт - ${startDate}*
Прямо сейчас подключитесь к *чату игроков* 

[${chatLink}]

В нем мы будем публиковать важную информацию и объявления.
📣 А еще в любой момент вы сможете обратиться в нем к своему ментору за помощью в выполнении задания, вопросом или консультацией 🤗
Поэтому *обязательно подключайтесь!* 👉

[${chatLink}]
''';
        def chatLink = chatLinks[pyGameStartDate];
        def messageKeyboard = "inlineKeyboard\nЯ подписался в чат. Что дальше?=>pymarathon_chat_subscribed";

        message = message.replace('${chatLink}', chatLink).replace('${startDate}', startTexts[pyGameStartDate]);
    
        //Отправляем сообщение
        commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: true, botName: 'goit_python_game_bot'], messageKeyboard);
}

//Если пользователь зарегался по реферальной ссылке
if (telegramUser.fromUserId > 0) {
    def additionalScore = 5;
        
    //Получаем текущие баллы
    def currentScore = Integer.parseInt(commonActions.getUserVariable(user, 'pythonCurrentScore', 30));
    currentScore += additionalScore;
        
    //Сохраняем текущие баллы, синхроня старые
    commonActions.setUserVariable(user, 'pythonCurrentScore', currentScore);
    commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    
    //Теперь нужно добавить 5 баллов тому, кто пригласил
    def whoInvitedTelegramUser = commonActions.getTelegramUserService().getById(telegramUser.fromUserId);
    def whoInvitedUser = commonActions.getTelegramUserService().getRelatedInnerUser(whoInvitedTelegramUser);
    
    def whoInvitedCurrentScore = Integer.parseInt(commonActions.getUserVariable(whoInvitedUser, 'pythonCurrentScore', 30));
    whoInvitedCurrentScore += additionalScore;
    
    commonActions.setUserVariable(whoInvitedUser, 'pythonCurrentScore', whoInvitedCurrentScore);
    commonActions.setUserVariable(whoInvitedUser, 'pythonOldScore', whoInvitedCurrentScore);
    
    //Увеличиваем переменную, которая показывает за скольких приглашенных юзеров мы начислили баллы
    def pythonRefUserCount = Integer.parseInt(commonActions.getUserVariable(whoInvitedUser, 'pythonRefUserRegistered', 0));
    pythonRefUserCount += 1;
    commonActions.setUserVariable(whoInvitedUser, 'pythonRefUserRegistered', pythonRefUserCount);
    
    //Если первый человек из приглашенных выполнил задачу, то также задаем переменную oldpythonRefUserCount
    if (pythonRefUserCount == 1) {
        commonActions.setUserVariable(whoInvitedUser, 'pytonOldRefUserRegistered', 0);
    }
}
