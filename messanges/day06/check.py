def check(message):
    if len(message) > 0:
        return 'Your input is correct' 
    else:
        return 'Your input is empty'

string_in = input("Введите команду с параметром\n")
string_out = check(string_in)
print(string_out)