create table if not exists task (
                                       id int primary key,
                                       topic varchar not null,
                                       definition varchar,
                                       task_link varchar
);

insert into task
values(1,'test','test','test');