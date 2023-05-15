create table if not exists orders(
    id serial primary key,
    description text,
    status_order varchar
);
