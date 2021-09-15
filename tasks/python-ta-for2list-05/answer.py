records = []

for i in range(5):
    string_in=input("input costs\n")
    records.append(string_in)#

print(records)

eat_costs = 0
ent_costs = 0
transport_costs = 0
for record in records:
    elements = record.split(' ')
    if elements[0][1:]=='eat':
        eat_costs += int(elements[1])
    if elements[0][1:]=='ent':
        ent_costs += int(elements[1])
    if elements[0][1:]=='transport':
        transport_costs += int(elements[1])

total_costs.append(eat_costs)
total_costs.append(ent_costs)
total_costs.append(transport_costs)

print(eat_costs)
