package cn.xiaoyu.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author 01399268
 * @date 2020/9/3
 */
public class InitClient {
    public static RestHighLevelClient getClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("100.119.201.112", 9200, "http")));
        return client;
    }
}
