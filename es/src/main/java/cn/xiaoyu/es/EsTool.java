package cn.xiaoyu.es;

/**
 * @author 01399268
 * @date 2020/9/3
 */
public class EsTool {
//    /**
//     * 查看ES路径:elasticsearch-7.5.1\config\elasticsearch.yml cluster.name
//     */
//    private static final String CLUSTER_NAME = "my-application";
//
//    private static EsTool esTool;
//
//    private static TransportClient transportClient;
//
//    public static EsTool getInstance() {
//        if (null == esTool) {
//            esTool = new EsTool();
//        }
//        return esTool;
//    }
//
//    /**
//     * 减少频繁获取连接，定义一个变量存放连接
//     */
//    public TransportClient getClient() {
//        if (null == transportClient) {
//            transportClient = getNewClient();
//        }
//        return transportClient;
//    }
//
//    public static void main(String[] args) throws IOException {
//        String operate = "search";
//        if ("addIndicesMapping".equals(operate)) {
//            // 添加索引
//            String jsonString = "{\"properties\":{\"author\":{\"type\":\"keyword\"},\"title\":{\"type\":\"text\"},\"content\":{\"type\":\"text\"},\"price\":{\"type\":\"integer\"},\"date\":{\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}}}";
//            // 执行成功后显示:----------Add mapping success----------true
//            getInstance().createIndexAndMapping("indices_test", jsonString);
//        } else if ("addDocument".equals(operate)) {
//            // Add id success,version is :1
//            getInstance().addIndexDocument("indices_test", "_doc");
//        } else if ("addOrUpdateDocument".equals(operate)) {
//            // bulk success
//            getInstance().bulkIndexDocument("indices_test", "_doc");
//        } else if ("deleteById".equals(operate)) {
//            getInstance().deleteById("indices_test", "_doc", "id_003");
//        } else if ("batchDeleteByIds".equals(operate)) {
//            List<String> ids = new ArrayList<String>();
//            ids.add("id_001");
//            ids.add("id_002");
//            getInstance().batchDeleteByIds("indices_test", "_doc", ids);
//        } else if ("updateDocument".equals(operate)) {
//            // result is OK   == id指_id
//            getInstance().updateDocument("indices_test", "_doc", "TNZDUHEB_rQdj7R3LnO4", null);
//        } else if ("updateDocumentPrepare".equals(operate)) {
//            // result is UPDATED   == id指_id
//            getInstance().updateDocumentPrepare("indices_test", "_doc", "TNZDUHEB_rQdj7R3LnO4", null);
//        } else if ("searchByIndex".equals(operate)) {
//            // id指_id
//            getInstance().searchByIndex("indices_test", "_doc", "TNZDUHEB_rQdj7R3LnO4");
//        } else if ("queryAll".equals(operate)) {
//            // ..."totalHits":{"value":3,"relation":"EQUAL_TO"},"maxScore":1.0}
//            getInstance().queryAll("indices_test");
//        } else if ("search".equals(operate)) {
//            // 查询全部
//            QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
//            getInstance().searchQuery("indices_test", queryBuilder);
//            //以下内容仅仅为插叙条件格式
//            // Span First
//            /*QueryBuilder queryBuilder = QueryBuilders.spanFirstQuery(
//                    QueryBuilders.spanTermQuery("title", "title"), 1);*/
//
//            /*QueryBuilder queryBuilder =QueryBuilders.spanNearQuery(QueryBuilders.spanTermQuery("title", "title"),1000)
//            .addClause(QueryBuilders.spanTermQuery("title", "title_001"))
//            .addClause(QueryBuilders.spanTermQuery("title", "title_002"))
//            .addClause(QueryBuilders.spanTermQuery("title", "title_003"));*/
//            // ...
//        }
//    }
//
//    /**
//     * 根据不同的条件查询
//     *
//     * @throws Exception
//     */
//    public void searchQuery(String index, QueryBuilder queryBuilder) {
//        SearchResponse response = getClient().prepareSearch(index).setQuery(queryBuilder).get();
//        for (SearchHit searchHit : response.getHits()) {
//            System.out.println(searchHit);
//        }
//    }
//
//    /**
//     * 根据索引、类型、id获取记录
//     *
//     * @param index
//     * @param type
//     * @param id
//     */
//    public void searchByIndex(String index, String type, String id) {
//        GetResponse response = getClient().prepareGet(index, type, id).execute().actionGet();
//        String json = response.getSourceAsString();
//        if (null != json) {
//            System.out.println(json);
//        } else {
//            System.out.println("no result");
//        }
//    }
//
//    /**
//     * 修改内容
//     *
//     * @throws Exception
//     */
//    public boolean updateDocumentPrepare(String index, String type, String id, XContentBuilder source) {
//        XContentBuilder endObject;
//        try {
//            // 修改后的内容
//            endObject = XContentFactory.jsonBuilder().startObject().field("author", "test_prepare_001").endObject();
//            UpdateResponse response = getClient().prepareUpdate(index, type, id).setDoc(endObject).get();
//            System.out.println("result is " + response.getResult().name());
//            return "UPDATED".equals(response.getResult().name());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 修改内容
//     */
//    public boolean updateDocument(String index, String type, String id, XContentBuilder source) {
//        Date time = new Date();
//        // 创建修改请求
//        UpdateRequest updateRequest = new UpdateRequest();
//        updateRequest.index(index);
//        updateRequest.type(type);
//        updateRequest.id(id);
//        try {
//            // 根据实际需要调整方法参数source里的值
//            updateRequest.doc(XContentFactory.jsonBuilder().startObject().field("author", "author001").field("title", "title001")
//                    .field("content", "content001")
//                    .field("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time)).endObject());
//            UpdateResponse response = getClient().update(updateRequest).get();
//            System.out.println("result is " + response.status().name());
//            return "OK".equals(response.status().name());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 根据id批量删除
//     */
//    public boolean batchDeleteByIds(String index, String type, List<String> ids) {
//        if (null == ids || ids.isEmpty()) {
//            System.out.println("ids is require");
//            return true;
//        }
//        BulkRequestBuilder builder = getClient().prepareBulk();
//        for (String id : ids) {
//            builder.add(getClient().prepareDelete(index, type, id).request());
//        }
//        BulkResponse bulkResponse = builder.get();
//        System.out.println(bulkResponse.status());
//        if (bulkResponse.hasFailures()) {
//            System.out.println("has failed, " + bulkResponse.status().name());
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 根据索引名称、类型和id删除记录
//     *
//     * @param indexName
//     * @param type
//     * @param id
//     */
//    public void deleteById(String indexName, String type, String id) {
//        DeleteResponse dResponse = getClient().prepareDelete(indexName, type, id).execute().actionGet();
//        if ("OK".equals(dResponse.status().name())) {
//            System.out.println("delete id success");
//        } else {
//            System.out.println("delete id failed : " + dResponse.getResult().toString());
//        }
//    }
//
//    /**
//     * 删除某个索引下所有数据
//     * 删除不存在的索引时,记录实际情况,默认返回成功
//     */
//    public boolean deleteAllIndex(String indexName) {
//        if (null == indexName || "".equals(indexName.trim())) {
//            System.out.println("Error: index name is require.");
//            return false;
//        }
//        //如果传人的indexName不存在会出现异常.可以先判断索引是否存在：
//        IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indexName);
//        IndicesExistsResponse inExistsResponse = getClient().admin().indices()
//                .exists(inExistsRequest).actionGet();
//        if (inExistsResponse.isExists()) {
//            AcknowledgedResponse response = getClient().admin().indices().prepareDelete(indexName)
//                    .execute().actionGet();
//            System.out.println("delete index date, result is " + response.isAcknowledged());
//            return response.isAcknowledged();
//        } else {
//            System.out.println("index is not existed");
//        }
//        return true;
//    }
//
//    /**
//     * 查询索引下的全部数据
//     *
//     * @param index
//     * @param type
//     */
//    public void queryAll(String index) {
//        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
//        SearchResponse response = getClient().prepareSearch(index).setQuery(queryBuilder).get();
//
//        SearchHits resultHits = response.getHits();
//        System.out.println(JSONObject.toJSON(resultHits));
//    }
//
//    /**
//     * 添加或者修改ES里的数据
//     */
//    public void bulkIndexDocument(String index, String type) {
//        BulkRequestBuilder bulkRequest = getClient().prepareBulk();
//        Date time = new Date();
//        try {
//            bulkRequest.add(getClient().prepareIndex(index, type, "id_002")
//                    .setSource(XContentFactory.jsonBuilder()
//                            .startObject()
//                            .field("id", "id_002")
//                            .field("author", "author_002")
//                            .field("title", "titile_002")
//                            .field("content", "content_002")
//                            .field("price", "20")
//                            .field("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time))
//                            .endObject()
//                    )
//            );
//
//            bulkRequest.add(getClient().prepareIndex(index, type, "id_003")
//                    .setSource(XContentFactory.jsonBuilder()
//                            .startObject()
//                            .field("id", "id_003")
//                            .field("author", "author_003")
//                            .field("title", "title_003")
//                            .field("content", "content_003")
//                            .field("price", "30")
//                            .field("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time))
//                            .endObject()
//                    )
//            );
//
//            BulkResponse bulkResponse = bulkRequest.get();
//            if (bulkResponse.hasFailures()) {
//                // process failures by iterating through each bulk response item
//                System.out.println("bulk has failed and token " + bulkResponse.getTook());
//            } else {
//                System.out.println("bulk success");
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 根据索引添加数据
//     *
//     * @param index
//     * @param type
//     */
//    public void addIndexDocument(String index, String type) {
//        Date time = new Date();
//        IndexResponse response = null;
//        try {
//            response = getInstance().getClient().prepareIndex(index, type)
//                    .setSource(XContentFactory.jsonBuilder()
//                            // 以下内容可以封装成一个对象,然后重新解析成如下格式(方法多加一个参数，建议使用反射方式改成通用方法)
//                            .startObject()
//                            .field("id", "id_001")
//                            .field("author", "author_001")
//                            .field("title", "title_001")
//                            .field("content", "content_001")
//                            .field("price", "10")
//                            .field("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time))
//                            .endObject())
//                    .get();
//            System.out.println("Add id success,version is :" + response.getVersion());
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 创建索引和mapping
//     *
//     * @param indiceName
//     * @throws Exception
//     */
//    public boolean createIndexAndMapping(String indiceName, String json) {
//        if (null == indiceName || "".equals(indiceName.trim())) {
//            System.out.println("indice is required");
//            return false;
//        }
//        String content = "content";
//        CreateIndexRequestBuilder cib = getClient().admin().indices().prepareCreate(indiceName);
//        XContentBuilder builderMapping = generateMappingBuilder(json);
//        cib.addMapping(content, builderMapping);
//        CreateIndexResponse res = cib.execute().actionGet();
//        if (res.isAcknowledged()) {
//            System.out.println("----------Add mapping success----------" + res.isAcknowledged());
//        } else {
//            System.out.println("----------Add mapping failed-----------" + res.isAcknowledged());
//        }
//        return res.isAcknowledged();
//    }
//
//    /**
//     * 根据json动态构造mapping索引对应的XContentBuilder
//     */
//    private XContentBuilder generateMappingBuilder(Object object) {
//        XContentBuilder builder = null;
//        try {
//            builder = XContentFactory.jsonBuilder();
//            JSONObject jsonObj = null;
//            if (object instanceof String) {
//                jsonObj = JSON.parseObject((String) object);
//            }
//            // json对象
//            generateMappingBuilder(jsonObj, builder, true);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("get json builder error");
//        }
//        return builder;
//    }
//
//    /**
//     * 根据json对象动态构造mapping索引对应的XContentBuilder
//     */
//    private void generateMappingBuilder(Object objJson, XContentBuilder builder, boolean isBegin) {
//        try {
//            // #builder构造,需要添加一个开始"{"
//            if (isBegin) {
//                builder.startObject();
//            }
//            // json数组
//            if (objJson instanceof JSONArray) {
//                JSONArray objArray = (JSONArray) objJson;
//                for (int i = 0; i < objArray.size(); i++) {
//                    generateMappingBuilder(objArray.get(i), builder, false);
//                }
//            }
//            // json对象
//            else if (objJson instanceof JSONObject) {
//                JSONObject jsonObject = (JSONObject) objJson;
//                Iterator<String> it = jsonObject.keySet().iterator();
//                while (it.hasNext()) {
//                    String key = it.next().toString();
//                    Object object = jsonObject.get(key);
//
//                    // builder:key;这里区分object和普通的属性(冒号前认为为对象,冒号后为属性)
//                    if (!key.equals("type") && !key.equals("format")) {
//                        builder.startObject(key);
//                        // System.out.println("==" + key);
//                    }
//
//                    // json数组
//                    if (object instanceof JSONArray) {
//                        JSONArray objArray = (JSONArray) object;
//                        generateMappingBuilder(objArray, builder, false);
//                    }
//                    // json对象
//                    else if (object instanceof JSONObject) {
//                        generateMappingBuilder((JSONObject) object, builder, false);
//                    }
//                    // 其他
//                    else {
//                        builder.field(key, object.toString());
//                        // System.out.println("====" + key + "," + object.toString());
//                    }
//                }
//                // #builder构造,需要添加一个结束"}"
//                builder.endObject();
//                // System.out.println("==");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("generate mapping builder failed");
//        }
//    }
//
//    /**
//     * @return
//     * @throws IOException
//     */
//    private XContentBuilder generateMapping() throws IOException {
//        XContentBuilder builder = XContentFactory.jsonBuilder();
//        builder = builder
//                // #builer开始"{"
//                .startObject()
//                .startObject("properties") //设置之定义字段
//                .startObject("author")
//                .field("type", "keyword") //设置数据类型
//                .endObject()
//                .startObject("title")
//                .field("type", "text")
//                .endObject()
//                .startObject("content")
//                .field("type", "text")
//                .endObject()
//                .startObject("price")
//                .field("type", "integer")
//                .endObject()
//                .startObject("date")
//                .field("type", "date")  //设置Date类型
//                .field("format", "yyyy-MM-dd HH:mm:ss") //设置Date的格式
//                .endObject()
//                .endObject()
//                // #builer结束"}"
//                .endObject();
//        return builder;
//    }
//
//    /**
//     * 获取访问ES的连接
//     *
//     * @return
//     */
//    private TransportClient getNewClient() {
//        TransportClient client = null;
//        try {
//            Settings settings = Settings.builder().put("cluster.name", CLUSTER_NAME)
//                    // 开启嗅探功能(即自动检测集群内其他的节点和新加入的节点);或者全部用addTransportAddress添加，如下：
//                    .put("client.transport.sniff", true).build();
//            client = new PreBuiltTransportClient(settings)
//                    .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//            System.out.println("get host error");
//        }
//        return client;
//    }

}
