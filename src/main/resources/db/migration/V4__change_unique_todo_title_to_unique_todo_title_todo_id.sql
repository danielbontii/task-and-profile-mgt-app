ALTER TABLE todos
    DROP CONSTRAINT uc_todos_title;

ALTER TABLE todos
    ADD CONSTRAINT uc_todos_title_todos_id UNIQUE (user_id, title);