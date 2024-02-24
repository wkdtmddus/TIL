class Animal:
    num_of_animal = 0
    
    def __init__(self):
        Animal.num_of_animal += 1


# 아래 클래스를 수정하시오.
class Cat(Animal):
    def __init__(self, name):
        super().__init__()
        self.name = name

    def meow(self):
        print(f'{self.name}!')


cat1 = Cat("야옹")
cat1.meow()