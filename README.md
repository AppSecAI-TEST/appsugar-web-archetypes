# Install Base Dependency

* checkout https://github.com/shenliuyang/appsugar-web.git
* gradle clean install
* checkout https://github.com/ferigma/dbunit-gradle-plugin.git
* gradle publishToMavenLocal

# appsugar-web-archetypes

* gradle generateSchema  (execute only Persistence Bean was changed)
* gradle populateTestDb  (after every gradle generateSchema )
* gradle clean build
* gradle appRun

## Tutorial

### Create Entity bean appsugar-archetypes-core

```java
package org.appsugar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

@Table(name="as_person")
@Entity
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
### Create condition appsugar-archetypes-core

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

### Edit db data  (appsugar-archetypes-core/src/test/resources/data/sample-data.xml)

#### append

```xml
<as_person id="-1" name="NewYoung" age="108" email="shenliuyang@gmail.com" created_at="2016-06-23" updated_at="2016-06-23"/>
```

### Create PersonRepository (appsugar-archetypes-core)

```java
package org.appsugar.repository;

import java.util.List;

import org.appsugar.condition.PersonCondition;
import org.appsugar.entity.Person;

public interface PersonRepository extends IdEntityRepository<Person, PersonCondition> {

	public List<Person> findByNameStartingWith(String name);

}

```

### Create Specification (appsugar-archetypes-core)

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

### Create PersonRepositoryTest (appsugar-archetypes-core)

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
### Execute  gradle appsugar-archetypes-core:test

### Create PersonService (appsugar-archetypes-core)

```java
package org.appsugar.service;

import java.util.List;

import org.appsugar.condition.PersonCondition;
import org.appsugar.entity.Person;

public interface PersonService extends GenericService<Person, PersonCondition> {

	public List<Person> getByNameStartingWith(String name);

}
```

### Create PersonServiceImpl (appsugar-archetypes-core)

```java
package org.appsugar.service.impl;

import java.util.List;

import org.appsugar.condition.PersonCondition;
import org.appsugar.entity.Person;
import org.appsugar.repository.PersonRepository;
import org.appsugar.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, PersonCondition> implements PersonService {

	private PersonRepository personRepository;

	@Override
	public List<Person> getByNameStartingWith(String name) {
		return personRepository.findByNameStartingWith(name);
	}

	@Autowired
	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

}

```

### Create PersonController (appsugar-archetypes-web)

```java
package org.appsugar.controller;

import org.appsugar.condition.PersonCondition;
import org.appsugar.dto.page.Pageable;
import org.appsugar.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService service;

	@RequestMapping
	public String list(Model model, PersonCondition condition, Pageable pageable) {
		model.addAttribute("page", service.getByCondition(condition, pageable));
		return "/person/list";
	}
}
```

### Create list.jsp (appsugar-archetypes-web/src/main/webapp/WEB-INF/views/person/list.jsp)

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<head>
<title>Person List</title>
</head>
<body>
	<div style="float: right">
		<form action="#" method="POST" class="form-inline">
			<input type="text" name="name" class="form-control input-sm"
				value="${param.name}" placeholder="name">
			<button type="submit" class="btn btn-primary btn-sm" id="search_btn">
				<spring:message code="search" />
			</button>
		</form>
	</div>

	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>age</th>
				<th>email</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="bean" items="${page.content}">
				<tr>
					<td>${bean.id}</td>
					<td>${bean.name}</td>
					<td>${bean.age}</td>
					<td>${bean.email}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<tags:pagination page="${page}" />
</body>
```

### append menu.tag (appsugar-archetypes-web/src/main/webapp/WEB-INF/tags/menu.tag)

```jsp
menuGroup.addChild(new MenuConfig("/person","PersonManager","menu.person",null,null));
```

### Execute gradle bootRun

#### open browser  type http://localhost:8080    (google chrome are suggested)
