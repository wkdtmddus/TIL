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
  `gitignore.io`: 기본 gitignore 셋팅 사이트
---
git init은 같은 경로에서 하나만 만들어야합니다. 내부에 또 하면, 내부의 commit들을 읽을 수가 없습니다.  
gitignore.io에서 관련된 프로그램을 간단하게 작성하면, 편리하게 파일을 만들어 줍니다.  
<div style="text-align: right">20240112</div>

---
참고  
### commit convention
자주 사용하는 태그 종류
```
Feat: 새로운 기능을 추가하는 경우

Fix: 버그를 고친경우

Docs: 문서를 수정한 경우

Style: 코드 포맷 변경, 세미콜론 누락, 코드 수정이 없는경우

Refactor: 코드 리펙토링

Test: 테스트 코드. 리펙토링 테스트 코드를 추가했을 때

Chore: 빌드 업무 수정, 패키지 매니저 수정

Design: CSS 등 사용자가 UI디자인을 변경했을 때

Rename: 파일명(or 폴더명)을 수정한 경우

Remove: 코드(파일)의 삭제가 있을 때. 'Clean', 'Eliminate'를 사용하기도 함
```
기타 태그
```
Add: 코드나 테스트, 예제, 문서등의 추가 생성이 있는경우

Improve: 향상이 있는 경우. 호환성, 검증 기능, 접근성 등

Implement: 코드가 추가된 정도보다 더 주목할만한 구현체를 완성시켰을 때

Move: 코드의 이동이 있는경우

Updated: 계정이나 버전 업데이트가 있을 때 사용. 주로 코드보다는 문서나, 리소스, 라이브러리 등

Comment: 필요한 주석 추가 및 변경
```
---
커밋 컨벤션을 통해 팀원들과 소통하며 repogitory를 관리할 수 있습니다. 숙지하여 원활한 개발이 될 수 있도록 해야합니다.  
<div style="text-align: right">20240123</div>