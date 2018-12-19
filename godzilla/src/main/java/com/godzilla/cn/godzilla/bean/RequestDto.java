package com.godzilla.cn.godzilla.bean;

/**
 * @ProjectName: knowledge-graph
 * @Package: com.iflytek.knowledgegraph.model
 * @Author: xingli12
 * @Description:
 * @Date: Created in 2018-07-03 17:40
 * @Modified By:
 * @UpdateDate:
 * @Version:
 */
public class RequestDto {
    private String content;

    private String relation;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
