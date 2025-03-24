# Tour System
本项目为学生游学系统。学生游学系统可以帮助学生管理自己的游学活动，具备**游学推荐、游学路线规划、场所查询、游学日记管理**等功能。

# 项目功能
游学前：需先按照游学热度、评价和个人兴趣选择游学目的地；
游学中：在学校和景点内部也需要根据游览的目标规划最优的参观线路，在游览过程中还可以给出相应的景点介绍和场所查询；
游学后：可以根据所拍照片和游览经历生成游学日记。

# 设计思路
## 后端
基于Spring Boot框架完成后端开发，设计并实现游学地点推荐、路线规划、日记管理等核心功能；  
使用MyBatis进行数据持久化；  
使用Redis缓存热门景点数据；  
使用Elasticsearch实现日记的全文检索。  

## 前端
采用`vue`来作为框架，使用`ElmentUI`、`axios`等组件。
根据应实现的功能，将每个功能划分到不同页面具体实现。

# 运行实现
本项目采用前后段分离的结构，用户在下载完文件夹`backend`(后端文件夹)和`fronted`(前端文件夹)后，按照以下步骤即可实现运行。
1. 用idea或其他开发集成环境，分别打开`backend`和`fronted`文件夹。
2. 在`backend`文件夹下点击运行按钮。
3. 在front文件的命令行中输入：`npm run serve`命令,并在网页端输入网址`localhost:8082`，即可运行。

