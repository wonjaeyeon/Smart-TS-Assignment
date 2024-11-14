# SMART TS ASSIGNMENT

<img width="80" src="https://github.com/user-attachments/assets/0ecf41cc-5c0f-426a-852c-bcaf78bfffd9">

2024년 KIKII SmartTs Assignment과제 전형을 수행한 참여한 프로젝트입니다.

<p align="center">
<img src="https://github.com/user-attachments/assets/aa4a1d00-c0d3-4074-9c91-ae49210240bc" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/a9024a5d-30a0-4fd1-b4df-c9f5e319bea8" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/eb236295-74bf-4b35-9b63-17f2d638a71d" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/3268d7b4-2680-45ba-9570-06bff5172b04" width="16%" height="30%">
<img src="https://github.com/user-attachments/assets/8d6caab5-dd01-4291-bd9f-2b0e6678d85e" width="16%" height="30%">
</p>

<p align="center">
<img src="https://github.com/user-attachments/assets/03444cfa-5737-4167-82f5-f8a4f80f4c1b" width="40%" height="30%">
<img src="https://github.com/user-attachments/assets/826a2948-77a9-4d56-b61f-cfed87a88e5f" width="40%" height="30%">
</p>



# OverView

- 개발 기간 : 2024.11.11 ~ 2024.11.14


- Android : <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=Android&logoColor=white"> <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white"> <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=Jetpack%20Compose&logoColor=white">

# About

### Features
- 로그인/로그아웃
- 소속 노선 및 버스번호 조회
- 배차일보 조회

### Architecture Pattern

- Architecture Pattern: MVVM Pattern
<p align="center">
<img src="https://github.com/user-attachments/assets/dbb903ad-3534-44ee-9aed-7aa82f68c633" width="90%" height="40%">
</p>


## Technology Stack
- Tools: IntelliJ 2024.2.4
- Language: Kotlin (version 2.0.10)




### Libraries:

#### UI:
- Jetpack Compose (Compiler version 1.5.1, BOM version 2024.09.00)

#### Navigation:
- Navigation Component (version 2.8.0)

#### Suspend Computing
- Coroutine (version 1.8.1)

#### DI:
- Dagger-Hilt (version 2.52)

#### DB:
- Room Database (version 2.6.1)

#### Network:
- Retrofit (version 2.9.0)
- Kotlinx Serialization (version 1.7.3)

#### Testing:
- JUnit (version 4.13.2)
- JUnit Jupiter (version 5.8.1)
- AndroidX Test (version 1.6.1)

#### CI/CD:
- Github Actions(checkout@v4)

