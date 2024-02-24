# 아래 클래스를 수정하시오.
class Animal:
    num_of_animal = 0
    
    def increase_num(self):
        Animal.num_of_animal += 1


class Dog(Animal):
    def __init__(self):
        super().increase_num()
        


class Cat(Animal):
    def __init__(self):
        super().increase_num()


class Pet(Dog, Cat):
    @classmethod
    def access_num_of_animal(cls):
        return f'동물의 수는 {super().num_of_animal}마리 입니다.'


dog = Dog()
print(Pet.access_num_of_animal())
cat = Cat()
print(Pet.access_num_of_animal())