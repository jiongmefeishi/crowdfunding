# crowdfunding
 The platform composed of sponsors and investors has the characteristics of low threshold diversity, relying on the power of the public and paying attention to creativity. It refers to the behavior of raising funds from the public to support the initiated individuals or organizations, generally speaking, connecting sponsors and sponsors through the platform on the Internet

众筹平台，由发起人、跟投人、平台构成。具有低门槛、多样性、依靠大众力量、注重创意的特征，是指一种向群众募资，以支持发起的个人或组织的行为。一般而言是透过网络上的平台连结起赞助者与提案者。

### 开发工具

| 工具         | 版本                      |
| :----------- | :------------------------ |
| IDEA         | 2019                      |
| 本地开发系统 | window10 家庭版           |
| 线上测试系统 | Linux centos7  / Ubuntu19 |
|              |                           |

**《工程安装导入说明》**

1、选择open  **父工程 crowdfunding01-admin-parent**

2、导入两个辅助工程

File-> Project Structure -> Modules -> + ---> 添加两个辅助工程模块**辅助工程 crowdfunding05-common-util**、**辅助工程 crowdfunding05-common-reverse**

3、配置启动项

Tomcat Server -> Deployment -> + Artificts -> **crowdfunding02-admin-webui:war**

**【工程模块开发】**

|   工程模块开发顺序   |                          关键时间点                          |
| :------------------: | :----------------------------------------------------------: |
| mybatis 逆向工程模块 | [mybatis逆向工程](https://github.com/zqtao2332/crowdfunding/tree/929ebd09e199977d7a7f72dc59f6a716e11f7896) |
|    管理员管理模块    | [管理员管理模块](https://github.com/zqtao2332/crowdfunding/tree/40ce306d1552911a0adc50d334e7647c6ff3e1de) |
|  权限之角色管理模块  | [权限之角色管理模块](https://github.com/zqtao2332/crowdfunding/tree/21f8e19ab9bee86318f45cc42174c735a8afe474) |
|  权限之菜单管理模块  |                                                              |
|                      |                                                              |

**【工程关系说明】**

**1、工程依赖关系说明**

1. parent工程管理依赖控制版本
2. webui继承parent，依赖于component
3. component继承parent，依赖于util和entity
4. entity继承parent
5. util和reverse 都是独立工程，不参与继承，聚合

**2、工程各自职责说明**

**父工程 crowdfunding01-admin-parent**

1. 统一的依赖管理，去重，控制版本、控制插件的版本
2. 聚合工程

**子工程 crowdfunding02-admin-webui**

1. 项目工程运行的核心，只运行此war包工程
2. 所有mybatis的xxxMapper.xml文件均在此工程下
3. 所有的web配置项，web.xml，Spring等整合配置文件均在此工程下
4. 所有的静态资源，js,css,html 均在此工程下

**子工程 crowdfunding03-admin-component**

  定义所有的功能性组件，如：

- mapper接口
- mvc.config配置项
- mvc.handler控制器
- mvc.interceptor拦截器
- service 业务层

**子工程 crowdfunding04-admin-entity**

  定义所有的实体类

**辅助工程 crowdfunding05-common-util**

定义所有的辅助工具、常量类、自定义异常，如：

1. 加密工具
2. 时间工具
3. 常量类
4. 自定义异常

**辅助工程 crowdfunding05-common-reverse**

负责mybatis的逆向工程

**《更新》**

**【2020/6/6】**

| 更新内容操作 | 更新描述                    |
| :----------: | --------------------------- |
|     [*]      | 更新-基于模态框新增role记录 |
|     [*]      | 更新-role信息更新           |
|     [+]      | 新增-单条role 记录删除      |
|     [+]      | 新增-批量删除样式和响应事件 |
|     [*]      | 更新-role批量删除           |

**【2020/6/2】**

| 更新内容操作 | 更新描述                   |
| :----------: | -------------------------- |
|     [+]      | mybatis逆向工程-角色       |
|     [+]      | 新增权限之角色             |
|     [+]      | 权限校验之分页查询         |
|     [+]      | 新增-基于bootstrap的模态框 |

**【2020/5/29】**

| 更新内容操作 | 更新描述                 |
| :----------: | ------------------------ |
|     [+]      | 管理员登录               |
|     [+]      | 管理用户分页查询         |
|     [+]      | 管理用户新增、删除、更新 |

**【2020/5/20】**

| 更新内容操作 | 更新描述             |
| :----------: | -------------------- |
|     [+]      | 整合SpringMVC        |
|     [+]      | 配置Ajax请求返回格式 |
|     [+]      | 配置异常映射机制     |

**【2020/5/17】**

| 更新内容操作 | 更新描述                                |
| :----------: | --------------------------------------- |
|     [+]      | Spring集成日志系统 增加配置 logback.xml |
|     [+]      | Spring事务配置-声明式事务               |

**【2020/5/16】**

| 更新内容操作 | 更新描述                                                 |
| :----------: | -------------------------------------------------------- |
|     [+]      | 更新子工程crowdfunding06-common-reverse  mybatis逆向工程 |
|     [+]      | 整合spring+mybatis                                       |

**【2020/5/14】**

| 更新内容操作 | 更新描述                               |
| :----------: | :------------------------------------- |
|     [+]      | 父工程 crowdfunding01-admin-parent     |
|     [+]      | 子工程 crowdfunding02-admin-webui      |
|     [+]      | 子工程 crowdfunding03-admin-component  |
|     [+]      | 子工程 crowdfunding04-admin-entity     |
|     [+]      | 辅助工程 crowdfunding05-common-util    |
|     [+]      | 辅助工程 crowdfunding06-common-reverse |

