# 인터페이스

---

인터페이스는 두 가지 의미를 가지고 있다.  
응용 프로그래밍 인터페이스(application programming interface, API)의 맥락에서는 **어떤 기능성을 제공하는 클래스나 메서드의 의미**이다.

자바의 맥락에서는 **언어의 기능으로, 클래스와 유사하게 어떤 메서드 집합을 명시**한다.

자바의 List 인터페이스를 구현하는 클래스로는 `ArrayList` 와 `LinkedList` 가 있다.


## ArrayList v.s LinkedList

ArrayList와 LinkedList의 차이점은 저장 방식이다.  
ArrayList는 메모리에 저장될 때 순차적으로 저장되는 반면, LinkedList는 뛰엄뛰엄 빈 공간을 찾아서 저장하고 이때 다음 원소에 주소도 같이 저장한다.

ArrayList 에 원소를 i번째에 삽입하려면 i번째 뒤에 있는 녀석들은 전부 뒤로 옮기는 작업을 수행해야 하기 때문에 평균적으로 O(n)이 걸리는 반면
LinkedList는 i-1번째 포인터를 현재 위치로 변경하고, i-1가 가지고 있던 포인터를 i가 가지면 된다.



| Time Complexity | ArrayList | LinkedList |
|-----------------|-----------|------------|
| indexing        | O(1)      | O(n)       |
| Insert / remove | O(n)      | O(1)       |