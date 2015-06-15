package com.raizlabs.android.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

@Table(databaseName = AppDatabase.NAME)
public class Widget extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String name;

    public static List<Widget> getAll() {
        return new Select().from(Widget.class).queryList();
    }

    public static long getCount() {
        return new Select().count().from(Widget.class).count();
    }
}