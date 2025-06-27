-----

# DecaDive: 1학년 1학기 Java 프로젝트 회고

이 프로젝트는 1학년 1학기에 수강한 \*\*'발명과 창업'\*\*과 **'자바 기초'** 두 수업의 융합 과제로 시작되었습니다. 창업 수업에서 구상한 비즈니스 아이템을 자바 수업에서 배운 객체 지향 프로그래밍(OOP) 개념과 프로그래밍 기술을 활용하여 직접 구현해 보는 것을 목표로 했습니다.

부족한 점이 많은 첫 프로젝트이지만, 아이디어를 구체화하고 배운 내용을 코드로 옮기는 과정 속에서 얻은 배움과 고민의 흔적을 기록으로 남기고자 합니다.

## 💡 프로젝트 선정 이유: "향수(鄕愁)를 탐험하다, DecaDive"

'발명과 창업' 수업에서 '어떻게 하면 사용자의 구매 욕구를 자연스럽게 자극할 수 있을까?'라는 질문에서 프로젝트를 시작했습니다. 저는 **'향수'** 라는 감성에 주목했습니다.

**DecaDive**는 Decade(10년)와 Dive(탐험하다)의 합성어로, 사용자가 그리운 시절(80s, 90s, 00s)로 시간 여행을 떠나 잊고 있던 추억의 아이템들을 발견하는 컨셉의 쇼핑몰입니다. 예를 들어, 어린 시절 좋아했던 포스터를 보러 왔다가 '맞아, 이 만화책도 있었지\!'하며 다른 상품으로 자연스럽게 관심이 이어지는 경험을 통해, 사용자가 더 즐겁게 머물며 쇼핑할 수 있도록 유도하고자 했습니다.

## 🎬 시연 영상

  * **비회원 시연**:

