package org.example.springboottoursystem.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.example.springboottoursystem.domain.DiaryEs;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.example.springboottoursystem.service.DiarySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiarySearchServiceImpl implements DiarySearchService {
    @Resource
    private final RestHighLevelClient client;
    @Resource
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public DiarySearchServiceImpl(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public void ensureIndexExists() throws IOException {
        GetIndexRequest indexRequest = new GetIndexRequest("diary");
        boolean exists = client.indices().exists(indexRequest, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("diary");
            try {
                client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
                System.out.println("Index created successfully.");
            } catch (Exception e) {
                System.err.println("Failed to create index: " + e.getMessage());
                throw e;
            }
        }
    }

    @Override
    public void syncDiaryToElasticsearch(DiaryEs diaryEs) throws IOException {
        IndexRequest request = new IndexRequest("diary")
                .id(String.valueOf(diaryEs.getId()))
                .source(objectMapper.writeValueAsString(diaryEs), XContentType.JSON);

        try {
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            System.out.println("IndexResponse: " + response.toString());
        } catch (Exception e) {
            System.err.println("Error syncing diary to Elasticsearch: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<DiaryEs> searchDiaries(String keyword) throws IOException {
        SearchRequest searchRequest = new SearchRequest("diary");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(keyword, "title", "spot", "text"));
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        List<DiaryEs> result = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            DiaryEs diaryEs = objectMapper.readValue(hit.getSourceAsString(), DiaryEs.class);
            result.add(diaryEs);
        }
        return result;
    }
}