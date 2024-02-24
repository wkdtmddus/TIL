number_of_people = 0
number_of_book = 100


def increase_user():
    global number_of_people
    number_of_people += 1


name = ['김시습', '허균', '남영로', '임제', '박지원']
age = [20, 16, 52, 36, 60]
address = ['서울', '강릉', '조선', '나주', '한성부']


def create_user(name, age, address):
    increase_user()
    print(f'{name}님 환영합니다!')
    user_info = {
        'name' : name,
        'age' : age,
        'address' : address
    }
    return user_info


many_user = list(map(create_user, name, age, address))



# def lambda_1(user_info):
#     result = {
#         'name': user_info['name'],
#         'books': user_info['age'] // 10
#     }
#     return result

# result = list(map(lambda_1, many_user))
result = list(map(lambda user_info: {'name': user_info['name'], 'books': user_info['age'] // 10}, many_user))


def rental_book(info):
    decrease_book(info['books'])
    print(f'{info["name"]}님이 {info["books"]}권의 책을 대여하였습니다.')


def decrease_book(number):
    global number_of_book
    number_of_book -= number
    print(f'남은 책의 수 : {number_of_book}')


list(map(rental_book, result))