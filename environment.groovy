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
				password = "";
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
	//other properties
}