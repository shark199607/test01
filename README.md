##资料
[spring文档](https://spring.io/guides)
[github oauth](https://developer.github.com/apps/building-oauth-apps/)
[okHttp](https://square.github.io/okhttp/)


##工具
[Git]
[VP]

##脚本
`sql
create table user.user
(
	id int auto_increment
		primary key,
	account_id varchar(100) null,
	name varchar(20) null,
	token char(80) null,
	gmt_create bigint null,
	gmt_modified bigint null
);


`