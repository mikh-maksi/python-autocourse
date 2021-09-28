def telegramUserQueryParams = [:];
telegramUserQueryParams['tag'] = 'PY-TA'; // Тег Телеграм пользователей
// telegramUserQueryParams['maxRegisterDaysFromNow'] = '28'; //Пользователи, не старше 28 дней
telegramUserQueryParams['idIs'] = '394735340'; //ДЕБАГ - только мне слать
// telegramUserQueryParams['phoneIs'] = '380997852751'; //ДЕБАГ - только мне слать

def telegramUsers = commonActions.getTelegramUsersWithAllConditions(telegramUserQueryParams);
