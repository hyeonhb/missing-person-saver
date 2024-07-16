# Missing-Person-Saver BackEnd

## 🚀 Backend Base Setup
프로젝트 기본 설정은 Java 17, Spring Boot 3.x, 그리고 H2 데이터베이스를 사용합니다.

## 📋 Prerequisites
- **Java 17**: Java 17이 설치되어 있는지 확인하세요.
- **Spring Boot 3.x**: 이 프로젝트는 Spring Boot 3.0 이상을 사용하여 빌드되었습니다.
- **Maven**: 의존성 관리 및 프로젝트 빌드를 위해 사용됩니다.

## 🛠️ Getting Started
1. Clone 된 프로젝트의 `MpsBackEndApplication`을 실행합니다.
2. 콘솔에 'start server'가 출력되면 실행이 완료된 것입니다!
3. `application.properties` 파일로 이동하여 `spring.datasource.url=jdbc:h2:file:` 뒷부분에 실제 프로젝트를 다운로드받은 경로를 설정해주세요.
4. 웹 브라우저에서 `http://localhost:8080/h2-console`로 이동하여 H2 콘솔 사이트가 나타나는지 확인합니다.
5. H2 콘솔에서 JDBC URL에 위에서 설정한 경로를 복사하여 붙여넣은 후 `Connect` 버튼을 클릭합니다.
