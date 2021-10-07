string_in = input("Please, enter command with parameters\n")
elements = string_in.split(' ')

if not len(string_in) > 0:
    string_out = 'Your input is empty' #string_out =
elif not string_in[0]=='/':
    string_out = 'First symbol of your input is not slash.'
elif not ' ' in string_in:
    string_out = 'Your input does not contain spaces'
elif not elements[1].isdigit():
    string_out = 'Parameter of command is not digit'
else:
    string_out = 'Your input is correct' #string_out =
print(string_out)

