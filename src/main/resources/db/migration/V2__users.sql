INSERT INTO roles (role_type)
VALUES ('USER'),
       ('ADMIN');
INSERT INTO users (user_name, password, email, first_name, last_name, birth_date)
VALUES ('Admin', '$2a$10$1ewKFOkMkeK73AzK88QfLeBPWo.vas9JaXu8hcDX/Cil0O5l0sRC6', 'admin@yopmail.com', 'admin',
        'Admin', '2020-12-12'),
       ('User', '$2a$10$8W9KSOCgREYuHWDdD8bAL.o7WPf4vNZ3CqcHmeeyFmR0bYCs3u0nC', 'user@yopmail.com', 'user', 'User',
        '2020-12-12'),
       ('Admin1', '$2a$10$1ewKFOkMkeK73AzK88QfLeBPWo.vas9JaXu8hcDX/Cil0O5l0sRC6', 'admin1@yopmail.com', 'admin',
        'Admin', '2020-12-12'),
       ('User1', '$2a$10$8W9KSOCgREYuHWDdD8bAL.o7WPf4vNZ3CqcHmeeyFmR0bYCs3u0nC', 'user1@yopmail.com', 'user', 'User',
        '2020-12-12');
INSERT INTO user_roles (user_id, role_id)
VALUES (1, 2),
       (2, 1),
       (3, 2),
       (4, 1);