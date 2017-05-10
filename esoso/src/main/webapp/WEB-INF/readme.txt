
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

性能介绍：
	1. 使用了存储过程
	2. 使用了内置函数
	3. 对ArrayList使用定义了大小
	4. 对数据库返回数据做了处理，并且处理都是做了再判断（即再校验)


2017-5-10：
	重要更新：
	1. 去除所有的Patch方式，部分浏览器，及Jquery不支持
	2. 对与数据库接口定义规范，增加了一种：selectVersionsByIdAndName
	3. 对VO与DAO可以直接转换增加了一条。因为有些页面只有数据，所以将DAO -> PO(POJO) - > VO ，当然VO还是用新模型来接收，不放入Model包（即POJO）所在命名环境中
	4. 详情页面解析，现在要去除.html后缀，现在是有后缀的
	5. 对于爬虫结果入库，需要将收集、访问量放入新数据项中
	6. 对与PullArticle不适用versionId作为传导，直接使用id做为参数。
	7. sql语句：select * from pullArticles where pullArticles.using = 1; 获取正在使用的数据项，是否有问题？应该获取同名同uuid的一个，不应该用using来取？【待思考】
	8. 对与服务的描述：服务默认都是开启的，只需要指定关闭对象即可
	9. 禁用内容详情版本访问，做不到禁用某个版本访问，只能禁用某个链接。【待改进】
	
	
	细节更新：
	1. 修正$.get()中参数传错的问题。非{}对象类型
	2. 【待改进】对于表格数据进行操作之后，页面是没有更新的。现做的修正：对搜索的结果进行重新请求，对与页面访问生成的暂时忽略。原因：onclick事件没有传入this,无法找到来源
    3. 将pullArticle的scores修改为double类型


2017-5-9：
	重要更新：
	1. IDNameValueModel 为通用的数据库接收模型。id,name,value值
	2. 统一，服务返回数字到controler层的含义，0代表失败，-1代表异常，-2代表其他异常，1及更多代表成功或受影响的行
	3. 将version的defJs、defCss、defPage的定义进行更改，只存储文件名
	4. 不太清楚存储过程返回值是否方便，所以对于Version的默认设置采用了两条sql语句(已解决：存储过程可以返回受影响行)

2017-5-7：
	待改进：
	1. 爬虫队列的立即执行，是需要控制的，防止多次点击。要对数据进行标识. 可以进行静态变量标识，用作爬虫器是否已在运行。
	2. 多用户搜索同一界面，禁止放入爬虫待处理队列！
	3. 管理员触发爬虫器依赖选择站点，需要重构队列获取器，按时获取部分数据，同时校验开始时间与结束时间。对与爬虫好的队列需要赋值endTime
	4. 同一时间自定义计划3秒内只能添加一条，或者进行校验，同一条url不允许添加
	5. 页面的删除，恢复，新增操作都要刷新界面！
	6. 队列数据确定，需要在队列的字段paId中关联内容版本，新增hTime，hMan等
	7. 可以考虑为收录管理增加一个测试模块，用于测试那个页面对应的那个站点版本是可用的
	
	续(2017-5-8):
		1. 定时任务队列与定时计划的区别。定时任务队列是对收录站点的某个地址进行定时刷新，刷新 版本。定时计划是对站点进行设置，是按热度进行更新，还是其他。
		2. 【值得思考的问题】：定时爬取收录库的数据，需要注意它的依赖站点版本选择的是当前版本，还是以前的版本。可能有种情况就是某些网站的页面是：旧数据页面是不改变的
		
		
	重要更新：
		1. 新增一个ScheduleMissions表，用于定义处理站点的内容
		2. 为scheduleQueue，wormlog、pullArticle等为status增加新定义。-1代表删除
		3. 定义pullArticles的using的意思在于：1代表使用。0代表禁用，禁用代表当前内容不是最新采用的内容。不是最新版本



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




	