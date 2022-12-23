create database cajero_2022;
use cajero_2022;
create table cuentas
(id_cuenta int not null primary key,
saldo double not null,
tipo_cuenta varchar(20) not null
);

insert into cuentas values(1000, 3000, 'ahorro'),(2000, 5000, 'corriente');

create table movimientos
(id_movimiento int not null primary key auto_increment,
id_cuenta int not null,
fecha  datetime not null,
cantidad double not null,
operacion varchar(45) not null,
foreign key(id_cuenta) references cuentas(id_cuenta)
);

create user ucajero identified by 'ucajero';
grant all privileges on cajero_2022.* to ucajero;