-- liquibase formatted sql

-- changeset 5rshv:1
CREATE TABLE if not exists migration_Student (
    id serial,
    name text
)


-- changeset 5rshv:2
CREATE INDEX idx_name ON migration_Student (name);


select * from migration_Student