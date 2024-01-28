# 아래 함수를 수정하시오.
def count_character(string, s):
    # lst = list(string)
    # return lst.count('o')

    count = 0
    for i in string:
        if i == s:
            count += 1
    return count



result = count_character("Hello, World!", "o")
print(result)  # 2