# git

`리누스 토르발스`가 개발한 분산형 버전 관리 시스템(VCS)

### git 영역
>working directory : 실제 작업 중인 파인들이 위치하는 영역  

>staging area : 변경된 파일 중, 다음 버전에 포함시킬 파일들을 선택적으로 추가하거나 제외할 수 있는 중간 준비 영역  

>repository : 버전 이력과 파일들이 영구적으로 저장되는 영역. 모든 버전과 변경 이력이 기록됨  

>* 버전 = commit : 변경된 파일들을 저장하는 행위. 'snapshot'

### git CLI
```
git init : 초기화

git add : 변경사항을 staging area에 추가

git commit -m '메세지' : staging area에 있는 파일들을 저장소에 기록

git status : 현재 상태 확인

git config --global --list : git global 설정 확인
(= git config --global -l)

git log : commit 확인

git log --oneline : commit 목록 한 줄로 보기
```
```
git add . : 현재 디렉토리 모두 staging area에 추가

.git : 숨김파일
(.git을 지우면 git init이 취소됨)

* 'git 저장소 안에 또 다른 git 저장소는 존재할 수 없다.'
```
---
### git 상황
local(사용자가 직접 조작하는 환경) <-> global(전역) <-> online, remote(원격)

---
참고
>VCS(Version Control System)
---
CLI를 통해 git을 이용하여 원격 저장소에 로컬 파일을 쉽게 commit 할 수 있습니다.  
git은 '분산형 버전 관리 시스템'이기 때문에 팀원 또는 다른 사람들과 같이 저장소를 만들어 갈 수 있습니다.  
<div style="text-align : right">2024011</div>

---