### Foldering
```
├── SmartTsAssignment.kt
├── data
│   ├── common
│   │   └── ResultData.kt
│   ├── datasource
│   │   ├── local
│   │   │   ├── SmartTsAssignmentLocalDataSource.kt
│   │   │   ├── db
│   │   │   │   ├── InstantConverter.kt
│   │   │   │   ├── SmartTsDatabase.kt
│   │   │   │   ├── auth
│   │   │   │   │   ├── AuthDao.kt
│   │   │   │   │   └── AuthEntity.kt
│   │   │   │   ├── di
│   │   │   │   │   └── SmartTsDatabaseModule.kt
│   │   │   │   ├── dispatch
│   │   │   │   │   ├── BusType.kt
│   │   │   │   │   ├── DispatchDao.kt
│   │   │   │   │   └── DispatchEntity.kt
│   │   │   │   └── route
│   │   │   │       ├── RouteDao.kt
│   │   │   │       ├── RouteEntity.kt
│   │   │   │       ├── RouteType.kt
│   │   │   │       └── ShiftType.kt
│   │   ├── remote
│   │   │   ├── SmartTsAssignmentRemoteDataSource.kt
│   │   │   ├── api
│   │   │   │   ├── SmartTsAPIService.kt
│   │   │   │   ├── auth
│   │   │   │   │   ├── AuthRequest.kt
│   │   │   │   │   └── AuthResponse.kt
│   │   │   │   ├── di
│   │   │   │   │   └── SmartTsAPIServiceModule.kt
│   │   │   │   ├── dispatch
│   │   │   │   │   ├── DispatchRequest.kt
│   │   │   │   │   └── DispatchResponse.kt
│   │   │   │   └── route
│   │   │   │       └── RouteResponse.kt
│   ├── mapper
│   │   ├── AuthMapper.kt
│   │   ├── DispatchMapper.kt
│   │   ├── RouteMapper.kt
│   │   └── di
│   │       └── MapperModule.kt
│   ├── model
│   │   ├── AuthModel.kt
│   │   ├── DispatchModel.kt
│   │   └── RouteModel.kt
│   └── repository
│       ├── composite_repo
│       │   ├── auth_dispatch
│       │   │   ├── AuthDispatchRepository.kt
│       │   │   └── CompositeAuthDispatchRepository.kt
│       │   └── auth_route
│       │       ├── AuthRouteRepository.kt
│       │       └── CompositeAuthRouteRepository.kt
│       ├── default_repo
│       │   ├── auth
│       │   │   ├── AuthRepository.kt
│       │   │   └── DefaultAuthRepository.kt
│       │   ├── dispatch
│       │   │   ├── DefaultDispatchRepository.kt
│       │   │   └── DispatchRepository.kt
│       │   └── route
│       │       │   ├── DefaultRouteRepository.kt
│       │       │   └── RouteRepository.kt
│       └── di
│           └── SmartTsRepositoryModule.kt
├── domain
│   ├── di
│   │   ├── AuthUseCaseModule.kt
│   │   ├── DateUseCaseModule.kt
│   │   ├── DispatchUseCaseModule.kt
│   │   ├── RouteUseCaseModule.kt
│   │   └── coroutine
│   │       ├── DispatcherAnnotation.kt
│   │       └── DispatcherModule.kt
│   └── usecases
│       ├── auth
│       │   ├── GetAuthUseCases.kt
│       │   ├── LoginAuthUseCases.kt
│       │   └── LogoutAuthUseCases.kt
│       ├── date
│       │   └── DateFormatUseCases.kt
│       ├── dispatch
│       │   ├── FetchDispatchUseCases.kt
│       │   └── GetDispatchUseCases.kt
│       └── route
│           ├── FetchRouteUseCases.kt
│           └── GetRouteUseCases.kt
├── ui
│   ├── MainActivity.kt
│   ├── SmartTsApp.kt
│   ├── components
│   │   ├── app_bar
│   │   ├── card
│   │   │   └── SmartTsDriverInfoCard.kt
│   │   ├── list
│   │   │   ├── ListItem.kt
│   │   │   └── SmartTsItemList.kt
│   │   └── textField
│   │       └── SmartTsOutlinedTextField.kt
│   ├── feature
│   │   ├── auth
│   │   │   ├── AuthRoute.kt
│   │   │   ├── AuthUiState.kt
│   │   │   ├── LoginScreen.kt
│   │   │   ├── LoginUiState.kt
│   │   │   ├── LoginViewModel.kt
│   │   │   └── components
│   │   ├── dispatch
│   │   │   ├── DispatchRoute.kt
│   │   │   ├── DispatchScreen.kt
│   │   │   ├── DispatchUiState.kt
│   │   │   ├── DispatchViewModel.kt
│   │   │   └── components
│   │   │       ├── CustomDatePickerDialog.kt
│   │   │       ├── DispatchItem.kt
│   │   │       └── DispatchList.kt
│   │   ├── empty
│   │   │   └── EmptyComingSoon.kt
│   │   ├── route
│   │   │   ├── RouteRoute.kt
│   │   │   ├── RouteScreen.kt
│   │   │   ├── RouteUiState.kt
│   │   │   ├── RouteViewModel.kt
│   │   │   └── components
│   │   └── settings
│   │       ├── MainSettingsScreen.kt
│   │       ├── SettingRoute.kt
│   │       ├── SettingUiState.kt
│   │       ├── SettingViewModel.kt
│   │       ├── components
│   │       └── sub
│   │           ├── NotificationSettingsScreen.kt
│   │           ├── PrivacySettingsScreen.kt
│   │           └── account
│   │               ├── AccountSettingsScreen.kt
│   │               └── components
│   │                   └── AccountCard.kt
│   ├── navigation
│   │   ├── Route.kt
│   │   ├── SmartTsNavHost.kt
│   │   ├── SmartTsNavigationActions.kt
│   │   ├── TopLevelDestination.kt
│   │   └── components
│   │       ├── ModalNavigationDrawerContent.kt
│   │       ├── PermanentNavigationDrawerContent.kt
│   │       ├── SmartTsBottomNavigationBar.kt
│   │       ├── SmartTsNavigationRail.kt
│   │       ├── SmartTsNavigationWrapper.kt
│   │       └── policy
│   │           └── NavigationMeasurePolicy.kt
│   ├── theme
│   │   ├── Color.kt
│   │   ├── Shapes.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── window
│       └── WindowState.kt
```

# 프로젝트를 통해 이룬 부분
- 애초에 기획한 모든 기능 구현 완성
- Android User가 대다수라는 걸 가만하면 DUAL, SINGLE 화면 모두 호환가능한 Reactive UI로 제작
- 안드로이드 공식문서에서 보여주는 local cache 구현 (Mutex lock만 따로 사용 안 함)
- MVVM 패턴으로 구현


# 프로젝트 이후 좀 아쉬웠던 부분
- NowinAndroid의 Sync Worker도 사용하면 좀 더 좋았을 것 같다. 추후에 기회되면 더 업그레이드 할  예정
- DB Migration Senario에 대한 구현은 하지 않음. 추후 유저들 이용 패턴 파악해보고 구현 예정
