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

def msg = taskId;

if (taskId.equals('python-ta-string-01')) {
    msg = msg + "fist";
}
if (taskId.equals('python-ta-string-02')) {
    msg = msg + "second";
}
if (taskId.equals('python-ta-string-03')) {
    msg = msg + "third";
}




commonActions.sendTelegramSimpleTextMessage(msg, [telegramUser: telegramUser, sendOrdered: false, botName: 'goiteens_python_bot']);


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



tgUserTag=PY-TA,tgUserSourceBotName=GoITeens_Python_autocourse_bot