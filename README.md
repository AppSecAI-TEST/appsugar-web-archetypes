# appsugar-web-archetypes
xxx

生成数据库,当entity bean改变时需要执行该task.
如果使用了gradle daemon,执行完该语句后,需要执行  gradle --stop

## gradle appsugar-archetypes-bean:generateSchema  

编译打包

## gradle clean build

本地运行jetty   http://localhost:8080 登录账号密码: admin  admin

## gradle appsugar-archetypes-web:jettyRun


当测试数据被修改后需要执行(appsugar-archetypes-data/src/test/resources/data/sample-data.xml)

## gradle appsugar-archetypes-data:populateTestDb
