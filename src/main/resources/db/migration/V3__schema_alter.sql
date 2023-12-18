ALTER TABLE blogs
    ADD COLUMN user_id BIGINT REFERENCES users (user_id);
