## 1. 회원 가입

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 유저 정보 입력 및 전송
DjangoRestServer->>DB : 유저정보 저장
DB-->>DjangoRestServer : 결과 코드
DjangoRestServer-->>FrontPage : 결과 코드 
```







## 2. 로그인

사용자로부터 입력된 ID, PW를 DB에 저장된 사용자 레코드와 비교한 후 로그인 진행

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : ID / PW 입력 및 전송
DjangoRestServer->>DB : 유저 정보 확인
DB-->>DjangoRestServer : 확인 결과 반환
DjangoRestServer-->>FrontPage : 로그인 결과, 토큰 발행(로그인 성공시) 

```



## 3. 마이페이지

유저정보 조회는, 클라이언트에서 서버로 토큰 전송 후, 서버에서 토큰 유효성 검사 및 토큰에서 유저번호를 추출하여 DB에서 적절한 결과를 조회한다.

#### 유효한 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사
DjangoRestServer->>DB : 유저정보 조회
DB-->>DjangoRestServer : 결과 반환
DjangoRestServer-->>FrontPage : 결과 반환
```

#### 유효하지 않은 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(실패)
DjangoRestServer-->>FrontPage : 실패코드
```



## 4. 개인 정보 수정

#### 유효한 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 사용자 입력 + 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사
DjangoRestServer->>DB : 유저정보수정
DB-->>DjangoRestServer : 수정 결과 반환
DjangoRestServer-->>FrontPage : 수정 결과 반환
```

#### 유효하지 않은 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 사용자 입력 + 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(실패)
DjangoRestServer-->>FrontPage : 실패 코드
```



## 5. 음식 검색

검색어와 음식명, 제조사, 맛표현, 영양성분 등의 조건을 이용해 검색

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 검색어 + 검색조건 입력 및 전송
DjangoRestServer->>DB : DB 조회
DB-->>DjangoRestServer : 조회 결과 반환
DjangoRestServer-->>FrontPage : 조회 결과 반환
```



## 6. 음식 비교리스트 : 추가

토큰 + 음식정보를 이용해 사용자의 비교리스트에 해당 음식 추가

#### 유효한 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 음식번호 + 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(성공)
DjangoRestServer->>DB : DB에 (유저, 리스트에 담은 음식번호) 정보 추가
DB-->>DjangoRestServer : 삽입 결과 반환
DjangoRestServer-->>FrontPage : 삽입 결과 반환
```



#### 유효하지 않은 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 음식번호 + 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(실패)
DjangoRestServer-->>FrontPage : 실패 코드
```



## 7. 음식 비교리스트 : 조회

서버로부터 

1. 사용자가 비교리스트에 넣은 음식정보
2. 권장섭취량 - 현재섭취량 정보

획득 후 음식간 영양성분 비교

#### 유효한 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(성공)
DjangoRestServer->>DB : <DB 조회><br/>유저 - 비교리스트 내 음식들(영양성분 포함)<br/>사용자 일일 권장섭취량<br/>현재섭취량
DB-->>DjangoRestServer : 조회 결과 반환
DjangoRestServer-->>FrontPage : 조회 결과
FrontPage->>FrontPage : 조회 결과를 차트로 시각화
```

#### 유효하지 않은 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(실패)
DjangoRestServer-->>FrontPage : 실패 코드
```



## 8. 음식 비교리스트 : 삭제

#### 유효한 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 음식번호 + 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(성공)
DjangoRestServer->>DB : 전달된 (유저, 음식번호)와 매칭되는 행 삭제  
DB-->>DjangoRestServer : 삭제 결과 반환
DjangoRestServer-->>FrontPage : 삭제 결과
```

#### 유효하지 않은 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(실패)
DjangoRestServer-->>FrontPage : 실패 코드
```



## 9. 음식 상세 정보

### 	9 - 1. 영양정보 탭

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 음식번호 전송
DjangoRestServer->>DB : 영양정보 정보 조회
DB-->>DjangoRestServer : 조회 결과 반환
DjangoRestServer-->>FrontPage : 조회 결과
```

    ### 	9 - 2. 레시피 탭

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 음식번호 전송
DjangoRestServer->>DB : 레시피 정보 조회
DB-->>DjangoRestServer : 조회 결과 반환
DjangoRestServer-->>FrontPage : 조회 결과
```



​	

### 	9 - 3. 주변 음식점 탭

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 음식번호 전송
DjangoRestServer->>DB : 음식 판매 가게 
DB-->>DjangoRestServer : 조회 결과 반환
DjangoRestServer-->>FrontPage : 조회 결과
```



## 10. 식단 캘린더

#### 유효한 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(성공)
DjangoRestServer->>DB : 사용자 식단 기록 조회 
DB-->>DjangoRestServer : 조회 결과 반환
DjangoRestServer-->>FrontPage : 조회 결과
```

#### 유효하지 않은 요청

```mermaid
sequenceDiagram

FrontPage->>DjangoRestServer : 토큰 전송
DjangoRestServer->>DjangoRestServer : 토큰 유효성 검사(성공)
DjangoRestServer-->>FrontPage : 실패 코드
```



