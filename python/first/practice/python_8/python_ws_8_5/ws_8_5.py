class Animal:
    num_of_animal = 0
    
    def __init__(self):
        Animal.num_of_animal += 1


class Dog(Animal):
    sound = '멍멍'
    def __init__(self):
        super().__init__()


    def bark(self):
        print(self.sound)


class Cat(Animal):
    sound = '야옹'
    def __init__(self):
        super().__init__()



    def meow(self):
        print(self.sound)


# 아래 클래스를 수정하시오.
class Pet(Dog, Cat):
    def __init__(self):
        super().__init__()


    def play(self):
        print('애완동물과 놀기')
    
    
    def make_sound(self):
        print(self.sound)

    def __str__(self):
        return f'애완동물은 {self.sound} 소리를 냅니다'

pet1 = Pet()
print(pet1)