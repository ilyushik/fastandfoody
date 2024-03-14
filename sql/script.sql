CREATE TABLE IF NOT EXISTS Category(
    id int primary key generated by default as identity ,
    category_name varchar(32) unique not null
);

CREATE TABLE IF NOT EXISTS Delivery_Way
(
    id int primary key generated by default as identity ,
    way varchar(32) unique not null
);

CREATE TABLE IF NOT EXISTS Status(
    id int primary key generated by default as identity ,
    status_name varchar(32) unique not null
);

CREATE TABLE IF NOT EXISTS Promo_Code
(
    id int primary key generated by default as identity ,
    code varchar(10) unique not null
);

CREATE TABLE IF NOT EXISTS Payment_Way
(
    id int primary key generated by default as identity ,
    way varchar(32) unique not null
);

CREATE TABLE IF NOT EXISTS User_Role
(
    id int primary key generated by default as identity ,
    user_role varchar(32) unique not null
);
CREATE TABLE IF NOT EXISTS Item(
    id int primary key generated by default as identity ,
    item_name varchar(64),
    price int not null,
    description text not null,
    prep_time int not null,
    category varchar references Category(category_name),
    item_img varchar unique not null
);

CREATE TABLE IF NOT EXISTS Order_Item(
    id int primary key generated by default as identity ,
    count int not null ,
    prep_time int not null,
    item_id int references Item(id)
);

CREATE TABLE IF NOT EXISTS Person(
    id int primary key generated by default as identity ,
    name varchar(32) not null,
    surname varchar(32) not null,
    phone varchar(16) not null unique ,
    email varchar(64) not null unique ,
    username varchar(32) not null unique ,
    person_password varchar(32) check ( length(person_password) > 7 ),
    person_role varchar references User_Role (user_role) not null
);

CREATE TABLE IF NOT EXISTS City(
  id int primary key generated by default as identity,
  name varchar(32) not null ,
  longitude numeric not null,
  latitude numeric not null
);

CREATE TABLE IF NOT EXISTS Restaurant(
    id int primary key generated by default as identity ,
    admin_id int references Person(id) not null unique ,
    address varchar(64) not null unique ,
    longitude numeric not null,
    latitude numeric not null,
    city_id int references City(id),
    phone varchar not null ,
    email varchar not null
);

CREATE TABLE IF NOT EXISTS Purchase(
    id int primary key generated by default as identity ,
    prep_time int not null ,
    wish text,
    restaurant_id int references Restaurant(id) not null ,
    payment_way varchar references Payment_Way (way) not null,
    promo_code int references Promo_Code(id),
    status varchar references Status(status_name) not null,
    delivery_way varchar references Delivery_Way(way) not null,
    order_item_id int references Order_Item(id) not null,
    person_id int references Person(id),
    address varchar ,
    date timestamp not null
);

ALTER TABLE Person ADD COLUMN restaurant_id int references Restaurant(id) unique;
ALTER TABLE Order_Item ADD COLUMN purchase_id int references Purchase(id);
ALTER TABLE City ADD COLUMN