# 实现效果
## 1 登录/注册页面
用户进入到页面，首先需要登录(可使用用例：用户名：user 密码：user)也可以选择注册。
![image](https://github.com/user-attachments/assets/cf1b168d-8a7f-42ea-96d6-44e9f7384811)
![image](https://github.com/user-attachments/assets/9cee0124-29cf-4d96-80d5-e1f060381120)
## 2 游学推荐
1.推荐功能：在进入到游学推荐页面，系统会按照热度自动为学生推荐前10个景点。也可以点击“显示系统默认排序”按钮，显示系统排序。

2.用户可以点击“显示所有数据”按钮，浏览所有游学目的地。

3.推荐功能：用户可以只选择不同的排序方式（热度或评价）进行排序，结果会显示前10个地点
![image](https://github.com/user-attachments/assets/d997a531-35b4-4f42-a665-bcc299a87faf)

4.查询功能：用户可以输入(完全或不完全）地点名，选择或输入类别，选择热度或者评价进行排序，点击“搜索”按钮系统会显示排序结果

![image](https://github.com/user-attachments/assets/706bdd9a-3059-4e16-9868-f323b52ab3d9)
![image](https://github.com/user-attachments/assets/701e02e9-be63-4f0a-be10-8ac9a9739e59)

5.类别筛选：用户可以对输出结果进行地点类别(单选或多选）筛选。

![image](https://github.com/user-attachments/assets/2ca10d61-79a1-45e4-8611-3f053f901503)
![image](https://github.com/user-attachments/assets/e6efac44-cd53-4226-bc72-174ad8c886ac)

6.美食推荐：用户点击“查看详情”按钮，系统会跳转到美食推荐页面。

7.点击任意一行游学地信息，会自动跳转到场所查询页面。
## 3 场所查询
1.进入场所查询页面，系统会自动显示景区内所有设施
![image](https://github.com/user-attachments/assets/16594715-f3b5-4309-a0de-0db3f9ec4906)

2.过滤功能：用户可以对输出结果进行设施类别(单选或多选）筛选。

![image](https://github.com/user-attachments/assets/1a4355bb-7887-474c-a5cf-651a6a3c7ed8)
![image](https://github.com/user-attachments/assets/9bb33a47-21ff-43cd-a4eb-62329c138c4e)

3.查询、排序和范围排序功能：用户可以输入或选择地点，输入距离半径，输入或选择类别，点击“搜索”按钮对某个景点的附近设施进行距离排序。

![image](https://github.com/user-attachments/assets/917c466d-e95b-43dc-8dbe-5e7c2b0e1748)

## 4 游学日记管理

1.在进入游学日记管理页面，系统会自动展示所有游学日记和所有日记数目。（默认排序是热度）
![image](https://github.com/user-attachments/assets/54312f9a-ef5b-4039-b458-928aa673fb15)

2.浏览和查询功能：用户可以输入(完全或不完全）日记名称查询日记，选择一种排序方式，点击“搜索”按钮，就可以实现精准查询。
![image](https://github.com/user-attachments/assets/c05fbab7-8f0c-445e-baa6-9a44d7facf9a)

3.评分和热度功能：用户点击“查看详情”按钮，可以跳转到日记页面，并可以在左上角，通过点星星实现评分，返回所有日记页面的时候，评分会自动改变，热度会自加1。
点击之前：（评分为8.1，热度为984）
![image](https://github.com/user-attachments/assets/88c08fbd-886d-4df1-8bc9-07646587f81a)

用户评1分
![image](https://github.com/user-attachments/assets/adcd344b-39ae-4992-bb80-5a7e70f680fc)

点击“返回”按钮后，返回到所有日记页面时，
当前评分为7.4，热度为985。
![image](https://github.com/user-attachments/assets/8579a68a-b1e9-41e7-844a-34aa0c35df48)

4.推荐功能：用户可以选择热度或评价进行排序。
热度：结果与默认排序相同
评价：
![image](https://github.com/user-attachments/assets/76b598d4-d496-4321-bad9-4dcbdfe2fe88)

5.排序功能：用户可以输入或选择目的地，选择热度或评价进行排序,点击“搜索”按钮进行排序。
![image](https://github.com/user-attachments/assets/788ca009-9d19-4cbb-bc05-96639ed5cbfa)

6.全文检索功能：在日记页面，可以在搜索框中输入要查询的内容，系统会自动高亮显示查询的内容。
![image](https://github.com/user-attachments/assets/12bed9db-d723-40d8-b411-e7979e83d3a5)

7.压缩存储功能：在用户写完日记后，日记会压缩存储，以2进制的形式存在数据库中。(下图为数据库存储形式)
![image](https://github.com/user-attachments/assets/ace28feb-a7ab-4ac7-bcf7-084999694dee)

8. 写日记功能：用户可以点击“新增日记按钮”跳转到写日记页面，在写日记页面，可以输入标题，输入或选择游学地，在文本框中写内容。点击“提交”按钮，会提示成功信息，过2.5s后会跳转到所有日记页面。
![image](https://github.com/user-attachments/assets/6d4aba3c-b0e3-4c58-9a5f-375c5d73ae66)
![image](https://github.com/user-attachments/assets/7ee53177-7d02-44c7-8ed4-24076275b802)


## 5 游学路线规划
地点选择和路线规划：
1.用户可以选择最短路径策略或者最短时间策略的规划方式，

2.输入或选择出发地点，输入目的地，点击“新增目标景点”按钮，添加多个目标景点。

3.点击“提交”按钮，系统会跳出成功提交弹框，之后游学路线规划为自动显示出来。
![image](https://github.com/user-attachments/assets/85a666de-b836-4beb-8c31-68a2b0c7238c)
![image](https://github.com/user-attachments/assets/2f57ee31-0c89-4b37-a2c6-911eba78dd85)

## 6 美食推荐
1.进入美食推荐页面，系统会自动推荐前10个美食。或者点击“显示系统默认排序”来显示结果。(默认排序是按照热度)
![image](https://github.com/user-attachments/assets/2ddc3a5f-8b9c-4511-8b26-4b5ed3e25d0c)

2.搜索和排序功能：用户可以选择搜索方式(美食名称\餐馆名称\菜系)，输入搜索内容，选择排序方式(热度\评价\距离)，点击“搜素”按钮进行搜索，系统会显示排序结果。
![image](https://github.com/user-attachments/assets/85496036-4ffd-4f7c-ba6a-93f8d79edf12)
![image](https://github.com/user-attachments/assets/37344ecd-8d04-4209-a87b-eb8416bacad5)

3.过滤功能：用户可以在菜系筛选单个或多个类别。


![image](https://github.com/user-attachments/assets/55f8d297-77cf-4cfe-a49a-962a8a0c5de5)
![image](https://github.com/user-attachments/assets/0c036218-9583-431c-8694-5d7b76287caa)


