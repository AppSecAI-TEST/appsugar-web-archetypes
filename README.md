# appsugar-web-archetypes

* gradle generateSchema  
* gradle populateTestDb  (after every gradle generateSchema )
* gradle clean build
* gradle appRun

## Tutorial

### Create Entity bean appsugar-archetypes-bean

```java
package org.appsugar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Email;

@Entity(name = "as_person")
public class Person extends IdEntity {
	private static final long serialVersionUID = 9088528084480261924L;
	private String name;
	private Integer age;
	private String email;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "email")
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=").append(name).append(", age=").append(age).append(", email=").append(email)
				.append("]");
		return builder.toString();
	}

}
```
### Create condition appsugar-archetypes-bean

```java
package org.appsugar.condition;

public class PersonCondition extends IdEntityCondition {
	//start like
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonCondition [name=").append(name).append("]");
		return builder.toString();
	}

}
```

### Edit db data  (appsugar-archetypes-data/src/test/resources/data/sample-data.xml)

#### append

```xml
<as_person id="-1" name="NewYoung" age="108" email="shenliuyang@gmail.com" created_at="2016-06-23" updated_at="2016-06-23"/>
```

### Create PersonRepository (appsugar-archetypes-data)

```java
package org.appsugar.repository;

import java.util.List;

import org.appsugar.condition.PersonCondition;
import org.appsugar.entity.Person;

public interface PersonRepository extends IdEntityRepository<Person, PersonCondition> {

	public List<Person> findByNameStartingWith(String name);

}

```

### Create Specification (appsugar-archetypes-data)

```java
package org.appsugar.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.appsugar.condition.PersonCondition;
import org.appsugar.entity.Person;
import org.appsugar.entity.account.Role;
import org.appsugar.extend.SpecificationQueryWrapper;
import org.springframework.stereotype.Component;

@Component
public class PersonSpecification extends IdEntitySpecification<Person, PersonCondition> {

	public PersonSpecification() {
		super();
	}

	public PersonSpecification(PersonCondition conditionObject) {
		super(conditionObject);
	}

	@Override
	protected void addCondition(SpecificationQueryWrapper<Person> query, Root<Person> root, CriteriaBuilder cb,
			PersonCondition condition) {
		super.addCondition(query, root, cb, condition);
		String name = condition.getName();
		if (StringUtils.isNotBlank(name)) {
			Expression<String> nameExpression = root.get(Role._name);
			query.add(cb.like(nameExpression, name + "%"));
		}
	}

}

```

### Create PersonRepositoryTest (appsugar-archetypes-data)

```java
package org.appsugar.repository;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonRepositoryTest extends BaseJpaDaoTestCase {

	@Autowired
	private PersonRepository repository;

	@Test
	public void testFindByNameStartLike() {
		String name = "New";
		List<Person> personList = repository.findByNameStartLike(name);
		logger.debug("testFindByNameStartLike name is {} result is {}", name, personList);
		Assert.assertTrue(CollectionUtils.isNotEmpty(personList));
	}
}

```

### Execute  gradle generateSchema 
### Execute  gradle --stop (fix file lock bug)
### Execute  gradle populateTestDb
### Execute  gradle appsugar-archetypes-data:test
