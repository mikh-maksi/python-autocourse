def chek_text(lst):
    string_out = ''

    if lst[0] == 0:
        string_out +='\nThis is no command'
    if lst[1] == 0:
        string_out +='\nCategory in not in list'
    if lst[2] == 0:
        string_out +='\nParameter is not digit'
    if lst[3] == 0:
        string_out +='\nNo parameters'

    return string_out

print(chek_text([0,0,2,0]))