# 아래 클래스를 수정하시오.
class Shape:
    def __init__(self, w, h):
        self.w = w
        self.h = h

    def __str__(self):
        return f'Shape: width={self.w}, height={self.h}'

shape1 = Shape(5, 3)
print(shape1)