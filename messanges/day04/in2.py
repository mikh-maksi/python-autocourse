flag = 0
string_out = ''
string_in = input("Please enter message\n")
elements = string_in.split(" ")
cat_list = ['eat','transport','coffee','start','help']

if not elements[0][1:] in cat_list:
    string_out += 'You command in not in command list.\n'
    flag = 1
    
if not len(elements)>=2:
    string_out += "Please put 2 or more elements, devide by space.\n"
    flag = 1
elif not elements[1].isdigit():
    string_out += "Second parameter must be a digit.\n"
    flag =1

if not elements[0][0]=='/':
    string_out += "You message is not command\n"
    flag = 1

if flag == 0:
    string_out = 'It is ok'


print(string_out)

