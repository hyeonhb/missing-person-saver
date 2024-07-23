package com.example.serviceImpl;

import com.example.config.OpenApiConfig;
import com.example.entity.MissingPerson;
import com.example.entity.Users;
import com.example.repository.MissingPersonRepository;
import com.example.service.MissingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Optional;

@Service
public class MissingPersonServiceImpl implements MissingPersonService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OpenApiConfig openApiConfig;

    @Autowired
    private MissingPersonRepository missingPersonRepository;

    @Override
    public String getMissingPersonList(Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.safe182.go.kr/api/lcm/amberList.do")
//                .queryParam("esntlId", openApiConfig.getApiId())
//                .queryParam("authKey", openApiConfig.getApiKey())
                .queryParam("rowSize", "10")
                .queryParam("page", "1");

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        String url = builder.toUriString();
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public String getMissingPersonInfo(Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://www.safe182.go.kr/api/lcm/findChildListT.do")
                /*.queryParam("esntlId", openApiConfig.getApiId())
                .queryParam("authKey", openApiConfig.getApiKey())
                .queryParam("rowSize", "10")
                .queryParam("returnURL", openApiConfig.getReturnUrl())*/;

        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }

        String url = builder.toUriString();
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 실종자 id 로 정보 조회
     * @param id
     * @return 추후 api로 호출해 사용할 예정으로 정보 값이 무조건 있다는 것을 전제로 함
     */
    @Override
    public MissingPerson getMissingPerson(Long id) {
        Optional<MissingPerson> missingPersoneOptional = missingPersonRepository.findById(id);
        return missingPersoneOptional.get();
    }
}