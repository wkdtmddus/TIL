# 아래 함수를 수정하시오.
def reverse_string(string):
    list = []
    for a in string:
        list.append(a)
    list.reverse()
    result = ''.join(list)
    return result
    

result = reverse_string("Hello, World!")
print(result)  # !dlroW ,olleH