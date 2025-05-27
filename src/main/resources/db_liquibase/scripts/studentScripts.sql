-- changeset 5rshv:1
CREATE TABLE if not exists student (
    id serial primary key,
    name text,
    test text
)

-- changeset 5rshv:2
CREATE INDEX if not exists idx_students_name ON student(name);