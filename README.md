# EasyNaraMarket 프로젝트
[https://enm.jinsol.my/list](배포 주소)
## 프로젝트 개요
국가 입찰공고 시스템인 ‘나라장터’는 내부 자바스크립트와 POST요청으로 데이터 를 조작합니다. 
따라서 일반 사용자들이 입찰공고 관련 데이터를 공유/수집하는 데에는 어려움이 따릅니다.
EasyNaraMarket은 이를 용이하도록 돕는 서비스입니다.
## 기술스택
`Java` `SpringBoot` `JavaScript` `Thymeleaf` `Docker` `GithubAction` `AWS EC2`
## 서비스 설계
입찰공고 목록조회

![image](https://github.com/user-attachments/assets/41afd3b4-d27c-4491-8ef1-405a9ec8bd00)

입찰공고 상세조회

![image](https://github.com/user-attachments/assets/70f67829-7c97-4eff-adbd-93579628333f)

## 구현기능
 - [x] api를 통해 공유/수집 가능한 입찰공고 리스트 데이터 얻기
 - [x] api를 통해 공유/수집 가능한 입찰공고 상세 데이터 얻기
 - [x] '나라장터'서비스의 공고 상세 페이지에 직접 접근 가능한 URL
 - [ ] 공고에 첨부된 첨부파일을 api를 통해 수집하기
