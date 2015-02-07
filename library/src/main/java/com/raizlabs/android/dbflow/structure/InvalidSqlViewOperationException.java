package com.raizlabs.android.dbflow.structure;

/**
 * Gets thrown when an operation is not valid for the SQL View
 */
class InvalidSqlViewOperationException extends RuntimeException {

    InvalidSqlViewOperationException(String detailMessage) {
        super(detailMessage);
    }
}
