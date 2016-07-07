 environments{
	//default properties
	base{
		encoding = "utf-8"
		db{
			name = "appsugar-archetypes"
		}
		test{
			jdbc{
				groupId = "com.h2database"
				artifactId = "h2"
				version = "1.4.191"
				url = "jdbc:h2:~/tmp/${db.name}"
				driverClassName = "org.h2.Driver"
				username = "sa"
				password = ""
			}
			hibernate{
				dialect = "org.hibernate.dialect.H2Dialect"
			}
		}
		
		jdbc = test.jdbc
		hibernate = test.hibernate
		dbunit{
			dataTypeFactoryName = "org.dbunit.ext.h2.H2DataTypeFactory";
			operationType = "CLEAN_INSERT";
			sampleData =  "/src/test/resources/data/sample-data.xml";
		}
	}
	mysql{
		db{
			name = "appsugar"
		}
		jdbc {
			groupId = "mysql"
			artifactId = "mysql-connector-java"
			version = "5.1.39"
			url = "jdbc:mysql://localhost:3306/${db.name}?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8"
			driverClassName = "com.mysql.jdbc.Driver"
			username = "root"
			password = "123456"
		}
		hibernate{
			dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
		}
		test{
			jdbc = jdbc
			hibernate = hibernate
		}
		dbunit{
			dataTypeFactoryName = "org.dbunit.ext.mysql.MySqlDataTypeFactory";
		}
	}
	
}