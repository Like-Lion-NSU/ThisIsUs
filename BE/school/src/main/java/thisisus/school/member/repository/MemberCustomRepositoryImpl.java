package thisisus.school.member.repository;

import static thisisus.school.member.domain.QMember.*;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
	private final JPAQueryFactory queryFactory;
	private final EntityManager entityManager;

	@Override
	public long deleteByDeletedAt() {
		long count = queryFactory
			.delete(member)
			.where(member.deletedAt.eq(LocalDate.now()))
			.execute();

		entityManager.clear();
		return count;
	}
}