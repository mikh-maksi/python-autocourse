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

//User solved last task - set variable
if (taskId.equals('python-ta-string-07')) {
    commonActions.setUserVariable(user, 'finishedPythonMarathon', true);
    commonActions.setUserVariable(user, 'finishPythonMarathonDate', LocalDateTime.now());
    
    debug.log(tgUserInfo, '[USER FINISHED PYTHON GAME] Пользователь прошел игру');
}

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
if (taskId.equals('python-ta-string-07')) {
    def message =
'''
👏Поздравляем! Вы завершили все задачи первого дня.
${name}, как ощущения?

Оцените по шкале от 1 до 5, где:

1 - не понравилось, неинтересно🙁
5 - очень круто, чувствую себя супер-героем🔥
'''.replace('${name}', telegramUser.getFirstName());
    def keyboard =
'''inlineKeyboard
1=>pymarathon_day1_m1_1|2=>pymarathon_day1_m1_2|3=>pymarathon_day1_m1_3|4=>pymarathon_day1_m1_4|5=>pymarathon_day1_m1_5''';

    commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false, botName: 'goit_python_game_bot'], keyboard);
}

