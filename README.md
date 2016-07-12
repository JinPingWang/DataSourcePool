# DataSourcePool
自己实现的一个数据库连接池，可以动态管理多个不同类型的数据库连接池。此外还添加了商业数据库连接池的使用：dbcp, c3p0等。
# 类说明
## DatasourceConfigBean
用于存放每个数据库连接池的连接信息。如驱动，连接地址，用户名，密码，最大连接数，初始连接数等。
## DatasourceConfig
存放不同数据库连接池信息的类。可以动态加载新的数据库信息，删除已经加载的数据库信息。
## DatasourcePool
根据DatasourceConfigBean的信息，创建一个数据库连接池，里面提供了获取连接，释放连接，关闭连接池等方法。
## DatasourceDriverManager
存放和动态管理多个DatasourcePool。可以获得某个数据库连接池或根据某个连接池获得一个这个数据库连接池的一个连接。

