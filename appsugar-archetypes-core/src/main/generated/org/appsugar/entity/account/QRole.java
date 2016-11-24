package org.appsugar.entity.account;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = 204438259L;

    public static final QRole role = new QRole("role");

    public final org.appsugar.bean.entity.QLongIdEntity _super = new org.appsugar.bean.entity.QLongIdEntity(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    public final ListPath<String, StringPath> permissionList = this.<String, StringPath>createList("permissionList", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.util.Date> updatedAt = _super.updatedAt;

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

