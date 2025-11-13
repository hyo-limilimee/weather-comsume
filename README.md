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
└── .gitignore / .gitattributes / gradlew 등

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