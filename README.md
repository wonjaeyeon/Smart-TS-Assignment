


1. Android User가 대다수라는 걸 가만하면 DUAL, SINGLE 화면 모두 호환가능한 Reactive UI로 제작

2. encrypt room db로 데이터 보호

3. MVVM 패턴으로 구현

4. Android Jetpack 사용


# SMART TS ASSIGNMENT

<img width="80" src="https://github.com/user-attachments/assets/0ecf41cc-5c0f-426a-852c-bcaf78bfffd9">

2024년 KIKII SmartTs Assignment과제 전형을 수행한 참여한 프로젝트입니다.

<p align="center">
<img src="https://github.com/user-attachments/assets/28068e34-a0d6-47fb-a16f-6bc74d5c7d21" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/95ed5b3e-40ef-4310-b5b4-cffde848321f" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/d48cfc92-c9db-4224-b7a2-e2196e05cef0" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/d02394c1-ec24-47a0-9e2c-d020700e1570" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/ae6cb789-5cc4-407d-aae5-f6c794b81a07" width="16%" height="30%">
</p>


# OverView
> 하루에 600대 이상의 태블릿을 공급하는 회사 태클라스트 코리아의 더 빠르고 효율적인 QC를 위한 어플리케이션
>
> CPU, GPU, ... 모든 물리 QC가 가능한 앱
>
> 작업자들을 위한 자동화 QC 프로세스 마련(실제 작업량을 총평균 30% 감소)
>
> PULL Architecture를 이용해 커스텀 QC를 진행 가능(서버에서 QC 결과를 DB에 저장하고 AI를 통한 분석으로 최적의 QC 검사 순서/기준을 QC APP에 제공)

- 개발 기간 : 2024.11.11 ~ 2024.11.14
- Android : <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=Android&logoColor=white"> <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white"> <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=Jetpack%20Compose&logoColor=white">

# About

### Features
- 로그인/로그아웃
- 소속 노선 및 버스번호 조회
- 배차일보 조회

### Technology Stack
- Tools : Intellij
- Language : Kotlin
- Architecture Pattern : MVVM Pattern
- Android Architecture Components(AAC)
    - ViewModel
    - Coroutine
- Naivgation Component
- RETROFIT
- SERIALIZATION

### Foldering
```
.

├── data
│   ├── qcResults
│     ├── QCResults
│     └── QCResultsDAO
└── ui (for features)
```

# 프로젝트를 통해 이룬 부분
- 애초에 기획한 모든 기능 구현 완성
- RoomDB를 이용한 QC RESULTS의 CRUD를 깔끔하게 구현


# 프로젝트 이후 좀 아쉬웠던 부분
- Android Hilt, Dagger를 사용하지 못함(잘 몰랐음) -> 추후에 20204.7월에 완벽한 MVVM의 추가적인 공부 진행
