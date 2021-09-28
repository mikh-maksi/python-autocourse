import java.time.*;

//Включаем режим отладки
debug.enable();

//Получаем информацию о пользователе
def tgUserInfo = telegramUser.userId + ' (' + telegramUser.getFullName() + ')';


def taskScore = [:];

//День 1
taskScore['python-ta-string-01'] = 2;
taskScore['python-ta-string-02'] = 2;
taskScore['python-ta-string-03'] = 2;
taskScore['python-ta-string-04'] = 2;
taskScore['python-ta-string-05'] = 2;
taskScore['python-ta-string-06'] = 2;
taskScore['python-ta-string-07'] = 2;
taskScore['python-ta-string-08'] = 2;

taskScore['python-ta-commands-if-01'] = 2;
taskScore['python-ta-commands-if-02'] = 2;
taskScore['python-ta-commands-if-03'] = 2;
taskScore['python-ta-commands-if-04'] = 2;
taskScore['python-ta-commands-if-05'] = 2;
taskScore['python-ta-commands-if-06'] = 2;
taskScore['python-ta-commands-if-07'] = 2;

taskScore['python-ta-types-01'] = 2;
taskScore['python-ta-types-02'] = 2;
taskScore['python-ta-types-03'] = 2;
taskScore['python-ta-types-04'] = 2;
taskScore['python-ta-types-05'] = 2;
taskScore['python-ta-types-06'] = 2;
taskScore['python-ta-types-07'] = 2;


taskScore['python-ta-string2list-01'] = 2;
taskScore['python-ta-string2list-02'] = 2;
taskScore['python-ta-string2list-03'] = 2;
taskScore['python-ta-string2list-04'] = 2;
taskScore['python-ta-string2list-05'] = 2;
taskScore['python-ta-string2list-06'] = 2;




def msg = taskId;




//commonActions.sendTelegramSimpleTextMessage(msg, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);


//User solved last task - set variable
// if (taskId.equals('python-ta-string-07')) {
//     commonActions.setUserVariable(user, 'finishedPythonMarathon', true);
//     commonActions.setUserVariable(user, 'finishPythonMarathonDate', LocalDateTime.now());
    
//     debug.log(tgUserInfo, '[USER FINISHED PYTHON GAME] Пользователь прошел игру');
// }

//Add score
if (taskScore.containsKey(taskId)) {
    def score = taskScore[taskId];
    
    //Set current score
    def currentScore = Integer.parseInt(commonActions.getUserVariable(user, 'pythonCurrentScore', 30));
    currentScore += score;
    
    commonActions.setUserVariable(user, 'pythonCurrentScore', currentScore);
    
    //update oldScore
    commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
}

//Send message after day1
if (taskId.equals('python-ta-string-08')) {
   // commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 1);
    def message =
'''
👏ЮХУ! Первая ступенька преодолена! Поздравляю!
Ты завершил все задачи первого дня.

Следующее задание придет завтра в 16:00.


${name}, как ощущения?

Оцени по шкале от 1 до 5, где:

1 - не понравилось, неинтересно🙁
5 - очень круто, чувствую себя супер-героем🔥
'''.replace('${name}', telegramUser.getFirstName());
    def keyboard =
'''inlineKeyboard
1=>pymarathon_day1_m1_1|2=>pymarathon_day1_m1_2|3=>pymarathon_day1_m1_3|4=>pymarathon_day1_m1_4|5=>pymarathon_day1_m1_5''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot'], keyboard);
}
//Send message after day2
if (taskId.equals('python-ta-commands-if-07')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 2);
    //Добавляем в переменную сделанные дни +1
    //Чтобы в 16:00 отправляло новое сообщение или напоминало, что нужно доделать существующее
    def message = 
    '''
    Круто! Ты набираешь обороты, а твой бот уже научился реагировать на команды start и help, вот это да🔥
    
    Отдыхай и готовься, ведь завтра в 16:00 мы запрограммируем еще больше функционала. 
    
    На связи!
    ''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);

}

//Send message after day3
if (taskId.equals('python-ta-types-07')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 3);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}


//Send message after day4
if (taskId.equals('python-ta-strings2list-07')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 4);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}

//Send message after day5
if (taskId.equals('python-ta-files-05')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 5);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}


//Send message after day6
if (taskId.equals('python-ta-for2list-01')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 6);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}

//Send message after day7
if (taskId.equals('python-ta-functions-06')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 7);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}


//Send message after day8
if (taskId.equals('python-ta-functions-check-01')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 8);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}


//Send message after day9
if (taskId.equals('python-ta-lists-05')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 9);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}

//Send message after day10
if (taskId.equals('python-ta-lists-count-01')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 10);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}

//Send message after day11
if (taskId.equals('python-ta-dict-01')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 11);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}


//Send message after day12
if (taskId.equals('python-ta-dict-count-01')) {
    //commonActions.setUserVariable(user, 'pythonOldScore', currentScore);
    commonActions.setUserVariable(user, 'dayNumberDone', 12);
    def message = 
    '''
    ЕЕЕ! Отличный результат! Твой мозг поработал на славу😇
    
    Жди завтра в 16:00 новое задание.
    
    Дальше - больше😉

''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);
}



