# 🌦️ Weather Consume API

> **날씨에 따른 소비 변화 분석 및 예측을 위한 백엔드 프로젝트**

기온, 강수량, 습도 등 기상 데이터를 기반으로 카드 소비지수(또는 매출액 등) 변화를 분석하고,  
머신러닝 모델을 통해 소비 패턴을 예측하는 서비스입니다.

현재 프로젝트는 **Spring Boot 기반 REST API 서버**이며,  
Python(FastAPI) 모델 서버와의 연동을 통해 분석 및 예측 기능을 확장할 예정입니다.

---

## 📘 프로젝트 소개

- **프로젝트명**: Weather Consume API
- **개발 목적**: 날씨 데이터와 소비 데이터를 연동하여 빅데이터분석기사 실기 실습용 API 구현
- **주요 역할**
    - 공공데이터포털 API 또는 CSV를 통한 날씨 데이터 수집
    - Python 모델 서버(FastAPI)와 연동하여 소비 예측값 산출
    - 요청/응답 로그 DB 저장 및 관리
- **특징**
    - 프론트엔드 없이 백엔드 중심 설계
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
-[ ] 예측 결과 수신 후 DB 로그 저장
-[ ] 통신 및 에러 처리 테스트

### 📊 E. 데이터 모델링
-[ ] 기상 데이터 수집 (기상자료개방포털, 공공데이터포털)
-[ ] 소비 데이터 수집 (BC카드, 서울열린데이터광장)
-[ ] 데이터 전처리 및 병합 (날짜 기준 Join)
-[ ] train_model.py 작성 (모델 학습 및 model.pkl 생성)
-[ ] FastAPI에서 model.pkl 로드하여 실제 예측 수행
