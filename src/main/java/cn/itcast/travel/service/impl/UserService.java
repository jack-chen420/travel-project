package cn.itcast.travel.service.impl;


import cn.itcast.travel.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);
    
    //激活
	boolean active(String code);

	User login(User user);

    
}
