package com.gxyan.search;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest
public class SearchApplicationTests {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 测试保存数据
     */
    @Test
    public void indexData() throws IOException {
        IndexRequest request = new IndexRequest("users");
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        IndexRequest source = request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(source, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Data
    class User {
        private String name;
        private int age;
    }

}