https://github.com/user-attachments/assets/17be2348-c7de-4e69-bdcc-f269d0342fb1

 (상품 둘러보기, 회원가입, 로그인 과정)
  * **회원 시연**: [영상 링크](https://youtu.be/Bwb5YnfAoQQ) (상품 구매, 장바구니, 회원정보 수정 과정)
  * **관리자 시연**: [영상 링크](https://youtu.be/mCZHnqYGMlI) (상품, 회원, 주문 관리 과정)



## 📈 유스케이스 다이어그램

요구사항을 바탕으로 사용자(Actor)와 시스템 간의 상호작용을 표현한 다이어그램입니다.
![데카다이브_유스케이스](https://github.com/user-attachments/assets/ea8e957f-8708-4a93-8f27-c14a487eeb15)


-----

## 📋 요구사항 명세

프로젝트 기획 단계에서 정의한 기능 및 비기능 요구사항입니다.

### **요구사항 정의서**

  * **기능**:
    ![데카_정의_기능](https://github.com/user-attachments/assets/ae715af1-f4ea-465e-bcb3-30522d5333aa)

  * **비기능**:
    ![데카_정의_비기능](https://github.com/user-attachments/assets/c136229e-918a-439e-afb9-d9f4a9dcbbe4)



<details>
<summary><b>기능 요구사항 상세 (Functional Requirements)</b> - 클릭하여 보기/접기</summary>

#### **상세 기능 명세**

  * **상품(Item)**:
    ![데카_명세_기능_상품](https://github.com/user-attachments/assets/48f60c74-92bd-479b-9796-3b4fe7c9524d)

  * **사용자(User)**:
    ![데카_명세_기능_사용자](https://github.com/user-attachments/assets/95fa239b-6202-4a24-b17d-dba1737b5276)

  * **주문(Order)**:
    ![데카_명세_기능_주문](https://github.com/user-attachments/assets/8aef3f04-ad81-40f9-a0a8-1561879d9c7d)

  * **장바구니(Cart)**:
    ![데카_명세_기능_장바구니](https://github.com/user-attachments/assets/df16230a-06a3-4088-8341-65b069e306d0)


</details>

<details>
<summary><b>비기능 요구사항 상세(Non-functional Requirements)</b> - 클릭하여 보기/접기</summary>


#### **상세 비기능 명세**

  * **데이터 관리**:
    ![데카_명세_비기능_데이터](https://github.com/user-attachments/assets/edbfdb08-16a8-4a71-b610-90062cc63560)

  * **시스템 환경**:
    ![데카_명세_비기능_시스템 환경](https://github.com/user-attachments/assets/a941decd-992d-405b-9b75-a1f07147056e)


</details>

----

## 🏛️ 프로젝트 구조

'자바 기초' 수업에서 배운 내용들을 최대한 활용하여 프로젝트의 구조를 설계했습니다.

### 패키지 구조

각 기능적 요구사항을 기반으로 역할을 분리하여 아래와 같이 5개의 주요 패키지와 데이터 폴더로 구성했습니다.

```
DecaDive/
├── src/
│   ├── app/
│   │   ├── DecaDiveConsoleApp.java
│   │   └── MyAppReader.java
│   ├── cart/
│   │   ├── CartDAO.java
│   │   ├── CartItemVO.java
│   │   ├── CartService.java
│   │   ├── CartServiceImpl.java
│   │   └── HashMapCartDAO.java
│   ├── item/
│   │   ├── ItemDAO.java
│   │   ├── ItemService.java
│   │   ├── ItemServiceImpl.java
│   │   ├── ItemVO.java
│   │   └── ObjFileHashMapItemDAO.java
│   ├── member/
│   │   ├── MemberDAO.java
│   │   ├── MemberService.java
│   │   ├── MemberServiceImpl.java
│   │   ├── MemberVO.java
│   │   └── ObjFileHashMapMemberDAO.java
│   └── order/
│       ├── ObjFileHashMapOrderDAO.java
│       ├── OrderDAO.java
│       ├── OrderItemVO.java
│       ├── OrderService.java
│       ├── OrderServiceImpl.java
│       └── OrderVO.java
└── data/
    ├── itemDB.obj
    ├── memberDB.obj
    └── orderDB.obj
```

### 클래스 다이어그램

각 패키지의 클래스들은 데이터 객체(VO), 데이터 접근 로직(DAO), 비즈니스 로직(Service)의 역할로 분리하여 설계했습니다.

  * **상품 (Item)**
    ![데카다이브_아이템_클래스](https://github.com/user-attachments/assets/f5f528bf-644f-4d4d-9da9-871661dbd07e)

  * **회원 (Member)**
    ![데카다이브_멤버_클래스](https://github.com/user-attachments/assets/d692b2b9-8796-47d9-ba3a-0a20a9178de1)

  * **주문 (Order)**
    ![데카다이브_오더_클래스](https://github.com/user-attachments/assets/690598d4-b645-44c6-8c27-25bd30845999)

  * **장바구니 (Cart)**
    ![데카다이브_카트_클래스](https://github.com/user-attachments/assets/1dda2e18-2e0f-4595-a8c5-1a23c834ca1a)


-----

## 📝 코드 리뷰를 통한 성장

프로젝트의 완성도를 높이고 객관적인 시각으로 코드를 개선하기 위해 동료 개발자들과 코드 리뷰를 진행했습니다. 혼자서는 발견하기 어려웠던 부분들을 동료들의 피드백을 통해 배우고 수정할 수 있었습니다.

  * **김재현님 코드리뷰**
    ![코드리뷰_김재현](https://github.com/user-attachments/assets/85819533-f491-426c-b51e-04d8bb27b834)

  * **박종인님 코드리뷰**
     ![코드리뷰_박종인](https://github.com/user-attachments/assets/eabb9724-27e6-4b57-b66a-8b39766c3513)


## 🎓 프로젝트를 마치며

DecaDive 프로젝트는 저에게 첫 '기획'과 '개발'의 전 과정을 경험하게 해준 소중한 첫걸음입니다. '자바 기초' 수업에서 배운 인터페이스, 컬렉션 프레임워크, 파일 입출력(객체 직렬화) 등의 개념이 실제 프로그램에서 어떻게 유기적으로 동작하는지 몸소 체감할 수 있었습니다.

특히 DAO 패턴을 적용하여 데이터 로직을 분리하는 과정은, 역할과 책임의 중요성을 깨닫게 해주었습니다. 물론 지금 다시 보면 아쉬운 점도 많고 부족한 코드지만, 이 프로젝트를 통해 얻은 경험과 동료들의 피드백은 앞으로 더 나은 개발자로 성장하는 데 튼튼한 밑거름이 될 것이라 생각합니다.
