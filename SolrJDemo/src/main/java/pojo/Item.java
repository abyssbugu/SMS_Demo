package pojo;

import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

/**
 * Created by Abyss on 2018/6/8.
 * description:
 */
public class Item implements Serializable {
    @Field("id")
    private String id;

    @Field("title")
    private String title;

    @Field("price")
    private long price;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getPrice() {
        return price;
    }
}
