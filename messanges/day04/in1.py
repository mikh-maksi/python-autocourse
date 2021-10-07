flag = 1
while flag :
    string_in = input("Please enter message\n")
    elements = string_in.split(" ")
    cat_list = ['eat','transport','coffee','start','help']
    if elements[0][1:] in cat_list:
        flag = 0
        string_out = 'It is ok'
    else:
        string_out = 'You command in not in command list.'
    print(string_out)

