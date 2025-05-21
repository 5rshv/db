
create table if not exists public.ola(
id int not null,
constraint ola_pk primary key (id),
name text not null,
age int null,
car_rights boolean DEFAULT FALSE
);

create table if not exists  public.car(
id int not null,
brand text null,
model text null,
price int null,
constraint car_pk primary key (id)
);

create table if not exists  public.car_ola (
car_id int not null,
car_ola_id int not null,
CONSTRAINT car_ola_pk primary key(car_ola_id, car_id),
CONSTRAINT car_ola_ola_fk foreign key (car_ola_id) REFERENCES public.ola(id) ON DELETE CASCADE ON UPDATE CASCADE,
CONSTRAINT car_ola_car_fk FOREIGN KEY (car_id) REFERENCES public.car(id) ON DELETE CASCADE ON UPDATE CASCADE
);

select * from public.ola , public.car , public.car_ola;
