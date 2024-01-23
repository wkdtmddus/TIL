# git

`리누스 토르발스`가 개발한 분산형 버전 관리 시스템(VCS)

### git 영역
>working directory: 실제 작업 중인 파인들이 위치하는 영역  

>staging area: 변경된 파일 중, 다음 버전에 포함시킬 파일들을 선택적으로 추가하거나 제외할 수 있는 중간 준비 영역  

>repository: 버전 이력과 파일들이 영구적으로 저장되는 영역. 모든 버전과 변경 이력이 기록됨  

>* 버전 = commit: 변경된 파일들을 저장하는 행위. `snapshot`

### git CLI
```
git init: 초기화

git add: 변경사항을 staging area에 추가

git commit -m '메세지': staging area에 있는 파일들을 저장소에 기록

git status: 현재 상태 확인

git config --global --list: git global 설정 확인
(= git config --global -l)

git log: commit 확인

git log --oneline: commit 목록 한 줄로 보기
```
```
git add .: 현재 디렉토리 모두 staging area에 추가

.git: 숨김파일
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
git은 `분산형 버전 관리 시스템`이기 때문에 팀원 또는 다른 사람들과 같이 저장소를 만들어 갈 수 있습니다.  
<div style="text-align: right">20240111</div>

---
---
# remote repository
원격 저장소: repository에 commit을 업로드
`ex) gitlab, github, bitbucket`

### git CLI
```
git remote add origin remote_repo_url: 로컬 저장소에 원격 저장소 추가
(origin: 추가하는 원격 저장소 별칭)

git remote -v: 원격 저장소 확인

git remote rm remote_repo_url: 등록된 원격 저장소 삭제
```
### push, pull, clone
```
git push -u origin master: 원격 저장소에 commit 목록을 업로드
(windows 자격 증명, 워킹트리 꼭 확인)

git pull origin master: 원격 저장소의 변경사항만을 받아옴

git clone remote_repo_url: 원격 저장소 전체를 복제
(clone으로 받은 프로젝트는 이미 git init이 되어 있음. remote add 불필요)
```
### gitingnore
특정 파일이나 디렉토리를 추적하지 않도록 설정하는 텍스트 파일  
.gitignore 파일 생성  
* 주의사항  
  이미 git의 관리를 받은 것은 나중에 gitignore 해도 적용이 되지 않음
* 참고
  gitignore.io: 기본 gitignore 셋팅 사이트
---
git init은 같은 경로에서 하나만 만들어야합니다. 내부에 또 하면, 내부의 commit들을 읽을 수가 없습니다.  
gitignore.io에서 관련된 프로그램을 간단하게 작성하면, 편리하게 파일을 만들어 줍니다.  
<div style="text-align: right">20240112</div>