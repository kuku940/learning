package cn.xiaoyu.learning.common;

import java.util.List;
import java.util.Map;

/**
 * 基础返回结果类
 *
 * @author 01399268
 * @date 2020/9/10
 */
public class BaseResult {
    /**
     * 请求是否成功
     */
    public Boolean success;
    /**
     * 请求的提示信息
     */
    public String msg;
    /**
     * 分页查询的总页数
     */
    public Integer total;
    /**
     * 分页查询的数据集
     */
    public List<Map> rows;
    public Map resultMap;
    public String resultJson;
    public List<Map<String, Object>> resultMaps;
    public List<BatchDealResult> batchDealResults;

    class BatchDealResult {
        public int index;
        public String msg;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Map> getRows() {
        return rows;
    }

    public void setRows(List<Map> rows) {
        this.rows = rows;
    }

    public Map getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map resultMap) {
        this.resultMap = resultMap;
    }

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }

    public List<Map<String, Object>> getResultMaps() {
        return resultMaps;
    }

    public void setResultMaps(List<Map<String, Object>> resultMaps) {
        this.resultMaps = resultMaps;
    }

    public List<BatchDealResult> getBatchDealResults() {
        return batchDealResults;
    }

    public void setBatchDealResults(List<BatchDealResult> batchDealResults) {
        this.batchDealResults = batchDealResults;
    }
}
