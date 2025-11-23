# 🌦️ Weather Consume API

> **날씨에 따른 지하철 이용량 변화 분석 및 예측을 위한 백엔드 프로젝트**

기온, 강수량, 습도 등 기상 데이터를 기반으로
서울 지하철 승하차 인원(도시 교통 수요) 변화를 분석하고,
머신러닝 모델을 통해 승객 수를 예측하는 서비스입니다.

현재 프로젝트는 **Spring Boot** 기반 **REST API** 서버이며,
Python(FastAPI) 모델 서버와의 연동을 통해 분석 및 예측 기능을 확장할 예정입니다.

---

## 📘 프로젝트 소개

- **프로젝트명**: Weather Consume API
- **개발 목적**: 날씨 데이터와 지하철 이용량 데이터를 연동하여 구현
- **주요 역할**
    - 기상자료개방포털 CSV 기반 기상 데이터 수집
    - 서울 열린데이터 지하철 승하차 데이터 수집 및 전처리
    - Python 모델 서버(FastAPI)와 연동하여 승객 수 예측값 산출
    - 요청/응답 로그 DB 저장 및 관리
- **특징**
    - 확장 가능한 MSA 구조 (Spring ↔ FastAPI)

---

## 🧩 프로젝트 구성

```
weather-consume-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/hyorim/weather_consume_api/
│   │   │       ├── WeatherConsumeApiApplication.java    # 실행 진입점
│   │   │       │
│   │   │       ├── controller/                          # 요청을 받는 REST API 엔드포인트
│   │   │       │   ├── TestController.java              # 단순 서버 확인용
│   │   │       │   ├── LogController.java               # 로그 CRUD 관리용
│   │   │       │   └── WeatherController.java           # 예측 API
│   │   │       │
│   │   │       ├── service/                             # 비즈니스 로직 계층
│   │   │       │   ├── TestService.java                 # 단순 응답용
│   │   │       │   ├── LogService.java                  # 로그 저장 및 조회 처리
│   │   │       │   └── WeatherService.java              # 예측 처리(임시 로직 포함)
│   │   │       │
│   │   │       ├── repository/                          # DB 접근 계층 (JPA)
│   │   │       │   └── AnalysisLogRepository.java       # 로그 테이블 접근
│   │   │       │
│   │   │       ├── domain/                              # Entity 정의 (DB 테이블과 매핑)
│   │   │       │   └── AnalysisLog.java                 # 로그 테이블 구조 정의
│   │   │       │
│   │   │       └── dto/                                 # 요청/응답 데이터 구조 정의
│   │   │           ├── WeatherRequest.java              # /predict 요청용 DTO
│   │   │           └── WeatherResponse.java             # /predict 응답용 DTO
│   │   │
│   │   └── resources/
│   │       ├── application.yml                          
│   │       ├── static/                                  
│   │       └── templates/                               
│   │
│   └── test/                                            
│
├── fastapi-model/                                       #  Python 기반 예측 모델 서버
│   ├── main.py                                          # FastAPI 실행 진입점 (/predict)
│   ├── model.pkl                                        # 학습된 머신러닝 모델
│   ├── train_model.py                                   # 모델 학습 스크립트
│   └── requirements.txt                                 # Python 의존성 
│
└──gitignore / .gitattributes / gradlew 등

```

## 🏗️ 아키텍처
```
┌─────────────────────────────┐
│        Spring Boot          │
│  - /api/predict             │
│  - WeatherService           │
│  - JPA Repository           │
└────────────┬────────────────┘
│ (HTTP POST)
▼
┌─────────────────────────────┐
│         FastAPI (Python)    │
│  - /predict                 │
│  - model.pkl (ML Model)     │
│  - pandas / scikit-learn    │
└────────────┬────────────────┘
│ (예측 결과 반환)
▼
┌─────────────────────────────┐
│             DB              │
│  - AnalysisLog 테이블        │
│  - 예측 요청/응답 로그 저장 │
└─────────────────────────────┘
```

## ✅ 기능 구현 체크리스트
### ⚙️ A. 서버 기본 구성
-[x] Spring Boot 프로젝트 세팅 (Gradle, Java 21, JPA, Lombok 등)
-[x] Sapplication.yml 환경 설정 (H2/MySQL DB, JPA 설정)
-[x] S패키지 구조 설계 (controller, service, domain, repository, dto)
-[x] /api/test 서버 상태 확인용 기본 API 구현

### 💾 B. 로그 관리 기능
-[x] AnalysisLog 엔티티 생성 (요청 시각, 입력값, 예측 결과)
-[x] AnalysisLogRepository 생성 (JPA 기반 CRUD)
-[x] LogService 구현 (로그 저장 및 조회 비즈니스 로직)
-[x] LogController 구현 (/api/logs POST, GET 엔드포인트)
-[x] H2 콘솔 활성화 및 DB 확인 (/h2-console)

### ☁️ C. 날씨 기반 예측 API
-[X] WeatherRequest, WeatherResponse DTO 생성
-[X] WeatherController 구현 (/api/predict 요청 처리)
-[X] WeatherService 구현 (랜덤 계산으로 예측값 생성)
-[X] 예측 요청 시 로그 자동 저장 (DB 연동 확인)
-[X] Postman/curl로 /api/predict 요청 테스트

### 🛜 D. FastAPI 연동
-[X] FastAPI 서버 구축 (/predict POST 엔드포인트)
-[X] Spring → FastAPI 간 HTTP 통신 구현 (WebClient 사용)
-[X] 예측 결과 수신 후 DB 로그 저장

### 📊 E. 데이터 모델링
-[X] 기상 데이터 수집 
-[X] 소비 데이터 수집 (지하철 역 승하차 정보)
-[X] 데이터 전처리 및 병합 (날짜 기준 Join)
-[X] train_model.py 작성 (모델 학습 및 model.pkl 생성)
-[X] FastAPI에서 model.pkl 로드하여 실제 예측 수행

----
## 🛠️ 테스트 수행
### Domain Test
-[ ]  AnalysisLog Builder 생성 테스트
-[ ]  null 값 처리 여부(필요 시)
-[ ]  상태 값 변경(규칙 생기면 추가)

### Service Test
-[ ] LogService Test
  - saveLog 호출 시 repository.save() 호출 여부
  - prediction 값 정상 매핑
  - timestamp null 아님 테스트
-[ ] WeatherService Test
 - FastAPI mock 응답 처리
- prediction 값과 message 포맷 확인
- logService.saveLog() 호출 여부

### Controller Test
-[ ] LogController Test
  - /api/logs 정상 200 반환
  - Repository 결과 JSON 매핑 확인
-[ ] WeatherController Test
  -  정상 요청 200
  - JSON 구조 검증 (prediction, message)