package hello.servlet.web.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository(); // 싱글톤화

    public static MemberRepository getInstance() {
        return instance; // 선언과 동시에 만듦
    }

    private MemberRepository(){
        // 싱글톤을 위해 생성자를 막음
    }


    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }



    public void init() {
        save(Member.builder().age(20).username("현덕1").build());
        save(Member.builder().age(30).username("현덕2").build());
        save(Member.builder().age(40).username("현덕3").build());

    }







}
