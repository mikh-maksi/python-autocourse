string_out = '/addcat tech'
elements = string_out.split(' ')
f = open('categ_list.txt','r')
for line in f:
    categories = line
f.close
f = open('categ_list.txt','w')
f.write(categories)
f.write(' '+elements[1])
f.close()
