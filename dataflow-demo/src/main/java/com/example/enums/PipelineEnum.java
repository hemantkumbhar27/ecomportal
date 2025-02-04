package com.example.enums;

public enum PipelineEnum {
    REPLICATION,
    MIGRATION;

    public boolean isReplication() {
        return this.equals(REPLICATION);
    }

    public boolean isMigration() {
        return this.equals(MIGRATION);
    }

}
