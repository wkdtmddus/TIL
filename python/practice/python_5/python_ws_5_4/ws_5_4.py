# 아래 함수를 수정하시오.
def capitalize_words(string):
    # result = string.title()

    lst = string.split(' ')
    lst[0] = lst[0].capitalize()
    lst[1] = lst[1].capitalize()
    result = ' '.join(lst)
    return result

result = capitalize_words("hello, world!")
print(result)