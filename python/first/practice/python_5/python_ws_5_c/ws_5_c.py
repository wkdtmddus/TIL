def restructure_word(word, arr):
    for wd in word:
        if wd.isdecimal():
            for w in range(int(wd)):
                arr.pop()
        else:
            arr.remove(wd)
    return arr

original_word = '코딩 공부는ㄴ 1일ㄹ 1커ㅓ밋ㅅ @@@#^()#_+!&~:"'
word = '1ㄴ2ㄹ3ㅓ4ㅅ5'
arr = []

arr.extend(original_word)
print(arr)

result = restructure_word(word, arr)
print(result)

result2 = ''.join(result)
print(result2)