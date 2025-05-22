
-- changeset 5rshv:1
CREATE TABLE if not exists student (
    id serial,
    name text
);


-- changeset 5rshv:2
CREATE INDEX idx_name ON student (name);