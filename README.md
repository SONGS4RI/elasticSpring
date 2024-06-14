# elasticSpring
코프링과 엘라스틱서치를 활용한 데이터 작업 프로젝트 입니다.

# 사용 방법
1. elasticsearch와 kibana 컨테이너 생성
    ```BASH
    docker-compose up -d
    ```
2. 키바나를 사용해서 예제 인덱스 스냅샷 복구 -> http://localhost:5601 에서 메뉴바 Management/Dev Tools 에서 콘솔 사용
    ```BASH
    # elasticsearch 노드에 스냅샷 등록
    PUT _snapshot/search_example
    {
      "type": "fs",
      "settings": {
        "location": "/es/book_backup/search_example",
        "compress": true
      }
    }
    # 스냅샷 등록 확인
    GET _snapshot/search_example/_all

    # 스냅샷 복구
    POST _snapshot/search_example/movie-search/_restore
    ```
3. Postman으로 문서에 대한 작업을 수행하고 kibana에서 확인하면 됩니다.