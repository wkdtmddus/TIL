class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def talk(self):
        print(f'반갑습니다. {self.name}입니다.')


class Professor():
    def __init__(self, name, age, department):
        self.name = name
        self.age = age
        self.department = department


class Student():
    def __init__(self, name, age, gpa):
        self.name = name
        self.age = age
        self.gpa = gpa
