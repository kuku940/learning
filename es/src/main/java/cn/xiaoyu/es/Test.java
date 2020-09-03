package cn.xiaoyu.es;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author 01399268
 * @date 2020/9/3
 */
public class Test {
    public static void main(String[] args) {
        try (RestHighLevelClient client = InitClient.getClient()) {
            // 1.创建索引名
            CreateIndexRequest request = new CreateIndexRequest("book8");

            // 2.索引setting配置
            request.settings(Settings.builder().put("index.number_of_shards", 5)
                    .put("index.number_of_replicas", 2) // 副本数
                    .put("analysis.analyzer.default.tokenizer", "standard")
            );

            // 3.设置索引的mapping
            request.mapping("_doc",
                    "  {\n" +
                            "    \"_doc\": {\n" +
                            "      \"properties\": {\n" +
                            "        \"message\": {\n" +
                            "          \"type\": \"text\"\n" +
                            "        }\n" +
                            "      }\n" +
                            "    }\n" +
                            "  }",
                    XContentType.JSON);

            // 设置索引别名
            request.alias(new Alias("lab1"));

            // 5.发送请求
            // 5.1同步方式
            CreateIndexResponse response = client.indices().create(request, new RequestOptions());

            // 处理响应
            boolean acknowledged = response.isAcknowledged();
            boolean shardsAcknowledged = response.isShardsAcknowledged();

            System.out.println("请求结果---------------");
            System.out.println("acknowledged:" + acknowledged);
            System.out.println("shardsAcknowledged:" + shardsAcknowledged);

            // 5.2 异步方式发送请求
            ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
                @Override
                public void onResponse(CreateIndexResponse createIndexResponse) {
                    boolean acknowledged = createIndexResponse.isAcknowledged();
                    boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
                    System.out.println("请求结果---------------");
                    System.out.println("acknowledged:" + acknowledged);
                    System.out.println("shardsAcknowledged:" + shardsAcknowledged);
                }

                @Override
                public void onFailure(Throwable e) {
                    e.printStackTrace();
                }
            };

            client.indices().createAsync(request, listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}
