string_in1="/eat 10 bread"
string_in2="/eat 10 bread"
records = []
records.append(string_in1)
records.append(string_in2)
eat_costs = 0
ent_costs = 0
for record in records:
    elements = record.split(' ')
    if elements[0][1:]=='eat':
        eat_costs += int(elements[1])
    if elements[0][1:]=='ent':
        ent_costs += int(elements[1])
print(eat_costs)
print(ent_costs)