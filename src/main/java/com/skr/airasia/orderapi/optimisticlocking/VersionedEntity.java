package com.skr.airasia.orderapi.optimisticlocking;

import java.util.Date;

public interface VersionedEntity {

    Long getId();

    Long getVersion();

    void setVersion(Long version);

    String getTableName();
}
