import java.time.*;

//Включаем отладку
debug.enable();

//Выбираем подходящих нам пользователей
def telegramUserQueryParams = [:];
telegramUserQueryParams['tag'] = 'PY_MARATHON'; // Тег Телеграм пользователей
// telegramUserQueryParams['maxRegisterDaysFromNow'] = '28'; //Пользователи, не старше 28 дней
telegramUserQueryParams['idIs'] = '394735340'; //ДЕБАГ - только мне слать
// telegramUserQueryParams['phoneIs'] = '380997852751'; //ДЕБАГ - только мне слать

def telegramUsers = commonActions.getTelegramUsersWithAllConditions(telegramUserQueryParams);
debug.log('Количество оповещаемых пользователей: ' + telegramUsers.size());

//Если некого оповещать - не оповещаем
if (telegramUsers.size() <= 0) {
    return;
}

def telegramUsersData = commonActions.getAggregatedDataForTelegramUsers(telegramUsers);


// Замыкание - оповещаем пользователя что есть ссылка на задачи такого-то дня
def notifyUser = {tgUser, userData -> 
    def marathonDay = 1

    def blockHashes = ['1': '892345q94', '2': 'mnop67852', '3': '345qrst97'];

    def blockHash = blockHashes[marathonDay + ''];
    def token = userData.user.token;
    
    def buttonName = "${marathonDay}-й день игры";

    def solveTasksLink = "https://goit.ua/python-marathon-autocheck/?token=${token}&block=${blockHash}";
    def keyboard = "inlineKeyboard\n${buttonName}=>${solveTasksLink}";
    
    def messages = [:];
    messages['1'] =
'''
Привет, ${name} 👋
Готовы сыграть?🚀 Начинаем прямо сейчас!

*Напомним: вы - будущий стартапер.*😎 Вы придумали крутую идею, способную изменить мир. Вы нашли инвестора и теперь *ваша цель - собрать команду мечты* из тысяч претендентов и показать ее инвестору, чтобы получить деньги. 💪

Чтобы справиться с задачей, вам предстоит создать *свою CRM-систему* - эдакую прокачанную записную книжку, которая посчитает все сама.🔥

Сегодня вас ждет увлекательная история: вы будете искать и найдете инвестора, уволите всю команду, но потом все же сделаете первую версию продукта... и это все в процессе изучения *синтаксиса языка Python*.😎 

*На реализацию у вас будет 3 дня = 3 жизни* 👌 
Каждый день будет приходить *ссылка на блок задач, которые нужно выполнить до 18:00* следующего дня. Не выполняете - жизни сгорают. 

*Если сгорят все - Game over*, игра окончена 🙌

Поэтому отнеситесь #повнимательнее к *дедлайну и решению задач*. А если возникнут вопросы - пишите их в чат 👉 [${chatLink}]  🔥

*Ссылка на первый день* уже ждет вас по кнопке ниже.👇 Успехов🙃
''';
    messages['2'] =
'''
Отправляем ссылку на *второй день задач* 😎 Сегодня познакомиться с с объектно-ориентированным программированием на Python💪: классами и объектами, полями и методами, наследованием; А также познакомитесь развития стартапа в формате Cust Dev.
Переходите по кнопке ниже и погнали. Напомню, что закончить задачи нужно *до завтра, 18:00*, иначе сгорит жизнь 🙌 

P.S. Закончите задачи прошлого дня, если вы не успели этого сделать, прежде, чем приступать к новым 👌
''';
    messages['3'] =
'''
*Вы почти у цели* 🤩

Сегодня третий день игры 🔥 Инвестор скоро будет в восторге - *проект мечты будет выведен на рынок!* Решайте задачи по кнопке ниже. Дедлайн - *до завтра, 18:00*. 

Завтра у нас завершающий день игры - *проведем занятие в формате вебинара в прямом эфире*, на котором будет *тестирование по материалам Python, которые вы успели выучить* 🚀

P.S. Напомним, что если у вас остались невыполненные задачи за прошлый день, закончите, прежде чем приступить к новым. Успехов 😎
''';
    
    def message = messages[marathonDay + ''].replace('${name}', tgUser.firstName);
    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: tgUser, sendOrdered: false, botName: 'goit_python_game_bot'], keyboard);
}

//Пробегаемся по пользователям
for(telegramUser in telegramUsers) {
    def userData = telegramUsersData.getOrDefault(telegramUser.userId, null);
    def tgUserId = telegramUser.userId;
    def tgUserInfo = telegramUser.userId + ' (' + telegramUser.getFullName() + ')';

    if (userData == null) {
        debug.log(tgUserInfo, '[WARNING] No user data');
        continue;
    }
    
    //Смотрим, нужно ли оповещать пользователя в этот день
    try {
            notifyUser(telegramUser, userData); //Оповещаем пользователей
        
    } catch(ex) {
        debug.log(ex, telegramUser);
    }
}




