# 아래 클래스를 수정하시오.
class UserInfo:
    def __init__(self):
        self.user_data = {}
        
    def get_user_info(self):
        try:    
            name = input('이름을 입력하세요: ')
            age = int(input('나이를 입력하세요: '))
            info = {name : age}
            self.user_data.update(info)
        except BaseException:
            print('나이는 숫자로 입력해야 합니다.')    



    def display_user_info(self):
        self.lst = list(self.user_data.keys())
        if self.lst != []:
            print('사용자 정보:')
            print(f'이름: {self.lst[0]}')
            print(f'나이: {self.user_data[self.lst[0]]}')
        else:
            print('사용자 정보가 입력되지 않았습니다.')
        


user = UserInfo()
user.get_user_info()
user.display_user_info()