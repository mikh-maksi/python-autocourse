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

def currentScore = Integer.parseInt(commonActions.getUserVariable(user, 'pythonCurrentScore', 20));

def daySend = 0;
//def marathonDay = commonActions.getUserVariable(user, 'dayNumberDone', 0)
// Замыкание - оповещаем пользователя что есть ссылка на задачи такого-то дня
def notifyUser = {tgUser, userData -> 
    def marathonDay = 0 //Читать день марафона из соответствующей переменной и/или из даты старта
    
    def userData1 = telegramUsersData.get(tgUser.userId);
    if (userData1 == null) {
        return -1;
    }
    
    def dayNumberDone = Integer.parseInt(userData1.variables.getOrDefault('dayNumberDone', 0));
    def dayNumberSend = Integer.parseInt(userData1.variables.getOrDefault('dayNumberSend', 1));    

   // def token = userData.user.token;
    
    def defaultMessage = "";

    def message = "";
    defaultMessage = "Привет, {name}! Мы видим, что ты еще не успел сделать задание. Обрати внимание, что новое задание тебе прийдет сегодня в 16:00";
    message = defaultMessage.replace('${name}', tgUser.firstName);
    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: tgUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}

//Пробегаемся по пользователям
for(telegramUser in telegramUsers) {
    def userData = telegramUsersData.getOrDefault(telegramUser.userId, null);
    def tgUserId = telegramUser.userId;
    def tgUserInfo = telegramUser.userId + ' (' + telegramUser.getFullName() + ')';
//    def marathonDay1 = Integer.parseInt(commonActions.getUserVariable(user, 'dayNumberDone', 3));



//    debug.log(tgUserInfo, marathonDay1);


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


