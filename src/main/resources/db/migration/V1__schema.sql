CREATE TABLE users
(
    user_id    SERIAL PRIMARY KEY,
    user_name  VARCHAR UNIQUE NOT NULL,
    password   VARCHAR        NOT NULL,
    email      VARCHAR UNIQUE NOT NULL,
    first_name VARCHAR        NOT NULL,
    last_name  VARCHAR        NOT NULL,
    birth_date DATE           NOT NULL
);

CREATE TABLE roles
(
    role_id   SERIAL PRIMARY KEY,
    role_type VARCHAR UNIQUE NOT NULL
);

CREATE TABLE user_roles
(
    user_id BIGINT REFERENCES users (user_id),
    role_id BIGINT REFERENCES roles (role_id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE blogs
(
    blog_id   SERIAL PRIMARY KEY,
    title     VARCHAR(1000) NOT NULL,
    content   TEXT          NOT NULL,
    blog_date DATE          NOT NULL
);

CREATE TABLE comments
(
    comment_id      SERIAL PRIMARY KEY,
    comment_content VARCHAR(10000) NOT NULL,
    comment_date    DATE           NOT NULL,
    blog_id         BIGINT REFERENCES blogs (blog_id) ON DELETE CASCADE, -- Added ON DELETE CASCADE here
    user_id         BIGINT REFERENCES users (user_id)
);
