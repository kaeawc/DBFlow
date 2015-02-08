package com.raizlabs.android.dbflow.structure;

/**
 * Description: The base Join class for it
 */
public class BaseModelJoin implements Model {
    @Override
    public void save(boolean async) {
        throw new InvalidSqlViewOperationException("Join " + getClass().getName() + " is not saveable");
    }

    @Override
    public void delete(boolean async) {
        throw new InvalidSqlViewOperationException("Join " + getClass().getName() + " is not deleteable");
    }

    @Override
    public void update(boolean async) {
        throw new InvalidSqlViewOperationException("Join " + getClass().getName() + " is not updateable");
    }

    @Override
    public void insert(boolean async) {
        throw new InvalidSqlViewOperationException("Join " + getClass().getName() + " is not insertable");
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean exists() {
        throw new InvalidSqlViewOperationException("Join " + getClass().getName() + " is not existable");
    }
}
