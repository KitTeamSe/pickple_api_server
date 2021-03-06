package com.se.pickple_api_server.v1.board.domain.entity;

import com.se.pickple_api_server.v1.board.domain.type.BoardType;
import com.se.pickple_api_server.v1.common.domain.entity.BaseEntity;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="account_id",referencedColumnName = "accountId",nullable = false)
    private Account account;

    @Size(min=2,max=50)
    @Column(nullable=false)
    private String title;

    @Size(min=2, max=2000)
    @Column(nullable=false)
    private String text;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column(nullable = false)
    private Integer hit;

    @Column(nullable = false)
    private Integer isDeleted;

    public Board(Account account, @Size(min = 2, max = 50) String title, @Size(min = 2, max = 2000) String text, BoardType boardType, Integer hit, Integer isDeleted) {
        this.account = account;
        this.title = title;
        this.text = text;
        this.boardType = boardType;
        this.hit = hit;
        this.isDeleted = isDeleted;
    }

    public void updateBoardContents(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public void updateIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
