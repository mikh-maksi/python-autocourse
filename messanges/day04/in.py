flag = 1
while flag :
    message = input("Please enter message\n")
    elements = message.split(" ")
    if elements[0][0] == '/':
        flag = 0
        string_out = 'Благодарим за ввод команды'
    else:
        string_out = 'К сожалению, вы не ввели команду'
    print(string_out)
    

        