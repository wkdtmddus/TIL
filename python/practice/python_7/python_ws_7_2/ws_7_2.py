# 아래 클래스를 수정하시오.
class Shape:
    def __init__(self, w, h):
        self.w = w
        self.h = h
    
    def calculate_area(self):
        return self.w * self.h

shape1 = Shape(5, 3)
area1 = shape1.calculate_area()
print(area1)