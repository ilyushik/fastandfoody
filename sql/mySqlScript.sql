CREATE TABLE IF NOT EXISTS Category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Delivery_Way (
    id INT PRIMARY KEY AUTO_INCREMENT,
    way VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Status (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status_name VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Promo_Code (
    id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(10) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Payment_Way (
    id INT PRIMARY KEY AUTO_INCREMENT,
    way VARCHAR(32) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS User_Role (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_role VARCHAR(32) UNIQUE NOT NULL
);

create table Image (
    id bigint primary key auto_increment,
    image_data mediumblob,
    name varchar(255),
    type varchar(255)
);

CREATE TABLE IF NOT EXISTS Item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    item_name VARCHAR(64),
    price INT NOT NULL,
    description TEXT NOT NULL,
    prep_time INT NOT NULL,
    image int UNIQUE REFERENCES Image(id),
    category VARCHAR(32) REFERENCES Category(category_name)
);

CREATE TABLE IF NOT EXISTS Order_Item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    count INT NOT NULL,
    prep_time INT NOT NULL,
    item_id INT REFERENCES Item(id)
);

CREATE TABLE IF NOT EXISTS Person(
  id int PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(32) NOT NULL ,
  surname VARCHAR(32) NOT NULL,
  phone VARCHAR(16) NOT NULL UNIQUE ,
  email VARCHAR(64) NOT NULL ,
  username VARCHAR(32) UNIQUE NOT NULL,
  person_password VARCHAR(32) CHECK (length(person_password) > 7),
  person_role VARCHAR(32) NOT NULL REFERENCES User_Role(user_role),
  image int REFERENCES Image(id)
);

CREATE TABLE IF NOT EXISTS City(
    id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL ,
    longitude decimal(8, 6) NOT NULL ,
    latitude decimal(8, 6) NOT NULL
);

CREATE TABLE IF NOT EXISTS Restaurant(
    id int PRIMARY KEY AUTO_INCREMENT,
    admin_id int UNIQUE NOT NULL REFERENCES Person(id)  ,
    address VARCHAR(64) NOT NULL UNIQUE ,
    longitude decimal(8, 6) NOT NULL ,
    latitude decimal(8, 6) NOT NULL ,
    city_id int REFERENCES  City(id),
    phone VARCHAR(16) NOT NULL ,
    email VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS Purchase(
    id int PRIMARY KEY AUTO_INCREMENT,
    prep_time int NOT NULL ,
    wish text,
    restaurant_id int NOT NULL REFERENCES Restaurant(id) ,
    payment_way VARCHAR(32) NOT NULL REFERENCES Payment_Way(way) ,
    promo_code int REFERENCES Promo_Code(id),
    status VARCHAR(32) NOT NULL REFERENCES Status(status_name) ,
    delivery_way VARCHAR(32) NOT NULL REFERENCES Delivery_Way(way) ,
    order_item_id int NOT NULL REFERENCES Order_Item(id) ,
    person_id int REFERENCES Person(id),
    address VARCHAR(64),
    date timestamp NOT NULL
);

ALTER TABLE Person ADD COLUMN restaurant_id int UNIQUE REFERENCES Restaurant(id) ;
ALTER TABLE Order_Item ADD COLUMN purchase_id int REFERENCES Purchase(id);
