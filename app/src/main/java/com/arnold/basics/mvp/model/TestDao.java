package com.arnold.basics.mvp.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TestDao {
    @Id
    public String id;

    @Generated(hash = 978546308)
    public TestDao(String id) {
        this.id = id;
    }

    @Generated(hash = 1692582752)
    public TestDao() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
