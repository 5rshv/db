-- changeset 5rshv:1
CREATE TABLE if not exists faculty (
    id serial primary key,
    name TEXT,
)

-- changeset 5rshv:2
CREATE INDEX if not exists idx_faculties_name_color ON faculty(name, color);