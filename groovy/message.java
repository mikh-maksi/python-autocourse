debug.enable();

def blockTasks = [
        '1': 'html-auto-hw-1-1 html-auto-hw-1-2 html-auto-hw-1-3 html-auto-hw-1-4 html-auto-hw-1-5 html-auto-hw-1-6 html-auto-hw-1-7 html-auto-hw-1-8 html-auto-hw-1-9 html-auto-hw-1-10 html-auto-hw-1-11 html-auto-hw-1-12 html-auto-hw-1-13 html-auto-hw-1-14 html-auto-hw-1-15 html-auto-hw-1-16 html-auto-hw-1-17',
        '2': 'html-auto-hw-1-18 html-auto-hw-1-19 html-auto-hw-1-20 html-auto-hw-1-21 html-auto-hw-1-22',
        '3': 'html-auto-hw-2-1 css-auto-hw-2-2 css-auto-hw-2-3 css-auto-hw-2-4 css-auto-hw-2-5 css-auto-hw-2-6 css-auto-hw-2-7 css-auto-hw-2-8 css-auto-hw-2-9 css-auto-hw-2-10 css-auto-hw-2-11 css-auto-hw-2-12 css-auto-hw-2-13 html-auto-hw-2-14 css-auto-hw-2-15 css-auto-hw-2-16 css-auto-hw-2-17 css-auto-hw-2-18 css-auto-hw-2-19 css-auto-hw-2-20 css-auto-hw-2-21 css-auto-hw-2-22 html-auto-hw-2-23 css-auto-hw-2-24 css-auto-hw-2-25 css-auto-hw-2-26 css-auto-hw-2-27 css-auto-hw-2-28',
        '4': 'css-auto-hw-3-1 css-auto-hw-3-2 css-auto-hw-3-3 css-auto-hw-3-4 css-auto-hw-3-5 css-auto-hw-3-6 css-auto-hw-3-7 css-auto-hw-3-8 css-auto-hw-3-9 css-auto-hw-3-10 css-auto-hw-3-11 css-auto-hw-3-12',
        '5': 'css-auto-hw-4-1 css-auto-hw-4-2 css-auto-hw-4-3 css-auto-hw-4-4 css-auto-hw-4-5 css-auto-hw-4-6 css-auto-hw-4-7 css-auto-hw-4-8 css-auto-hw-4-9 css-auto-hw-4-10 css-auto-hw-4-11 css-auto-hw-4-12 ',
        '6': 'css-auto-hw-5-1 css-auto-hw-5-2 css-auto-hw-5-3 css-auto-hw-5-4 css-auto-hw-5-5',
        '7': 'css-auto-hw-6-1 css-auto-hw-6-2 css-auto-hw-6-3 css-auto-hw-6-4 css-auto-hw-6-5 css-auto-hw-6-6 css-auto-hw-6-7 css-auto-hw-6-8 css-auto-hw-6-9 css-auto-hw-6-10',
        '8': 'css-auto-hw-7-1 css-auto-hw-7-2 css-auto-hw-7-3',
        '9': 'css-auto-hw-8-1 css-auto-hw-8-2 css-auto-hw-8-3 css-auto-hw-8-4 css-auto-hw-8-5 css-auto-hw-8-6 css-auto-hw-8-7 css-auto-hw-8-8',
        '10': 'css-auto-hw-9-1 css-auto-hw-9-2 css-auto-hw-9-3 css-auto-hw-9-4 css-auto-hw-9-5',
        '11': 'html-auto-hw-10-1 html-auto-hw-10-2 html-auto-hw-10-3 html-auto-hw-10-4 html-auto-hw-10-5 html-auto-hw-10-6 html-auto-hw-10-7',
        '12': 'css-auto-hw-10-8 css-auto-hw-10-9 css-auto-hw-10-10',
        '13': 'html-auto-hw-11-1 html-auto-hw-11-2 html-auto-hw-11-3 html-auto-hw-11-4 html-auto-hw-11-5',
        '14': 'css-auto-hw-12-1 css-auto-hw-12-2 css-auto-hw-12-3 css-auto-hw-12-4 css-auto-hw-12-5 css-auto-hw-12-6 css-auto-hw-12-7 css-auto-hw-12-8 css-auto-hw-12-9 css-auto-hw-12-10',
        '15': 'css-auto-hw-13-1 css-auto-hw-13-2 css-auto-hw-13-3 css-auto-hw-13-4 css-auto-hw-13-5 css-auto-hw-13-6 css-auto-hw-13-7',
        '16': 'css-auto-hw-14-1 html-auto-hw-14-2 html-auto-hw-14-3 html-auto-hw-14-4 html-auto-hw-14-5'
];

def userData = commonActions.getAggregatedDataForTelegramUsers([telegramUser])[telegramUser.userId];

//Замыкание, которое определяет прогресс блока
def getBlockProgress = {buttonText ->
    def taskIdArray = blockTasks[buttonText].split(' ');
    def solvedCount = 0;
    
    for(def taskId: taskIdArray) {
        if (userData.passedTasks.contains(taskId)) {
            solvedCount++;
        }
    }
    
    return (int) (100f * (float) solvedCount / (float) taskIdArray.length);
}

//Замыкание, которое делает отдельную кнопку блока
def makeBlockButton = {buttonText, blockHash ->
    def token = user.token;
    def progress = getBlockProgress(buttonText);
    
    def fullButtonText = "Занятие ${buttonText} - ${progress} %"
    return "${fullButtonText}=>https://html-css-autocheck.goit.ua/?token=${token}&block=${blockHash}";
}

def blockButtons = [
    makeBlockButton('1', 'nop678917'),
    makeBlockButton('2', 'yz01abc36'),
    makeBlockButton('3', 'p67892319'),
    makeBlockButton('4', '678923420'),
    makeBlockButton('5', '892345q22'),
    makeBlockButton('6', '92345qr23'),
    makeBlockButton('7', '2345qrs24'),
    makeBlockButton('8', '345qrst25'),
    makeBlockButton('9', '45qrstu26'),
    makeBlockButton('10', '5qrstuv27'),
    makeBlockButton('11', 'tuvwxyz31'),
    makeBlockButton('12', 'z01abcd37'),
    makeBlockButton('13', '01abcde38'),
    makeBlockButton('14', 'uvwxyz032'),
    makeBlockButton('15', 'wxyz01a34'),
    makeBlockButton('16', '1abcdef39')
];

def buttonsPerRow = 2;
def keyboard = 'inlineKeyboard\n';
for(int i = 0; i < blockButtons.size(); i++) {
    keyboard += blockButtons[i];
    
    if (i > 0 && (i+1)%buttonsPerRow == 0) {
        keyboard += '\n';   
    } else if (i < (blockButtons.size() -1)) {
        keyboard += '|';
    }
}

def message = 
'''
Ваш прогресс показан ниже в % на каждой кнопке.

Выбирайте занятие ниже, нажимайте на кнопку, и решайте задания.

Вы всегда можете написать слово *html*, и получить свой текущий прогресс.
''';

commonActions.sendTelegramSimpleTextMessage(message, [telegramUser: telegramUser, sendOrdered: false], keyboard);