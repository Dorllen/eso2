http://127.0.0.1:8080/esoso/pa/4b3a7cb53124f3c40d19ed76caa50c37
http://127.0.0.1:8080/esoso/s?q=python&t=answer&f=segmentfault&defaultWebsites=segmentfault|github|cnblog&defaults=segmentfault&d=
127.0.0.1:8080/esoso/s?q=java&t=answer&f=segmentfault&defaultWebsites=segmentfault|github|cnblog&defaults=segmentfault&d=
http://127.0.0.1:8080/esoso/s?q=ubuntu&f=segmentfault&t=answer


Websites文件夹：放爬虫内容页模板的地方


2017-4-27：
	重要更新：
		1. 所有数据库的using默认为0，为1代表在使用。所有Mapper也变动
		2. 在WebsiteBO做了不符规范的操作？ 写了业务转换逻辑！用于在线触发爬虫内容详情页
		3. 将数据库字段从varchar(255) 改称text|longtext
		4. status字段0代表已处理，1代表未处理，2,3代表严重程度递增。1为默认
		5. 