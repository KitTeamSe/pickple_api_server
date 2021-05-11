package com.se.pickple_api_server.v1.account.infra.repository;

import com.querydsl.jpa.JPQLQuery;
import com.se.pickple_api_server.v1.account.application.dto.AccountReadDto;
import com.se.pickple_api_server.v1.account.domain.entity.Account;
import com.se.pickple_api_server.v1.account.domain.entity.QAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountQueryRepositoryImpl extends QuerydslRepositorySupport implements AccountQueryRepository {
    public AccountQueryRepositoryImpl() { super(Account.class); }

    @Override
    public Page<Account> search(AccountReadDto.SearchRequest searchRequest) {

        QAccount account = QAccount.account;
        JPQLQuery query = from(account);

        if (searchRequest.getKeyword() != null) {
            query.where(
                    account.name.contains(searchRequest.getKeyword())
                    .or(account.idString.contains(searchRequest.getKeyword()))
            );
        }
        if (searchRequest.getType() != null) {
            query.where(account.accountType.eq(searchRequest.getType()));
        }

        Pageable pageable = searchRequest.getPageRequest().of();
        List<Account> accountList = getQuerydsl().applyPagination(pageable, query).fetch();
        Long totalElement = query.fetchCount();
        return new PageImpl(accountList, pageable, totalElement);
    }
}