def add_employee_to_file(record, path):
    f = open(path, "a")  #
    f.write(record + "\n")  #
    f.close()  #

