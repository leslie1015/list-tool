# 列表工具
一个Java+Vue开发的的工具，提供配置化开发后台列表的功能，开发一个列表只需要两步：<br>
1. 写sql语句 
2. 配置sql语句对应的字段属性

## 使用方式

### 一、配置sql
后台代码“/resource/sqlmap/”目录下，新建对应业务文件夹（分类更清晰），在文件夹内新建的或者已有的xml文件中， 写好对应的业务SQL脚本，注意sql的id，id范围最好根据业务提前规划好，如用户相关的列表，id从300000开始往后累加，避免后续管理混乱，后面列表菜单配置、资源权限控制等也会用到这个id。框架会扫描sqlmap文件夹，找到这个id对应的sql执行。如下图：

如图，id为300001，查询的字段如图
![avatar](https://upload-images.jianshu.io/upload_images/13491503-32d3d0bdd5ef16cd.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

### 二、配置菜单
配置sql对应的菜单信息
![avatar](https://upload-images.jianshu.io/upload_images/13491503-5d091b877e28e24b.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

### 三、配置列表字段
1. 左侧查询找到刚才配置的菜单信息，点击对应行的配置，右侧自动载入300001对应SQL的查询字段
字段名：即后台sql查询的列名，不可编辑
2. 可以拖动行来排序，列表字段展示的时候排序以此为据，字段名为“ACTION”的为默认行，默认在最后，如果列表仅查看，不需要操作按钮，可以选择隐藏此列。 此行配置后，还需要进行操作按钮配置，功能暂未提供，目前可以直接配置后台表来实现。
3. 字段说明<br>
  > - 显示名：在列表中的列名，可以配置列是否隐藏显示（display:none）<br>
  > - 是否主键：用于行操作按钮点击时获取关键数据<br>
  > - 参数key：对应后台数据字典表的key，用于将库中存储的1、2、3...等状态类的数据替换为对应的字典值，配置好字典表后，此处只需要配置字典的key即可<br>
  > - 查询方式：配置列的查询方式，支持七种查询<br>
  > - 是否排序：选择是，列head上出现排序按钮<br>
  > - 是否时间字段：前端判断字段是否需要格式化<br>
  > - 时间格式：如果选择是，可以配置列中时间展示格式<br>



4. 开发环境配置成功后点击右上角“导出配置”按钮，下载配置对应的sql，到测试环境执行即可将配置信息复制过去
![avatar](https://upload-images.jianshu.io/upload_images/13491503-688c7fd227aa0230.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

### 四、效果
如图：
![avatar](https://upload-images.jianshu.io/upload_images/13491503-0d29cc8031c61cca.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp)

使用列表可以配置化开发列表应用，减少重复类似的查询代码，无业务侵入性

## 实现原理
- 列表的显示。mybatis开发，需要一个实体，与数据库表字段做映射，返回对应结构的数据。显然我们这个工具这样做是不行的，因为共用一个查询方法，字段是变化的，所以返回的数据结构用了HashMap，可以灵活返回任意字段。定义Mapper接口，自定义接口的实现，注入SqlSessionFactory，获取到sqlSession，sqlSession.selectList(resourceId)执行resourceId对应的sql。

- 列表的查询。查询使用了Mybatis的拦截器，原理是执行前拦截sql，根据配置的查询条件修改sql，网上所查询到的Mybatis拦截器示例基本都是拦截StatementHandler.prepare，刚开始写的时候我拦截的也是这个方法，没什么问题，但是在做分页的时候出问题了，正确的结果应该是先拼接查询条件，再拼接分页语句，工具里面使用了PageHelper5来做分页，修改拦截器的顺序无效，结果都是先拼接分页语句再拼接查询条件，后来查看PageHelper5文档发现，PageHelper5拦截的是Executor的query方法，逻辑上在StatementHandler.prepare之前执行，所以配置拦截器的顺序失效了，只有拦截相同的方法，拦截器放置的顺序才会有效，所以参照了PageHelper5的拦截器源码，拦截query方法。

- 列表前端页面。前端代码使用Vue2.0+iView开发，关键在于一切都是动态的，包括动态路由、动态字段、动态样式等，列表渲染用了render

- 其它。因为动态，所以配置多，配置信息的特点是不需要较高的时效性，很少变动，工具所有配置信息全部放到Redis中缓存，通过提高配置加载速度，支持手动刷新缓存。前端sessionStorage缓存菜单、列表字段等信息。


## 说明
##### 此项目为列表后端，整体为前后端分离项目，对应的前端为[列表工具前端代码](https://github.com/leslie1015/list-tool-web) ，此工具代码具体的应用，请参照Demo，后端为Spring Cloud架构，前端是基于Vue2.6的多页面项目
##### https://github.com/leslie1015/silk-server （java8+mysql+redis+maven）
##### https://github.com/leslie1015/silk-web （vue2.6+webpack+iView+ElementUI）

## 测试地址（对应“说明”中Demo的代码）
##### [列表配置界面](http://139.186.75.156:9004/listConfig.html)
##### [配置后列表界面](http://139.186.75.156:9004/listTool.html)
##### （测试账号/密码：admin/123456， 可使用已有测试数据的resourceId来修改配置）
#### 前端有sessionStorage缓存配置信息，在配置界面提交配置信息后，需要关闭该站点或者重启浏览器再次进入列表界面查看效果

