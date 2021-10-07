string_in = '7'
a = int(string_in)
if a > 0:
    string_out = "Число а является положительным числом"
elif a % 2 == 0:
    string_out = "Число а является четным положительным числом"
else:
    string_out = "Число а не является четным положительным числом"
print(string_out)