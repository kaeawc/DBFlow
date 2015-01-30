package com.raizlabs.android.dbflow.structure;

import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.container.BaseModelContainer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 */
public class ModelForeignKeyContainer<ModelClass extends Model> extends BaseModelContainer<ModelClass, Map<String, Condition>> {

    public ModelForeignKeyContainer(Class<ModelClass> table) {
        super(table);
    }

    public ModelForeignKeyContainer(Class<ModelClass> table, Map<String, Condition> conditionMap) {
        super(table, conditionMap);
    }

    /**
     * Lazy-loads the underlying model based on the Map of {@link com.raizlabs.android.dbflow.sql.builder.Condition} in
     * this container.
     *
     * @return The model queried from the {@link com.raizlabs.android.dbflow.annotation.ForeignKeyReference}
     */
    public ModelClass toModel() {
        if (getModel() == null) {
            setModel(new Select().from(getTable()).where(getData().values()).querySingle());
        }
        return getModel();
    }

    @Override
    public Object getValue(String columnName) {
        return getData().get(columnName).value();
    }

    @Override
    public Map<String, Condition> newDataInstance() {
        return new LinkedHashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public BaseModelContainer getInstance(Object inValue, Class<? extends Model> columnClass) {
        return new ModelForeignKeyContainer(columnClass, (Map<String, Condition>) inValue);
    }

    @Override
    public void put(String columnName, Object value) {
        getData().put(columnName, Condition.column(columnName).is(value));
    }

}
