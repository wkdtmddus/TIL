# 아래 클래스를 수정하시오.
class Shape:
    def __init__(self, w, h):
        self.w = w
        self.h = h


    def calculate_perimeter(self):
        return 2 * self.w + 2 * self.h


shape1 = Shape(5, 3)
perimeter1 = shape1.calculate_perimeter()
print(perimeter1)