package org.appsugar.entity.account;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 204531272L;

    public static final QUser user = new QUser("user");

    public final org.appsugar.bean.entity.QLongIdEntity _super = new org.appsugar.bean.entity.QLongIdEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath loginName = createString("loginName");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final ListPath<String, StringPath> permissionList = this.<String, StringPath>createList("permissionList", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath phone = createString("phone");

    public final ListPath<Role, QRole> roleList = this.<Role, QRole>createList("roleList", Role.class, QRole.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

