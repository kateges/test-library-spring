delete from ges_us_test where USER_LOG='tomcat';
delete from authorities where USERNAME='tomcat';

insert into authorities(USERNAME, AUTHORITY)
values ('tomcat', 'ROLE_USER');
insert into ges_us_test (USER_LOG, USER_PWD )
values ('tomcat', 'tomcat');

commit;