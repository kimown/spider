--
--    Copyright 2015-2016 the original author or authors.
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

-- -- create table city (id int primary key auto_increment, name varchar, state varchar, country varchar);
--
-- insert into city (name, state, country) values ('San Francisco', 'CA', 'US');

-- drop TABLE mytable if EXISTS ;
-- drop TABLE city if EXISTS ;
-- CREATE table mytable(id text,name  text);
-- create table city3(id text,name text,state text,country text);

-- insert into city3 VALUE ('1','e','l','l');

drop table if exists test;
create table if not EXISTS city3(id text,name text,state text,country text);
insert into city3(id,name,state,country) values('110','import.sql','js','china');

-- insert into city3(id,name,state,country) values('110333','import.sql33333','33','china3333');