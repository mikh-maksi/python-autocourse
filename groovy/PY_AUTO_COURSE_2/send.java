import java.time.*;

//Включаем отладку
debug.enable();

commonActions.setUserVariable(user, 'dayNumberSend', 0);


//Выбираем подходящих нам пользователей
def telegramUserQueryParams = [:];
telegramUserQueryParams['tag'] = 'PY_AUTO_COURSE_2'; // Тег Телеграм пользователей
// telegramUserQueryParams['maxRegisterDaysFromNow'] = '28'; //Пользователи, не старше 28 дней
//telegramUserQueryParams['idIs'] = '394735340'; //ДЕБАГ - только мне слать 970719107
// telegramUserQueryParams['idIs'] = '970719107'; //ДЕБАГ - только мне слать
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
    
    marathonDay = Integer.parseInt(userData1.variables.getOrDefault('dayNumberDone', 0));
    debug.log( 'marathonDay='+marathonDay);

    def dayNumberSend = Integer.parseInt(userData1.variables.getOrDefault('dayNumberSend', 5));
  //  if (dayNumberSend==1){dayNumberSend = 2;}
    
    debug.log( 'dayNumberSend='+dayNumberSend);
    marathonDay = dayNumberSend;

    dayNumberSend = dayNumberSend + 1;

    daySend = dayNumberSend;
    debug.log('dayNumberSend+1='+dayNumberSend);
    
    commonActions.setUserVariable(userData.user, 'dayNumberSend', dayNumberSend); //Сколько сейчас фактически жизней


    //commonActions.setUserVariable(user, 'dayNumberSend', dayNumberSend);
    
    
    
 //  marathonDay = Integer.parseInt(commonActions.getUserVariable(user, 'dayNumberDone', 0));
    
    
    
    def blockHashes = ['1':'ijklmno156','2':'jklmnop157','3':'klmnop6158','4':'op67892162','5':'nop6789161','6':'p678923163','7':'mnop678160','8':'6789234164','9':'lmnop67159','10':'7892345165','11':'892345q166','12':'92345qr167'];

    def blockHash = blockHashes[marathonDay + ''];
    def token = userData.user.token;
    
    def taskNumber = marathonDay
    
    def buttonName = "РЕШАТЬ ЗАДАНИЕ ${taskNumber}";

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
Сегодня ты продолжаешь создавать своего телеграм-бота и научишь его различать первые команды!

Я подготовил для тебя 6 интересных задачек в Python-тренажере, поэтому не сомневайся, нажимай кнопку и стартуй!

Увидимся позже ;-)

''';
    messages['3'] =
'''
Ты проходишь курс уже 3-й день! Не время расслабляться  - у меня для тебя новый набор задач по Python.

Твой бот сегодня станет немножко умнее и научится отличать цифры от текста и правильно «читать» сообщения пользователя.
 
Готов пробовать? Нажимай кнопку и стартуй!

Я жду тебя на финише
;-)
''';

    messages['4'] ='''
    Привет! Это снова я! 

Наступил 4й день курса, а значит и новая порция знаний языка Python.

Сегодня ты будешь проверять информацию, которую пишет пользователь, и научишься использовать ее как параметр для различных подсчетов и оперций в боте.

Стартуй! Меньше слов - больше дела ;-)
''';
    messages['5'] ='''
Привет, будущий программист! Я к тебе по расписанию :-)

На часах 16:00 - время получать задание №5.

Сегодня ты разберешься с тем, что такое эхо-бот.
Пока ничего непонятно, но уже интересно?

Давай разбираться вместе:-)

Жми кнопку и увидимся через несколько выполненных заданий.
''';
    messages['6'] ='''
Эгегей! Ты видел какой сегодня день? 

Да-да ты подобралася к середине курса и не останавливаешься на достигнутом!
Отлично!

Давай сегодня научим твоего бота не просто принимать информацию, а еще и сохранять, чтобы никакие данные не потерялись.

Открывай 6й блок заданий и учись создавать файлы в Python.

Let's go!
''';
    messages['7'] ='''Следующий день''';
    messages['8'] ='''Следующий день''';
    messages['9'] ='''Следующий день''';
    messages['10'] ='''Следующий день''';
    messages['11'] ='''Следующий день''';
    messages['12'] ='''Следующий день''';
    
    def message = messages[taskNumber + ''].replace('${name}', tgUser.firstName);
    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: tgUser, sendOrdered: false, botName: 'goiteens_python_bot'], keyboard);
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



//def userData2 = telegramUsersData.get(telegramUser.userId);

//def dayNumberSend = Integer.parseInt(userData2.variables.getOrDefault('dayNumberSend', 0));

//commonActions.setUserVariable(user, 'dayNumberSend', dayNumberSend);


