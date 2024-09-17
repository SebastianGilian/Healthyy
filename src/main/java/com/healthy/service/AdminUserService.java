package com.healthy.service;

import com.healthy.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AdminUserService {
    List<User> listAll();
    Page<User> paginate(Pageable pageable);
    User create(User user);
    User findById(Integer id);
    User update(Integer id, User user);
    void delete(Integer id);

}
