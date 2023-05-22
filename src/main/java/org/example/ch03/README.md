# ArrayList 클래스

[MyArrayList](/org/example/ch02/MyArrayList.java) 클래스에서 구현한 메서드를 살펴보자


* ### get
```java
public T get(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException();
    }
    return array[index];
}
```

get 메서드는 상수 시간에 동작한다.  만약 정해진 size 를 벗어나게 된다면 자주 만나는 에러 `IndexOutOfBoundsException`을 마주하게 된다.  

* ### set
```java
public T set(int index, T element) {
    T old = get(index);
    array[index] = element;
    return old;
}
```

set 메서드 역시 상수 시간에 동작한다. 그리고 set 에서는 명시적으로 배열의 범위를 검사하지 않는다.  
이 일은 get 메서드에서 처리하기 때문이다.  

* ### indexOf

```java
public int indexOf(Object target) {
    for (int i = 0; i < size; i++) {
        if (equals(array[i], target)) {
            return i;
        }
    }
    return -1;
}
```

indexOf 메서드는 선형 시간을 갖는다. 0번째 인덱스부터 순차적으로 equals 메서드를 실행하기 때문이다.  

```java

private boolean equals(Object target, Object element) {
    if (target == null) {
        return element == null;
    } else {
        return target.equals(element);
    }
}
```


* ### remove

```java
public T remove(int index) {
    // index 뒤에 있는 녀석들을 앞당김
    T element = get(index);
    for (int i = index; i<size-1; i++) {
        array[i] = array[i+1];
    }
    size--;
    return element;
}
```

remove 메서드 역시 선형 시간을 갖는다.  
파라미터로 넘어온 index 뒤에 있는 원소들을 하나씩 앞으로 당겨준다. 만약 index 가 뭔소의 마지막 index라면 이때는 상수 시간을 갖게 되지만, 
시간 복잡도 계산은 평균으로 계산한다.  


## add 메서드 분류하기

add 메서드에는 인덱스와 요소를 인자로 받는, 즉 두 인자 버전 메서드인 add(int, E) 와 단일 인자 버전 메서드인 add(E) 
가 있다.  


```java
public boolean add(T element) {
    if (size >= array.length) {
        T[] bigger = (T[]) new Object[array.length*2];
        System.arraycopy(array, 0, bigger, 0, array.length);
        array = bigger;
    }
    array[size] = element;
    size++;
    return true;
}


public void add(int index, T element) {
    if (index < 0 || index > size) {
    throw new IndexOutOfBoundsException();
    }
    // add the element to get the resizing
    add(element);

    // shift the elements
    for (int i=size-1; i>index; i--) {
    array[i] = array[i-1];
    }
    // put the new one in the right place
    array[index] = element;
}
```

두 인자 버전 메서드 *add(int, E)* 는 단일 인자 버전 메서드 *add(E)* 를 호출하고 *add(E) 메서드는 새로운 인자를 마지막에 넣습니다. 
그 다음 다른 요소를 오른쪽으로 이동시키고 올바른 자리에 새로운 요소를 넣는다.  
단일 인자 버전은 배열에 미사용 공간이 있다면 add 메서드는 상수 시간이다. 하지먄 배열의 크기를 변경하면 System.arraycopy 메서드 호출 시 
실행시간이 배열의 크기에 비례하기 때문에 add 메서드는 선형이다.  

*add(E)* 메서드는 상수 시간일까? 간단한 예제를 두고 살펴보자.  
두 요소만큼의 공간이 있는 배열이 있다.  

* 첫번째로 add 메서드를 호출하면 배열에서 사용하지 않는 공간을 찾아서 요소 1을 저장
* 두번째 호출에서도 배열에서 사용하지 않는 공간을 찾아서 요소 1을 저장
* 세번째 호출에서는 배열의 크기를 변경하고 요소 2개를 복사, 요소 1을 저장 => 배열의 크기 4
* 네번째에는 요소 1을 저장
* 다섯번째에는 배열의 크기를 재조정하고 요소 4개를 복사하며 요소 1을 저장 => 배열의 크기 8
* 다음 3번의 add 메서드 호출로 요소 3개 저장
* 다음 add 메서드 호출로 요소 8개 복사, 요소 1 저장 => 배열의 크기 16
* 다음 7번의 add 메서드 호출로 7개의 요소 저장
* 4번의 add 메서드 호출 후에는 요소 4개를 저장하고 2번 복사
* 8번의 add 메서드 호출 후에는 요소 8개를 저장하고 6번 복사
* 16번의 add 메서드 호출 후에는 요소 16개를 저장하고 14번 복사


n번 추가하면 요소 n개를 저장하고 n-2 개를 복사하는 패턴을 가지게 된다.  
따라서 총 연산 횟수는 n + n - 2, 즉 2n - 2 이다.  

add 메서드의 평균 연산 횟수를 구하려면 합을 n으로 나눠야 해서 결과는 2 - 2/n 이다. 
n이 커지면 2/n은 작아지게 되기에 add 메서드는 상수 시간으로 간주한다.  


## 문제 크기

```java
public boolean removeAll(Collection<?> collection) {
    boolean flag = true;
    for (Object obj: collection) {
        flag &= remove(obj);
    }
    return flag;
}
```


반복문을 돌 때마다 removeAll 메서드는 선형인 remove 메서드를 호출한다. 그래서 removeAll 메서드를 이차로 생각하기 쉽다. 
하지만 모든 경우가 그런 것은 아니니 조심해야한다.  

반복문이 한개라면 보통 선형이고, 반복문 두 개가 중첩되었다면 보통 이차이다. 하지만 각 반복문을 몇 번 실행하는지 생각해야 한다.  

