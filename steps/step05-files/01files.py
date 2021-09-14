categ_list = ['eat','ent','coffee','transport', 'sport', 'clothers','other']
string_out = ' '.join(categ_list)
f = open('categ_list.txt','w')
f.write(string_out)
f.close()