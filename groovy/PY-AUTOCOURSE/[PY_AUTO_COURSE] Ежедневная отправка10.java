import java.time.*;

//Включаем отладку
debug.enable();

commonActions.setUserVariable(user, 'dayNumberSend', 0);


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
    
    def blockHashes = ['0':'ijklmno156','1':'jklmnop157','2':'klmnop6158','3':'op67892162','4':'nop6789161','5':'p678923163','6':'mnop678160','7':'6789234164','8':'lmnop67159','9':'7892345165','10':'892345q166','11':'92345qr167'];

    def blockHash = blockHashes[dayNumberSend + ''];

    def token = userData.user.token;
    
    def taskNumber = marathonDay + 1
    
    def buttonName = "РЕШАТЬ ЗАДАНИЕ ${taskNumber}";

    def solveTasksLink = "https://goit.ua/python-marathon-autocheck/?token=${token}&block=${blockHash}";
    def keyboard = "inlineKeyboard\n${buttonName}=>${solveTasksLink}";
    

    def defaultMessage = ""

    def message = "";
    if (dayNumberDone < dayNumberSend){
        defaultMessage = "Привет, {name}! Мы видим, что ты еще не успел сделать задание. Обрати внимание, что новое задание тебе прийдет сегодня в 16:00"
        message = defaultMessage.replace('${name}', tgUser.firstName);
        commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: tgUser, sendOrdered: false, botName: 'goiteens_python_bot'], keyboard);
    }
    else{
        defaultMessage = "Привет, {name}! Круто, что ты сделал прошлое задание. Новое задание тебе прийдет сегодня в 16:00"
        message = defaultMessage.replace('${name}', tgUser.firstName);
        commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: tgUser, sendOrdered: false, botName: 'goiteens_python_bot']);
    }
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
