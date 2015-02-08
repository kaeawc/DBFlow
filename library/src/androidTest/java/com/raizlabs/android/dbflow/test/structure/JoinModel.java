package com.raizlabs.android.dbflow.test.structure;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelJoin;
import com.raizlabs.android.dbflow.structure.BaseModelJoin;
import com.raizlabs.android.dbflow.test.TestDatabase;

/**
 * Description:
 */
@ModelJoin(databaseName = TestDatabase.NAME)
public class JoinModel extends BaseModelJoin {

    @Column
    String name;
}
