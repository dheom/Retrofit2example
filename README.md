#### 1. CatFacts 데이터 클래스
역할: 고양이 사실 데이터를 저장합니다.
연관성: ApiInterface와 RetrofitInstance에서 API 응답을 받을 때 사용됩니다. MainActivity에서는 이 데이터를 가져와 UI에 표시합니다.
#### 2. Util 객체
역할: API의 기본 URL을 저장합니다.
연관성: RetrofitInstance에서 Retrofit 객체를 생성할 때 기본 URL로 사용됩니다.
#### 3. RetrofitInstance 객체
역할: Retrofit 인스턴스를 생성하고 API 인터페이스를 구현합니다.
연관성: ApiInterface를 사용하여 API 호출을 실행합니다. MainActivity에서는 RetrofitInstance.api를 사용하여 고양이 사실을 가져옵니다.
#### 4. ApiInterface 인터페이스
역할: API 요청을 정의합니다.
연관성: RetrofitInstance에서 이 인터페이스를 구현하여 API 호출 메서드를 제공합니다. MainActivity에서는 이 API를 사용하여 고양이 사실을 가져옵니다.
#### 5. MainActivity 클래스
역할: 고양이 사실을 가져와 UI에 표시합니다.
#### 연관성:
RetrofitInstance.api를 사용하여 ApiInterface의 메서드를 호출하고, 고양이 사실 데이터를 가져옵니다.
API 응답으로 받은 CatFacts 데이터를 사용하여 UI 상태를 업데이트합니다.
sendRequest() 메서드를 통해 비동기적으로 데이터를 가져오고, 성공 시 CatFacts 데이터를 업데이트합니다.
연관성 요약
Util: 기본 URL을 제공합니다.
RetrofitInstance: Util의 URL을 사용하여 Retrofit 인스턴스를 생성하고 ApiInterface를 구현합니다.
ApiInterface: API 요청을 정의하고, Retrofit을 통해 호출됩니다.
MainActivity: RetrofitInstance를 통해 ApiInterface의 메서드를 호출하여 CatFacts 데이터를 가져옵니다. 가져온 데이터를 UI에 표시합니다.
CatFacts: API 응답 데이터를 저장하는 모델로 사용되며, MainActivity에서 UI 상태로 사용됩니다.
