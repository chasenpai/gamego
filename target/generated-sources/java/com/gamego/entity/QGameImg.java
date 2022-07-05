package com.gamego.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGameImg is a Querydsl query type for GameImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGameImg extends EntityPathBase<GameImg> {

    private static final long serialVersionUID = 1916462765L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGameImg gameImg = new QGameImg("gameImg");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final QGame game;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgName = createString("imgName");

    public final StringPath imgUrl = createString("imgUrl");

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath oriImgName = createString("oriImgName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath repImgYn = createString("repImgYn");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QGameImg(String variable) {
        this(GameImg.class, forVariable(variable), INITS);
    }

    public QGameImg(Path<? extends GameImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGameImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGameImg(PathMetadata metadata, PathInits inits) {
        this(GameImg.class, metadata, inits);
    }

    public QGameImg(Class<? extends GameImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.game = inits.isInitialized("game") ? new QGame(forProperty("game")) : null;
    }

}

