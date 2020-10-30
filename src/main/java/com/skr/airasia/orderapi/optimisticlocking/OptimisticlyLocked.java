package com.skr.airasia.orderapi.optimisticlocking;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks an Entity for optimistic locking.
 * Entities must implment com.slalom.blog.jdbcexample.optimisticlock.VersionedEntity
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OptimisticlyLocked { }
