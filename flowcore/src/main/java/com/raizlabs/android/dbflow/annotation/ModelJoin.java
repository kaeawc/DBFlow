package com.raizlabs.android.dbflow.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: Marks a Model class as corresponding to a JOIN statement.
 * Enables it to act just like a {@link com.raizlabs.android.dbflow.annotation.ModelView} except for
 * JOIN statements.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ModelJoin {
}
