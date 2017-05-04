
测试链接：

http://127.0.0.1:8080/esoso/pa/4b3a7cb53124f3c40d19ed76caa50c37
http://127.0.0.1:8080/esoso/s?q=python&t=answer&f=segmentfault&defaultWebsites=segmentfault|github|cnblog&defaults=segmentfault&d=
127.0.0.1:8080/esoso/s?q=java&t=answer&f=segmentfault&defaultWebsites=segmentfault|github|cnblog&defaults=segmentfault&d=

key: java 搜索：http://127.0.0.1:8080/esoso/s?q=java&t=answer&f=segmentfault&defaultWebsites=segmentfault%7Cgithub%7Ccnblog&defaults=segmentfault&d=

http://127.0.0.1:8080/esoso/s?q=ubuntu&f=segmentfault&t=answer

手动触发详情页爬取:
http://127.0.0.1:8080/esoso/admin/worm/start

在綫访问跳转：
http://127.0.0.1:8080/esoso/pa/f/b717ef7ea0b7e8fbcb83677db51a9382

http://127.0.0.1:8080/esoso/admin/website/getInfo3?name=Result 測試CommonClassLoader






Websites文件夹：放爬虫内容页模板的地方


2017-4-27：
	重要更新：
		1. 所有数据库的using默认为0，为1代表在使用。所有Mapper也变动
		2. 在WebsiteBO做了不符规范的操作？ 写了业务转换逻辑！用于在线触发爬虫内容详情页
		3. 将数据库字段从varchar(255) 改称text|longtext
		4. status字段0代表已处理，1代表未处理，2,3代表严重程度递增。1为默认
		
		
2017-4-28:
	1. 新增页面记录表
	2. 在线爬虫，结果页数据，在ScheduelQueues中的数据不重复记录
	3. 新增规则：只有搜索会有限寻找索引服务。
	4. 10分以内多次访问算一次
	
需兼容問題：
https://static.segmentfault.com/v-590a963a/global/css/global.css
http://segmentfault.con/static/css/qa.css




	