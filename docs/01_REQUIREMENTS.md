# 01. Project Requirements & Specs

## 1. 프로젝트 개요
- **프로젝트명:** eungda (응다)
- **목표:** 한국인의 식습관(매운맛, 밥, 밀가루 등)과 수분 섭취를 기록하고, 장 건강과의 상관관계를 추적/분석하는 헬스케어 시스템.

## 2. 도메인 스펙 (Data Model)

### 2.1. DietRecord (식단 기록)
- `id` (Long, PK): 기록 고유 번호
- `memberId` (Long): 회원 고유 번호
- `mealType` (String): 식사 종류 ("BREAKFAST", "LUNCH", "DINNER", "SNACK")
- `hasRice` (Boolean): 밥/탄수화물 포함 여부
- `hasFiber` (Boolean): 식이섬유(김치, 채소 등) 포함 여부
- `hasProtein` (Boolean): 단백질 포함 여부
- `hasSpicy` (Boolean): 매운 음식 포함 여부
- `hasFlour` (Boolean): 밀가루/인스턴트 포함 여부
- `hasWater` (Boolean): 충분한 수분 섭취 여부
- `recordedDate` (LocalDateTime): 기록 일시