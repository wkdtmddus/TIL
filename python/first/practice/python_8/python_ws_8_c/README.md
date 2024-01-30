### 다중 상속의 이해 Lv3
목표  
- 파이썬 클래스의 기본적인 개념(객체, 인스턴스)에 대해 이해한다.
- 파이썬 클래스 상속, 다중 상속에 대해 이해한다.
- 파이썬 프로그램 실행 시, 발생할 수 있는 예외 상황을 이해하고 대처한다.
---
문제  
ws_8_b에서 작성한 코드에서 이어서 진행한다.  
모델 클래스(BaseModel)와 그를 상속받는 두 가지 모델 클래스(Novel, Other)가 있다. 다중 상속을 활용하여 새로운 모델 클래스를 만들고, 요구 사항을 만족하는 코드를 작성하시오.   

요구사항  
새로운 모델 클래스 ExtendModel을 만든다.  
ExtendModel은 Novel과 Other클래스를 다중 상속받아야 한다.  
ExtendModel은 새로운 속성 extended_type을 가져야 한다.  
ExtendModel클래스를 이용하여 새로운 모델 인스턴스 extended_instance를 생성한다.  
인스턴스의 속성들을 적절히 초기화한다.  
ExtendModel클래스에 display_info메서드를 추가한다.  
이 메서드는 해당 모델의 PK, TYPE 그리고 extended_type을 출력한다.  
ExtendModel의 save메서드를 오버라이딩하여 "데이터를 확장해서 저장합니다."를 출력한다.  
extended_instance의 display_info메서드를 호출하여 정보를 출력한다.  
extended_instance의 save메서드를 호출하여 저장 메세지를 출력한다.  
모든 모델 클래스의 인스턴스 생성과 메서드 호출 겱과를 확인하여 적절한 출력을 한다.
```
ExtendedModel 인스턴스의 정보 출력 및 저장 메서드 호출
PK: 1, TYPE: Other Model, Extended Type: Extended Type
데이터를 확장해서 저장합니다.
```
---
클래스를 구현할 때, 매개변수로 클래스를 입력하면 해당 클래스를 상속받을 수 있습니다.  
`super()`를 사용하여 상속하는 클래스의 인자를 받아올 수 있습니다.  
클래스 자신만의 변수는, 본인 클래스의 내부에 변수를 생성하면 됩니다.  
`save`메서드를 동일한 입력으로 작성하면, 그대로 덮어씌워 오버라이딩이 됩니다. 상속하는 클래스의 메서드와는 무관한 코드가 실행됩니다.
<div style="text-align: right">20240125</div>