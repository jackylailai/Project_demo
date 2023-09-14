package org.example.repository;

import org.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

//用於訪問資料庫user的實體
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //User對應到table的class名字
    //默认提供了Optional<User> findById(Long id);
    //USER本身是實體 跟資料庫表格名字一樣

    User findByName(String name);

    @Query("select u from User u where u.id <= ?1")
    Page<User> findMore(Long maxId, Pageable pageable);
    //Page是一個集合型別，用來封裝多個 User 物件。每個 User 物件代表一個使用者資料
    //當你呼叫 findMore 方法時，內部會在記憶體中創建一個新的 Page<User> 實例，然後將查詢結果和分頁相關的資訊填充到這個實例中。//findMore 方法會從資料庫中查詢使用者資料，然後將這些使用者資料封裝到一個 Page<User> 物件中。這個 Page<User> 物件包含了查詢到的使用者資料以及分頁相關的資訊。
    //findMore 方法會從資料庫中查詢使用者資料，然後將這些使用者資料封裝到一個 Page<User> 物件中。這個 Page<User> 物件包含了查詢到的使用者資料以及分頁相關的資訊。
    @Modifying
    @Transactional
    @Query("update User u set u.name = ?1 where u.id = ?2")
    int updateById(String name, Long id);

    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username LIKE %:username%")
    List<User> searchStudentsByUsername(@Param("username") String username);
}
