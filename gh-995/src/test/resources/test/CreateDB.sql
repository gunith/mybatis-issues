--
--    Copyright 2009-2016 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       http://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

drop table super_roles if exists;
drop table roles if exists;
drop table users if exists;

create table super_roles (
  id int,
  name varchar(20),
  status varchar(10),
  type varchar(10)
);

create table roles (
  id int,
  name varchar(20),
  status varchar(10),
  super_role_id int
);
  
create table users (
  id int,
  name varchar(20),
  status varchar(10),
  gender varchar(10),
  role_id int
);

insert into super_roles (id, name, status, type) values (1, 'Role1', 'ACTIVE_SR', 'ADMIN');

insert into roles (id, name, status, super_role_id) values (1, 'Role1', 'ACTIVE_R' ,1);

insert into users (id, name, status, gender, role_id) values (1, 'User1', 'ACTIVE', 'MALE', 1);
insert into users (id, name, status, gender, role_id) values (2, 'User2', 'INACTIVE', 'FEMALE', 1);
