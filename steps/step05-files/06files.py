import os
def cat_list_value():
    if os.path.exists("categ_list.txt"):
        f = open('categ_list.txt','r')
        for line in f:
            string = line
        elements = string.split(' ')
        f.close()
        return elements
    else:
        return []


def total():
    total_dict = dict.fromkeys(cat_list_value(),0)

    fin_list = []
    f = open('costs_list.txt','r')
    for line in f:
        elements = line.split(' ')
        lst = [elements[0][1:],int(elements[1])]
        fin_list.append(lst)

    for lst in fin_list:
        total_dict[lst[0]] = total_dict[lst[0]] + lst[1]

    return str(total_dict)
print(total())