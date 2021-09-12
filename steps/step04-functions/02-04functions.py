def  check_list(string_in):
    lst = []
    cat_list = ['eat','transport','ent']
    elements = string_in.split(' ')
    # команда или нет
    if elements[0][0]=='/':
        lst.append(1)
    else:
        lst.append(0)
    # есть ли категория
    if elements[0][0]=='/' and elements[0][1:] in cat_list:
        lst.append(1)
    else:
        lst.append(0)
    # Число ли вводимый параметр
    if elements[1].isdigit():
        lst.append(1)
    else:
        lst.append(0)

    # Общее число параметров
    if len(elements)>1:
        lst.append(int(len(elements)))
    else:
        lst.append(0)
    return lst

print(check_list("/eat 10"))

    
    