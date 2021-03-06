import java.time.*;

//Включаем отладку
debug.enable();

//Выбираем подходящих нам пользователей
def telegramUserQueryParams = [:];
telegramUserQueryParams['tag'] = 'PY_AUTO_COURSE'; // Тег Телеграм пользователей
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
    def marathonDay = 1 //Читать день марафона из соответствующей переменной и/или из даты старта

    def blockHashes = ['1':'ijklmno156','2':'jklmnop157','3':'klmnop6158','4':'op67892162','5':'nop6789161','6':'p678923163','7':'mnop678160','8':'6789234164','9':'lmnop67159','10':'7892345165','11':'892345q166','12':'92345qr167'];

    def blockHash = blockHashes[marathonDay + ''];
    def token = userData.user.token;
    
    def buttonName = "${marathonDay}-й день игры";

    def solveTasksLink = "https://goit.ua/python-marathon-autocheck/?token=${token}&block=${blockHash}";
    def keyboard = "inlineKeyboard\n${buttonName}=>${solveTasksLink}";
    
    def messages = [:];
    messages['1'] =
'''
Привет, ${name} 👋
Готовы Создавать ботов?🚀 Начинаем прямо сейчас!
Сегодня нас ждет знакомство со строками и создание эхобота.
''';
    messages['2'] =
'''
Научим бота команлам /start /help
''';
    messages['3'] =
'''
Добавим к боту свои команды
''';
    messages['4'] ='''Следующий день''';
    messages['5'] ='''Следующий день''';
    messages['6'] ='''Следующий день''';
    messages['7'] ='''Следующий день''';
    messages['8'] ='''Следующий день''';
    messages['9'] ='''Следующий день''';
    messages['10'] ='''Следующий день''';
    messages['11'] ='''Следующий день''';
    messages['12'] ='''Следующий день''';
    
    def message = messages[marathonDay + ''].replace('${name}', tgUser.firstName);
    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: tgUser, sendOrdered: false, botName: 'goiteens_python_bot'], keyboard);
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




